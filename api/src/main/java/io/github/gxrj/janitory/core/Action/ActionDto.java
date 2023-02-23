package io.github.gxrj.janitory.core.Action;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.github.gxrj.janitory.core.Call.CallDto;
import io.github.gxrj.janitory.core.PubAgent.PubAgentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder @AllArgsConstructor @NoArgsConstructor
@JsonNaming( PropertyNamingStrategies.SnakeCaseStrategy.class )
@JsonIgnoreProperties( value = "createdAt", allowGetters = true )
public class ActionDto implements Serializable {
    
    String protocol;
    CallDto callDto;
    String userReply;
    String createdAt;
    String description;
    PubAgentDto agentDto;

    public static ActionDto serialize( Action action ) {
        return ActionDto.builder()
                        .protocol( action.getProtocol() )
                        .userReply( action.getUserReply() )
                        .description( action.getDescription() )
                        .callDto( CallDto.serialize( action.getCall() ) )
                        .agentDto( PubAgentDto.serialize( action.getAgent() ) )
                        .createdAt( action.getCreationDate().toString() )
                        .build();
    }
}
