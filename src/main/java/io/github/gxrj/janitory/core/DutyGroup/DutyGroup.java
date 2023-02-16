package io.github.gxrj.janitory.core.DutyGroup;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity( name = "GrupoServico" )
public class DutyGroup implements Serializable {
    
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;

    @NotBlank
    @Column( name = "descricao" )
    private String name;
}