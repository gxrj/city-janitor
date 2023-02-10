package io.github.gxrj.janitory.core.Duty;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import io.github.gxrj.janitory.utils.PlainJson;

@RestController
@RequestMapping( consumes = "application/json", produces = "application/json" )
public class DutyController {
    
    @PostMapping( "/manager/duty/new" )
    public String create( @RequestBody Duty duty ) {

        return PlainJson.builder().append( "message", "CServico criado com sucesso!" ).build();
    }
}
