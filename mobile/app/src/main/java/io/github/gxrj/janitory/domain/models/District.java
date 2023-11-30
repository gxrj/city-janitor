package io.github.gxrj.janitory.domain.models;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class District {

    private Long id;

    private final String name;

    public District( Long id, String name ) {
        this.id = id;
        this.name = name;
    }

    public static List<District> fromJsonArray( JSONArray districts ) throws JSONException {
       List<District> list = new ArrayList<>();
       for( int i = 0; i < districts.length(); i++ ) {
           JSONObject obj = districts.getJSONObject( i );
           District d = new District(
                   obj.getLong( "id" ),
                   obj.getString( "name" ) );

           list.add( d );
       }
       return list;
    }

    public String toPlainJson() {
        return "{ \"id\": " + id + ", \"name\": \"" + name +"\" }";
    }

    public Long getId() { return id; }

    @NonNull
    public String toString() {
        return name;
    }
}
