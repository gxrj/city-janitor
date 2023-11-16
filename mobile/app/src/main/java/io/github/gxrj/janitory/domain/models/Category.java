package io.github.gxrj.janitory.domain.models;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public static List<Category> fromJsonArray( JSONArray array ) throws JSONException  {
        List<Category> list = new ArrayList<>();

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
        return c;
    }

    @NonNull
    public String toString() {
        return name;
    }
}
