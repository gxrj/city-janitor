package io.github.gxrj.janitory.domain.models;

import io.github.gxrj.janitory.domain.builders.AddressBuilder;
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

    public String toPlanJson() {
        return "{" +
                "\"zip_code\":\""+ zipCode +"\","+
                "\"pub_place\":\""+ pubPlace +"\","+
                "\"number\":\""+ number +"\","+
                "\"reference\":\""+ reference +"\","+
                "\"district\":\""+ district +"\""+
                "}";
    }

    public void setZipCode( String zipCode ) { this.zipCode = zipCode; }
    public void setPubPlace( String pubPlace ) { this.pubPlace = pubPlace; }
    public void setNumber( String number ) { this.number = number; }
    public void setDistrict( String district ) { this.district = district; }
    public void setReference( String reference ) { this.reference = reference; }

}
