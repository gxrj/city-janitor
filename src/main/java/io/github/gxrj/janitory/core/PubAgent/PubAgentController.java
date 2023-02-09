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

    @PostMapping( "/agent/v1/new_password" )
    public String resetPassword() {
        // To do: Get logged user
        // To do: Get new password from request
        // To do: udpdate user password

        return PlainJson.builder().append( "message", "Senha alterada!" ).build();
    }
    
    @PostMapping( "/manager/v1/new_agent" )
    public String createAgent( @RequestBody PubAgentDto dto ) {

        try{
            var entity = PubAgentDto.desserialize( dto, deptService );

            // To do: encrypt password

            agentService.createOrUpdate( entity );

            return PlainJson.builder()
                    .append( "message", "gravado com sucesso!" )
                    .build();
        }
        catch( Exception ex ) {
            return PlainJson.builder()
                    .append( "error", ex.getMessage() )
                    .build();
        }
    }

    @PostMapping( "/manager/v1/agent/new_password" )
    public String resetPassword( @RequestBody PubAgentDto dto ) {

        var entity = agentService.findByLogin( dto.getLogin() );

        if( entity == null ) 
            return PlainJson.builder().append( "error", "Login não encontrado" ).build();

        // To do: update password

        agentService.createOrUpdate( entity );

        return PlainJson.builder()
                .append( "message", "Senha alterada com sucesso!" )
                .build();
    }
}
