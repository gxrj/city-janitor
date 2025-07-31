package io.github.gxrj.janitory.domain.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Citizen {

    private final String email;
    private final String name;

    public Citizen( String email, String name ) {
        this.email = email;
        this.name = name;
    }

    public static Citizen fromJSONObject( JSONObject json ) throws JSONException {
        return new Citizen( json.getString( "email" ), json.getString( "name ") );
    }

    public String toPlainJson() {
        return "{\"email\":\""+ email +"\",\"name\":\""+ name +"\"}";
    }
}
