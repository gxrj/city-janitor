package io.github.gxrj.janitory.core.Duty;

import io.github.gxrj.janitory.core.Dept.Dept;
import io.github.gxrj.janitory.core.DutyGroup.DutyGroup;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
@Entity( name = "Servico" )
public class Duty implements Serializable {
    
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;

    @NotBlank
    @Column( name = "descricao", length = 60, nullable = false )
    private String name;

    @ManyToOne
    @JoinColumn( name = "categoria" )
    private DutyGroup group;

    @ManyToOne
    @JoinColumn( name = "secreatria" )
    private Dept dept;
}