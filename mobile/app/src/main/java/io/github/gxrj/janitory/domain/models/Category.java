package io.github.gxrj.janitory.domain.models;

import android.util.Log;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private Long id;
    private String name;

    private List<Duty> duties;

    public Long getId() {
        return id;
    }

    public List<Duty> getDuties() {
        return duties;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public void setDuties( List<Duty> duties ) {
        this.duties = duties;
    }

    public static List<Category> fromJsonArray( JSONArray array ) throws JSONException  {
        List<Category> list = new ArrayList<>();

        if( array != null )
            for( int i = 0; i < array.length(); i++ ) {
                JSONObject item = array.getJSONObject( i );
                list.add( Category.fromJsonObject( item ) );
            }

        return list;
    }

    public static Category fromJsonObject( JSONObject json ) throws JSONException {
        Category c = new Category();
        c.setId( json.getLong( "id" ) );
        c.setName( json.getString( "name" ) );
        c.setDuties( Duty.fromJsonArray( json.getJSONArray( "duties" ) ) );
        return c;
    }

    public static JSONObject toJsonObject( Category c ) throws JSONException {
        String plainJson = "{ \"id\":" + c.getId() +
                            ",\"name\":\"" + c +
                            "\",\"duties\":" + Duty.fromListToString( c.getDuties() ) + "}";
        Log.d( "category", plainJson );
        return new JSONObject( plainJson );
    }

    public static Category fromJsonString( @NonNull String plainJson ) throws JSONException {
        JSONObject json = new JSONObject( plainJson );
        return Category.fromJsonObject( json );
    }

    @NonNull
    public String toString() {
        return name;
    }
}
