package io.github.gxrj.janitory.domain.models;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;

public class Duty {

    private final Long id;
    private final String name;

    public Duty( Long id, String name ) {
        this.id = id;
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
        return new Duty( json.getLong( "id" ), json.getString( "name" ) );
    }

    public static JSONArray fromListToJsonArray( List<Duty> list ) throws JSONException {
        BinaryOperator<String> accumulator = ( partialString, el ) ->
                                                partialString.equals( "" ) ?
                                                        el : partialString + "," + el;
        String plainJson = list.stream()
                                .map( el -> "{\"id\":"+el.id+",\"name\":\""+el.name+"\"}" )
                                .reduce( "", accumulator );

        return new JSONArray( "["+ plainJson + "]" );
    }

    @NonNull
    public String toString(){
        return name;
    }
}
