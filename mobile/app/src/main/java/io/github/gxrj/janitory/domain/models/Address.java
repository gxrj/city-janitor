package io.github.gxrj.janitory.domain.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Address {

    private String zipCode;
    private String pubPlace;
    private String reference;
    private District district;

    public static Address fromJsonObject( JSONObject json ) throws JSONException {
        Address a = new Address();

        a.zipCode = json.getString( "zip_code" );
        a.pubPlace = json.getString( "pub_place" );
        a.reference = json.getString( "reference" );
        JSONObject districtJson = json.getJSONObject( "district" );

        a.district = new District( districtJson.getString("name") );

        return a;
    }

    public String toPlanJson() {
        return "{" +
                "\"zip_code\":\""+ zipCode +"\","+
                "\"pub_place\":\""+ pubPlace +"\","+
                "\"reference\":\""+ reference +"\","+
                "\"district\":\""+ district +"\""+
                "}";
    }
}
