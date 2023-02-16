package io.github.gxrj.janitory.core.Call;

import io.github.gxrj.janitory.core.Citizen.Citizen;
import io.github.gxrj.janitory.core.Citizen.CitizenService;
import io.github.gxrj.janitory.utils.PlainJson;
import io.github.gxrj.janitory.utils.ProtocolEncoder;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
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
        dto.protocol = ProtocolEncoder.encode( now, dto.authorEmail );

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

    private String validateEmail( String email ) {
        return email.isBlank() ? "anonimo" : email;
    }

    private Citizen getAuthor( String email ) {
        return email.equalsIgnoreCase( "anonimo" ) ? null : userService.findByEmail( email );
    }
}
