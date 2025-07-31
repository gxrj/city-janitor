package io.github.gxrj.janitory.domain.models;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;

public class Duty {

    private final String name;

    public Duty( String name ) {
        this.name = name;
    }

    public static List<Duty> fromJsonArray( JSONArray array ) throws JSONException {
        List<Duty> list =  new ArrayList<>();

        if( array != null )
            for( int i = 0; i < array.length(); i++ ) {
                JSONObject item = array.getJSONObject( i );
                list.add( Duty.fromJsonObject( item ) );
            }

        return list;
    }

    public static Duty fromJsonObject( JSONObject json ) throws JSONException {
        return new Duty( json.getString( "name" ) );
    }

    public static String fromListToString( List<Duty> list ) {
        BinaryOperator<String> accumulator = ( partialString, el ) ->
                partialString.isEmpty() ?
                        el : partialString + "," + el;
        String plainJson = list.stream()
                .map( el -> "{\"name\":\""+el.name+"\"}" )
                .reduce( "", accumulator );

        return "["+ plainJson + "]";
    }

    public static String toPlainJson( Duty d, Category c ) {
        return "{\"name\":\""+d.name+"\",\"category\":\""+c+"\"}";
    }

    @NonNull
    public String toString(){
        return name;
    }
}
