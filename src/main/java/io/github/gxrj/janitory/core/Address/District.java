package io.github.gxrj.janitory.core.Address;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity( name = "Bairro" )
public class District implements Serializable {
    
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;

    @Column( name = "nome", nullable = false, unique = true )
    private String name;
}
