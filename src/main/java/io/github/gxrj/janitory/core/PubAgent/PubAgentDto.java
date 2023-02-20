package io.github.gxrj.janitory.core.PubAgent;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;

import io.github.gxrj.janitory.core.Dept.Dept;

import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor

@JsonIgnoreProperties(
    value = "password",
    allowSetters = true, 
    ignoreUnknown = true 
) 
@JsonInclude( JsonInclude.Include.NON_EMPTY )
public class PubAgentDto implements Serializable {
    
    String name;
    Dept dept;
    String password;
    
    @NotBlank
    String login;
    
    @JsonValue
    public static PubAgentDto serialize( PubAgent entity ) {
        return new PubAgentDto( 
                    entity.getName(), 
                    entity.getDept(), 
                    entity.getPassword(), 
                    entity.getLogin() );
    }

    public static PubAgent deserialize( PubAgentDto dto ) throws Exception {

        if( dto.name == null || dto.name.isBlank() ) throw new Exception( "Nome não encontrado" );
        if( dto.login == null || dto.login.isBlank() ) throw new Exception( "Login não encontrado" );
        if( dto.password == null || dto.password.isBlank() ) throw new Exception( "Senha não encontrada" );

        return PubAgent.builder()
                            .dept( dto.dept )
                            .name( dto.name )
                            .login( dto.login )
                            .password( dto.password )
                            .build();
    }
}
