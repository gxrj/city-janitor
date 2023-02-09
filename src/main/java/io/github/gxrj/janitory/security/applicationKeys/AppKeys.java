package io.github.gxrj.janitory.security.applicationKeys;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity( name = "ChavesAssinatura" )
public class AppKeys {
    
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;

    // To Do: Insert a JWK attribute
}
