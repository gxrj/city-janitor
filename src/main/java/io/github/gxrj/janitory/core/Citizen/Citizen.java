package io.github.gxrj.janitory.core.Citizen;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Data;

@Data
@Entity( name = "Cidadao" )
public class Citizen {

    @Id
    @Column( columnDefinition = "uuid not null" )
    @GeneratedValue( strategy = GenerationType.AUTO )
    private UUID id;
    
    @Column( name = "nome", length = 60, nullable = false )
    private String name;
    
    @Column( length = 60, unique = true, nullable = false )
    private String email;
}
