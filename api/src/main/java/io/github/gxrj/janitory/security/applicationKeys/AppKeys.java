package io.github.gxrj.janitory.security.applicationKeys;

import com.nimbusds.jose.jwk.JWK;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity( name = "ChavesAssinatura" )
public class AppKeys {
    
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;

    @Column( name = "par_de_chaves", nullable = false )
    private JWK keyPair;
}
