package io.github.gxrj.janitory.core.PubAgent;

import io.github.gxrj.janitory.utils.PlainJson;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( consumes = "application/json", produces = "application/json" )
public class PubAgentController {

    @Autowired
    private PubAgentService agentService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping( "/manager/agent/all" )
    public List<PubAgent> listAll() {
        return agentService.listAll();
    }

    @PostMapping( "/manager/agent/all_by_deptartment" )
    public List<PubAgent> listByDeptName( String deptName ) {
        return agentService.listByDeptName( deptName );
    }

    @PostMapping( "/agent/account_edition" )
    public String update() {
        // To do: Get logged user
        // To do: Get new data from request
        // To do: udpdate user data

        return PlainJson.builder().append( "message", "Dados atualizados!" ).build();
    }

    @PostMapping( "/agent/account_edition/new_password" )
    public String resetPassword() {
        // To do: Get logged user
        // To do: Get new password from request
        // To do: udpdate user password

        return PlainJson.builder().append( "message", "Senha alterada!" ).build();
    }
    
    @PostMapping( "/manager/agent_edition/new_agent" )
    public String createAgent( @RequestBody PubAgentDto dto ) {

        try{
            PubAgentDto.validateFields( dto );

            var entity = PubAgent.builder()
                            .dept( dto.dept )
                            .name( dto.name )
                            .login( dto.login )
                            .isAdmin( dto.isAdmin )
                            .password( passwordEncoder.encode( dto.password ) )
                            .build();

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

    @PostMapping( "/manager/agent_edition/new_password" )
    public String resetPassword( @RequestBody PubAgentDto dto ) {

        var entity = agentService.findByLogin( dto.getLogin() );

        if( entity == null ) 
            return PlainJson.builder().append( "error", "Login não encontrado" ).build();

        entity.setPassword( passwordEncoder.encode( dto.password ) );

        agentService.createOrUpdate( entity );

        return PlainJson.builder()
                .append( "message", "Senha alterada com sucesso!" )
                .build();
    }
}
