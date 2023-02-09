package io.github.gxrj.janitory.core.PubAgent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.gxrj.janitory.core.Dept.DeptService;
import io.github.gxrj.janitory.utils.PlainJson;

@RestController
@RequestMapping( consumes = "application/json", produces = "application/json" )
public class PubAgentController {
    
    @Autowired
    private DeptService deptService;

    @Autowired
    private PubAgentService agentService;
    
    @PostMapping( "/manager/v1/new_agent" )
    public String createAgent( @RequestBody PubAgentDto dto ) {

        try{
            var entity = PubAgentDto.desserialize( dto, deptService );
            agentService.createOrUpdate( entity );
        }
        catch( Exception ex ) {
            return PlainJson.builder()
                        .append( 
                            "error", 
                            ex.getMessage() 
                        )
                        .build();
        }

        return PlainJson.builder()
                    .append( 
                        "message", 
                        "gravado com sucesso!"
                    ).build();
    }
}
