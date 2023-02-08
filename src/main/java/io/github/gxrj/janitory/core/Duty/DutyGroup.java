package io.github.gxrj.janitory.core.Duty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity( name = "GrupoServico" )
public class DutyGroup {
    
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;

    @Column( name = "descricao" )
    private String name;
}