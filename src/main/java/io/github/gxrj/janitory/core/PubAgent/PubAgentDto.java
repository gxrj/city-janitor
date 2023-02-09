package io.github.gxrj.janitory.core.PubAgent;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;

import io.github.gxrj.janitory.core.Dept.DeptService;

import jakarta.validation.constraints.NotEmpty;

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
    String dept;
    String password;
    
    @NotEmpty
    String login;
    
    @JsonValue
    public static PubAgentDto serialize( PubAgent entity ) {
        return new PubAgentDto( 
                    entity.getName(), 
                    entity.getDept().getName(), 
                    entity.getPassword(), 
                    entity.getLogin() );
    }

    public static PubAgent desserialize( PubAgentDto dto, DeptService deptService ) throws Exception {

        var dept = deptService.findByName( dto.dept );
        
        if( dept == null ) throw new Exception( "Departamento não encontrado" );
        if( dto.login == null ) throw new Exception( "Login não encontrado" );
        if( dto.name == null ) throw new Exception( "Nome não encontrado" );
        if( dto.password == null || dto.password.isEmpty() ) throw new Exception( "Senha não encontrada" );

        return PubAgent.builder()
                            .dept( dept )
                            .name( dto.name )
                            .login( dto.login )
                            .password( dto.password )
                            .build();
    }
}
