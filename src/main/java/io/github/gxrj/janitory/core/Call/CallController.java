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

    @GetMapping( "/agent/call/all_by_dept" )
    public List<Call> listByDept( 
        @QueryParam( "to" ) String to,
        @QueryParam( "from" ) String from,
        @QueryParam( "dept_name" ) String deptName ) {

        var start = LocalDateTime.parse( from, DateTimeFormatter.ISO_LOCAL_DATE_TIME );
        var end = LocalDateTime.parse( to, DateTimeFormatter.ISO_LOCAL_DATE_TIME );
        return callService.listIntervalByDept( start, end, deptName );
    }

    @PostMapping( "/agent/call/all_by_status" )
    public List<Call> listByStatus( Status status ) {
        return callService.listByStatus( status );
    }

    @GetMapping( "/user/call/all_by_email" )
    public List<Call> listByEmail( @QueryParam( "email" ) String email ) {
        return callService.listByAuthor( email );
    }

    private String validateEmail( String email ) {
        return email == null || email.isBlank() ? "anonimo" : email;
    }

    private Citizen getAuthor( String email ) {
        return email.equalsIgnoreCase( "anonimo" ) ? null : userService.findByEmail( email );
    }
}
