package io.github.gxrj.janitory.core.DutyGroup;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

@JsonIgnoreProperties( "id" )

@Entity( name = "GrupoServico" )
public class DutyGroup {
    
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;

    @NotEmpty
    @Column( name = "descricao" )
    private String name;
}