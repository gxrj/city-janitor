package io.github.gxrj.janitory.core.Action;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

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
    String userReply;
    String createdAt;
    String agentLogin;
    String description;
    String callProtocol;

    public static ActionDto serialize( Action action ) {
        return ActionDto.builder()
                        .protocol( action.getProtocol() )
                        .userReply( action.getUserReply() )
                        .description( action.getDescription() )
                        .agentLogin( action.getAgent().getLogin() )
                        .callProtocol( action.getCall().getProtocol() )
                        .createdAt( action.getCreationDate().toString() )
                        .build();
    }

    public static Action deserialize( ActionDto dto ) {
        // To do
        return Action.builder()
                     .build();
    }
}
