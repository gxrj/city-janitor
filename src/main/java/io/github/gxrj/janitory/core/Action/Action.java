package io.github.gxrj.janitory.core.Action;

import java.time.LocalDateTime;
import java.util.UUID;

import io.github.gxrj.janitory.core.Call.Call;
import io.github.gxrj.janitory.core.PubAgent.PubAgent;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

    @Column( name="data_criacao" )
    private LocalDateTime creationDate;

    @Column( name = "resposta_cidadao" )
    private String userReply;

    @Column( name = "descricao", nullable = false )
    private String description;

    @ManyToOne
    @JoinColumn( name = "autor", nullable = false )
    private PubAgent agent;

    @ManyToOne
    @JoinColumn( name = "ocorrencia", nullable = false )
    private Call call;

    @Column( name = "protocolo", nullable = false, unique = true )
    private String protocol;
}