package io.github.gxrj.janitory.domain.models;

import io.github.gxrj.janitory.domain.builders.AddressBuilder;
import io.github.gxrj.janitory.domain.exceptions.NotValidAddressException;
import org.json.JSONException;
import org.json.JSONObject;

public class Address {

    private String zipCode;
    private String pubPlace;
    private String number;
    private String reference;
    private String district;

    public static Address fromJsonObject( JSONObject json ) throws JSONException {
        return new AddressBuilder()
                .zipCode( json.getString( "zip_code" ) )
                .pubPlace( json.getString( "pub_place" ) )
                .number( json.getString( "number" ) )
                .reference( json.getString( "reference" ) )
                .district( json.getString( "district" ) )
                .build();
    }

    public String toPlanJson() throws NotValidAddressException {

        pubPlace = pubPlace == null ? "" : pubPlace;

        if( pubPlace.isBlank() || district.equals( "Bairro" ) )
            throw new NotValidAddressException( "Campos \"logradouro\" e \"bairro\" são obrigatórios" );

        return "{" +
                "\"district\":\""+ district +"\","+
                "\"pub_place\":\""+ pubPlace +"\","+
                checkNumber()+
                checkReference()+
                checkZipCode()+
                "}";
    }

    private String checkZipCode() {
        return zipCode != null ? "\"zip_code\":\""+ zipCode +"\"," : "";
    }

    private String checkNumber() {
        return number != null ? "\"number\":\""+ number +"\"," : "s/n";
    }

    private String checkReference() {
        return reference != null ? "\"reference\":\""+ reference +"\"," : "";
    }

    public void setZipCode( String zipCode ) { this.zipCode = zipCode; }
    public void setPubPlace( String pubPlace ) { this.pubPlace = pubPlace; }
    public void setNumber( String number ) { this.number = number; }
    public void setDistrict( String district ) { this.district = district; }
    public void setReference( String reference ) { this.reference = reference; }

}
