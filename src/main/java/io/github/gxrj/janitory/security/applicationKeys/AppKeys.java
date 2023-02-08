package io.github.gxrj.janitory.security.applicationKeys;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class AppKeys {
    
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;

    // To Do: Insert a JWK attribute
}
