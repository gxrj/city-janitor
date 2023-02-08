package io.github.gxrj.janitory.core.Duty;

import java.util.UUID;

import io.github.gxrj.janitory.core.Dept.Dept;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity( name = "Servico" )
public class Duty {
    
    @Id
    @Column( columnDefinition = "uuid not null" )
    @GeneratedValue( strategy = GenerationType.AUTO )
    private UUID id;

    @Column( name = "descricao", length = 60, nullable = false )
    private String name;

    @ManyToOne
    @Column( name = "categoria" )
    private DutyGroup group;

    @ManyToOne
    @Column( name = "secreatria" )
    private Dept dept;
}