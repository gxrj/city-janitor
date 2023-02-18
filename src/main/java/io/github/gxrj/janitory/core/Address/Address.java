package io.github.gxrj.janitory.core.Address;

import java.io.Serializable;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

@JsonNaming( PropertyNamingStrategies.SnakeCaseStrategy.class )

@Embeddable
public class Address implements Serializable {
 
    @Column( name = "cep", length = 8 )
    private String zipCode;

    @ManyToOne
    @JoinColumn( name = "bairro" )
    private District district;

    @Column( name = "logradouro" )
    private String pubPlace;

    @Column( name = "referencia" )
    private String reference;
}