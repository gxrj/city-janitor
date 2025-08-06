package io.github.gxrj.janitory.domain.builders;

import io.github.gxrj.janitory.domain.models.Address;

public class AddressBuilder {

    private final Address address;

    public AddressBuilder() {
        address = new Address();
    }

    public AddressBuilder zipCode( String zipCode ) {
        address.setZipCode( zipCode );
        return this;
    }

    public AddressBuilder pubPlace( String pubPlace ) {
        address.setPubPlace( pubPlace );
        return this;
    }

    public AddressBuilder number( String number ) {
        address.setNumber( number );
        return this;
    }

    public AddressBuilder district( String district ) {
        address.setDistrict( district );
        return this;
    }

    public AddressBuilder reference( String reference ) {
        address.setReference( reference );
        return this;
    }

    public Address build() { return address; }
}
