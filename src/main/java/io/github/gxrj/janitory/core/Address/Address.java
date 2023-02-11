package io.github.gxrj.janitory.core.Address;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
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