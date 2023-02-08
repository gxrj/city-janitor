package io.github.gxrj.janitory.core.Call;

import java.util.UUID;
import java.util.List;

import io.github.gxrj.janitory.core.Action.Action;
import io.github.gxrj.janitory.core.Address.Address;
import io.github.gxrj.janitory.core.Category.Category;
import io.github.gxrj.janitory.core.Citizen.Citizen;
import io.github.gxrj.janitory.core.Dept.Dept;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
public class Call {
 
    @Id
    @Column( columnDefinition = "uuid not null" )
    @GeneratedValue( strategy = GenerationType.AUTO )
    private UUID id;

    @Column( name = "usuario" )
    private Citizen author;

    @Embedded
    private Address address;

    @Column( name = "destino" )
    private Dept destination;

    @Column( name = "categoria_servico" )
    private Category category;

    @Column( name = "descricao" )
    private String description;

    @OneToMany
    private List<Action> actions;
}
