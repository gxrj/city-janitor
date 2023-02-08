package io.github.gxrj.janitory.core.Address;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter

@Embeddable
public class Address implements Serializable {
 
    @Column( name = "cep" )
    private int zipCode;

    @Column( name = "bairro" )
    private String district;

    @Column( name = "logradouro" )
    private String pubPlace;

    @Column( name = "referencia" )
    private String reference;
}