package io.github.gxrj.janitory.domain.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Duty {

    private Long id;
    private String name;

    public Duty( Long id, String name ) {
        this.id = id;
        this.name = name;
    }

    public static List<Duty> fromJsonArray( JSONArray array ) throws JSONException {
        List<Duty> list =  new ArrayList<>();

        for( int i = 0; i < array.length(); i++ ) {
            JSONObject item = array.getJSONObject( i );
            list.add( Duty.fromJsonObject( item ) );
        }

        return list;
    }

    public static Duty fromJsonObject( JSONObject json ) throws JSONException {
        return new Duty( json.getLong( "id" ), json.getString( "name" ) );
    }
}
