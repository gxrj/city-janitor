package io.github.gxrj.janitory.core.Call;

import io.github.gxrj.janitory.core.Citizen.Citizen;
import io.github.gxrj.janitory.core.Citizen.CitizenService;
import io.github.gxrj.janitory.utils.PlainJson;
import io.github.gxrj.janitory.utils.ProtocolEncoder;
import jakarta.ws.rs.QueryParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CallController {
    
    @Autowired
    private CallService callService;

    @Autowired
    private CitizenService userService;

    @PostMapping( "/anonymous/call/new" )
    public String create( @RequestBody CallDto dto ) {

        var now = LocalDateTime.now();
        var author = getAuthor( validateEmail( dto.authorEmail ) );
        dto.protocol = ProtocolEncoder.encode( now, validateEmail( dto.authorEmail ) );

        var call = Call.builder()
                    .duty( dto.duty )
                    .author( author )
                    .creationDate( now )
                    .address( dto.address )
                    .protocol( dto.protocol )
                    .description( dto.description )
                    .destination( dto.destination )
                    .status( Status.PROCESSING )
                    .build();

        callService.createOrUpdate( call );

        return PlainJson.builder().append( "message", "Registrado com sucesso!" ).build();
    }

    @GetMapping( "/agent/call/all_by_dept/{deptName}" )
    public List<CallDto> listFromPeriodByDept(
                                    @PathVariable String deptName, 
                                    @QueryParam( "to" ) String to,
                                    @QueryParam( "from" ) String from ) {

        var start = LocalDateTime.parse( from, DateTimeFormatter.ISO_LOCAL_DATE_TIME );
        var end = LocalDateTime.parse( to, DateTimeFormatter.ISO_LOCAL_DATE_TIME );

        return listSerialization( callService.listIntervalByDept( start, end, deptName ) );
    }

    @GetMapping( "/agent/call/all_by_status" )
    public List<CallDto> listByStatus( @QueryParam("status") String status ) {
        return listSerialization( callService.listByStatus( Status.fromString( status ) ) );
    }

    @GetMapping( "/user/call/all_by_email" )
    public List<CallDto> listByEmail( @QueryParam( "email" ) String email ) {
        return listSerialization( callService.listByAuthor( email ) );
    }

    @GetMapping( "/agent/call" )
    public CallDto getByProtocol( @QueryParam( "protocol" ) String protocol ) {
        return CallDto.serialize( callService.findByProtocol( protocol ) );
    }

    private String validateEmail( String email ) {
        return email == null || email.isBlank() ? "anonimo" : email;
    }

    private Citizen getAuthor( String email ) {
        return email.equalsIgnoreCase( "anonimo" ) ? null : userService.findByEmail( email );
    }

    private List<CallDto> listSerialization( List<Call> list ) {
        return list.parallelStream().map( CallDto::serialize ).toList();
    }
}
