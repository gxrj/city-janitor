package io.github.gxrj.janitory.core.Action;


import io.github.gxrj.janitory.core.Call.CallDto;
import io.github.gxrj.janitory.core.PubAgent.PubAgentDto;
import io.github.gxrj.janitory.utils.PlainJson;
import io.github.gxrj.janitory.utils.ProtocolEncoder;

import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActionController {
    
    @Autowired
    private ActionService actionService;

    @PostMapping( "/agent/action/new" )
    public String create( ActionDto dto ) { 

        var now = LocalDateTime.now();

        try {
            var entity = Action.builder()
                            .creationDate( now )
                            .description( dto.description )
                            .call( CallDto.deserialize( dto.callDto ) )
                            .agent( PubAgentDto.deserialize( dto.agentDto ) )
                            .protocol( ProtocolEncoder.encode( now, dto.agentDto.getLogin() ) )
                            .build();

            actionService.createOrUpdate( entity );

            return PlainJson.builder().append( "message", "Registrado com sucesso!").build();
        }
        catch( Exception ex ) {
            return PlainJson.builder()
                            .append( "error", "Falha ao gerar atendimento: " + ex.getMessage() )
                            .build();
        }
    }

    @PostMapping( "/authenticated/action/all_by_call" )
    public List<ActionDto> listByCall( CallDto dto ) {
        return listSerialization( 
                    actionService.listByCall( dto.getProtocol() ) );
    }
    @GetMapping( "/agent/action" )
    public ActionDto findByProtocol( @QueryParam( "protocol" ) String protocol ) { 
        return ActionDto.serialize( actionService.findByProtocol( protocol ) );
    }

    @GetMapping( "/manager/action" )
    public List<ActionDto> listByAgent( @QueryParam( "agent" ) String agentLogin ) { 
        return listSerialization( actionService.listByAuthor( agentLogin ) );
    }

    @GetMapping( "/manager/action/{deptName}" )
    public List<ActionDto> listFromPeriodByDept( 
                    @QueryParam( "to" ) String to, 
                    @QueryParam( "from" ) String from, 
                    @PathParam( "deptName" ) String deptName ) { 
        
        var start = LocalDateTime.parse( from, DateTimeFormatter.ISO_LOCAL_DATE_TIME );
        var end = LocalDateTime.parse( to, DateTimeFormatter.ISO_LOCAL_DATE_TIME );

        return listSerialization( 
                        actionService.listIntervalByDept( start, end, deptName ) );
    }

    @GetMapping( "/manager/action/{agent}" )
    public List<ActionDto> listFromPeriodByAgent( 
                    @QueryParam( "to" ) String to, 
                    @QueryParam( "from" ) String from, 
                    @PathParam( "agent" ) String agentLogin ) { 

        var start = LocalDateTime.parse( from, DateTimeFormatter.ISO_LOCAL_DATE_TIME );
        var end = LocalDateTime.parse( to, DateTimeFormatter.ISO_LOCAL_DATE_TIME );

        return listSerialization( 
                        actionService.listIntervalByAgent( start, end, agentLogin ) );
    }

    private List<ActionDto> listSerialization( List<Action> list ) {
        return list.parallelStream().map( ActionDto::serialize ).toList();
    }
}
