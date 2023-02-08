package io.github.gxrj.janitory.core.Action;

import java.util.UUID;

import io.github.gxrj.janitory.core.Call.Call;
import io.github.gxrj.janitory.core.PubAgent.PubAgent;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity( name = "Atendimento" )
public class Action {
 
    @Id
    @Column( columnDefinition = "uuid not null" )
    @GeneratedValue( strategy = GenerationType.AUTO )
    private UUID id;

    @Column( name = "descricao", nullable = false )
    private String description;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "ocorrencia" )
    private Call call;

    @ManyToOne
    @JoinColumn( name = "autor" )
    private PubAgent agent;
}