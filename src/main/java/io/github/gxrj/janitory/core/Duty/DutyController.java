package io.github.gxrj.janitory.core.Duty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import io.github.gxrj.janitory.utils.PlainJson;

@RestController
@RequestMapping( consumes = "application/json", produces = "application/json" )
public class DutyController {
    
    @Autowired
    private DutyService dutyService;

    @PostMapping( { "/manager/duty/new", "/manager/duty/edition" } )
    public String create( @RequestBody Duty duty ) {
        dutyService.createOrUpdate( duty );
        return PlainJson.builder().append( "message", "Servico criado com sucesso!" ).build();
    }
}
