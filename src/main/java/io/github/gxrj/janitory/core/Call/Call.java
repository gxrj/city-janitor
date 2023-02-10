package io.github.gxrj.janitory.core.Call;

import io.github.gxrj.janitory.core.Address.Address;
import io.github.gxrj.janitory.core.Citizen.Citizen;
import io.github.gxrj.janitory.core.Dept.Dept;
import io.github.gxrj.janitory.core.Duty.Duty;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity( name = "Ocorrencia" )
public class Call {
 
    @Id
    @Column( columnDefinition = "uuid not null" )
    @GeneratedValue( strategy = GenerationType.AUTO )
    private UUID id;

    @Column
    private Status status;

    @ManyToOne
    @JoinColumn( name = "usuario" )
    private Citizen author;

    @Embedded
    private Address address;

    @Column( name = "destino" )
    private Dept destination;

    @ManyToOne
    @JoinColumn( name = "servico" )
    private Duty duty;

    @Column( name = "descricao" )
    private String description;

    @Column( name = "protocolo", unique = true, nullable = false )
    private String protocol;
}
