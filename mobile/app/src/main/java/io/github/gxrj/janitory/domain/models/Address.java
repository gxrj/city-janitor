package io.github.gxrj.janitory.domain.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Address {

    private String zipCode;
    private String pubPlace;
    private String reference;
    private District district;

    public static Address fromJsonObject( JSONObject json ) throws JSONException {
        Address address = new Address();

        address.setZipCode( json.getString( "zip_code" ) );
        address.setPubPlace( json.getString( "pub_place" ) );
        address.setReference( json.getString( "reference" ) );
        JSONObject districtJson = json.getJSONObject( "district" );

        address.setDistrict(
                districtJson.getString("name"), districtJson.getLong("id") );

        return address;
    }

    public static String toPlanJson( Address address ) {
        return "{" +
                "\"zip_code\":"+ address.getZipCode() +","+
                "\"pub_place\":"+ address.getPubPlace() +","+
                "\"reference\":"+ address.getReference() +","+
                "\"district\":"+ address.getDistrict().toPlainJson()+
                "}";
    }

    public String getZipCode() { return zipCode; }
    public String getPubPlace() { return pubPlace; }
    public String getReference() { return reference; }
    public District getDistrict() { return district; }

    public void setZipCode( String zipCode ) { this.zipCode = zipCode; }
    public void setPubPlace( String pubPlace ) { this.pubPlace = pubPlace; }
    public void setReference( String reference ) { this.reference = reference; }
    public void setDistrict( String name, Long id ) { this.district = new District( id, name ); }

}
