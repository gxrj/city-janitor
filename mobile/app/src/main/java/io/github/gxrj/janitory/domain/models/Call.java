package io.github.gxrj.janitory.domain.models;

import android.graphics.Bitmap;
import io.github.gxrj.janitory.utils.ImageParser;
import org.json.JSONException;
import org.json.JSONObject;

public class Call {

    private String status;
    private Citizen author;
    private Address address;
    private Duty duty;
    private String description;
    private String protocol;
    private Bitmap image;

     public Call fromJsonObject( JSONObject json ) throws JSONException {
         return new Call.Builder()
                 .status( json.getString( "staus" ) )
                 .author( Citizen.fromJSONObject( json.getJSONObject( "author" ) ) )
                 .address( Address.fromJsonObject( json.getJSONObject( "address" ) ) )
                 .duty( Duty.fromJsonObject(  json.getJSONObject( "duty" ) ) )
                 .description( json.getString( "description" ) )
                 .protocol( json.getString( "protocol" ) )
                 .image( ImageParser.toBitmap( json.getString( "image" ) ) )
                 .build();
     }

    public static class Builder {
        private final Call call;

        public Builder() {
            call = new Call();
        }

        public Builder status( String status ) {
            call.status = status;
            return this;
        }

        public Builder author( Citizen author ) {
            call.author = author;
            return this;
        }

        public Builder address( Address address ) {
            call.address = address;
            return this;
        }

        public Builder duty( Duty duty ) {
            call.duty = duty;
            return this;
        }

        public Builder description( String description ) {
            call.description = description;
            return this;
        }

        public Builder protocol( String protocol ) {
            call.protocol = protocol;
            return this;
        }

        public Builder image( Bitmap image ) {
            call.image = image;
            return this;
        }

        public Call build() {
            return call;
        }
    }
}
