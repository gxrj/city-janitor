package io.github.gxrj.janitory.core.PubAgent;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.github.gxrj.janitory.core.Dept.Dept;

import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor

@JsonIgnoreProperties(
    value = { "isAdmin", "password" },
    allowSetters = true, 
    ignoreUnknown = true 
)
@JsonInclude( JsonInclude.Include.NON_EMPTY )
@JsonNaming( PropertyNamingStrategies.SnakeCaseStrategy.class ) 
public class PubAgentDto implements Serializable {
    
    String name;
    Dept dept;
    String password;
    Boolean isAdmin;
    
    @NotBlank
    String login;
    
    @JsonValue
    public static PubAgentDto serialize( PubAgent entity ) {
        return new PubAgentDto( 
                    entity.getName(), 
                    entity.getDept(), 
                    entity.getPassword(),
                    entity.isAdmin(), 
                    entity.getLogin() );
    }

    /**
     * Checks if all required fields for creation of new entities are properly set.
     */
    public static void validateFields( PubAgentDto dto ) throws Exception {
        if( dto.isAdmin == null ) throw new Exception( "Privilégio de usuário não encontrado" );
        if( dto.name == null || dto.name.isBlank() ) throw new Exception( "Nome não encontrado" );
        if( dto.login == null || dto.login.isBlank() ) throw new Exception( "Login não encontrado" );
        if( dto.password == null || dto.password.isBlank() ) throw new Exception( "Senha não encontrada" );
    }
}
