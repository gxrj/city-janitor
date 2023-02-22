package io.github.gxrj.janitory.core.Citizen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.github.gxrj.janitory.utils.PlainJson;

@RestController
public class CitizenController {
    
    @Autowired
    private CitizenService citizenService;

    @PostMapping( { "/anonymous/account/new", "/user/account/edition" } )
    public String create( @RequestBody Citizen account ) {
        citizenService.createOrUpdate( account );
        return PlainJson.builder().append( "message", "Registardo com sucesso!" ).build();
    }

    @PostMapping( "/user/account/deletion" )
    public String delete( @RequestBody Citizen account ) {
        citizenService.delete( account );
        return PlainJson.builder().append( "message", "Conta excluida com sucesso" ).build();
    }
}
