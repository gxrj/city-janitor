package io.github.gxrj.janitory.core.Dept;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity( name = "Secretaria" )
public class Dept implements Serializable {
    
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO ) 
    private Long id;

    @NotEmpty
    @Column( name = "nome", length = 40, nullable = false )
    private String name;
}
