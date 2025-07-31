package io.github.gxrj.janitory.domain.models;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Category {

    private String name;

    private List<Duty> duties;

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
        c.name = json.getString( "name" );
        c.duties = Duty.fromJsonArray( json.getJSONArray( "duties" ) );
        return c;
    }

    public static Category fromJsonString( String plainJson ) throws JSONException {
        JSONObject json = new JSONObject( plainJson );
        return Category.fromJsonObject( json );
    }

    public String toPlainJson() {
        return "{ \"name\":\"" + name +
                "\",\"duties\":" + Duty.fromListToString( duties ) + "}";

    }

    public List<Duty> getDuties() {
        return duties;
    }

    @NonNull
    public String toString() {
        return name;
    }
}
