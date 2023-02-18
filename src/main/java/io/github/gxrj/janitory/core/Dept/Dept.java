package io.github.gxrj.janitory.core.Dept;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data @NoArgsConstructor @AllArgsConstructor

@Entity( name = "Secretaria" )
public class Dept implements Serializable {
    
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO ) 
    private Long id;

    @NotBlank
    @Column( name = "nome", length = 40, nullable = false )
    private String name;
}
