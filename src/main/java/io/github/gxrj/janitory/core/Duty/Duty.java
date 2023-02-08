package io.github.gxrj.janitory.core.Duty;

import io.github.gxrj.janitory.core.Dept.Dept;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity( name = "Servico" )
public class Duty {
    
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;

    @Column( name = "descricao", length = 60, nullable = false )
    private String name;

    @ManyToOne
    @JoinColumn( name = "categoria" )
    private DutyGroup group;

    @ManyToOne
    @JoinColumn( name = "secreatria" )
    private Dept dept;
}