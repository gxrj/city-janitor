package io.github.gxrj.janitory.domain.models;

import android.graphics.Bitmap;
import io.github.gxrj.janitory.utils.ImageParser;
import org.json.JSONException;
import org.json.JSONObject;

import io.github.gxrj.janitory.domain.builders.CallBuilder;

public class Call {

    private String status;
    private Citizen author;
    private Address address;
    private String duty;
    private String description;
    private String protocol;
    private Bitmap image;

    public static Call fromJsonObject( JSONObject json ) throws JSONException {
        return new CallBuilder()
                .status( json.getString( "staus" ) )
                .author( Citizen.fromJSONObject( json.getJSONObject( "author" ) ) )
                .address( Address.fromJsonObject( json.getJSONObject( "address" ) ) )
                .duty( json.getString( "duty" ) )
                .description( json.getString( "description" ) )
                .protocol( json.getString( "protocol" ) )
                .image( ImageParser.toBitmap( json.getString( "image" ) ) )
                .build();
    }
    public String toPlainJson() {

        return "{\"duty\":\""+ duty +"\"," +
                "\"address\":"+ address.toPlanJson() +"," +
                "\"description\":\""+ description +"\"," +
                checkStatus() +
                checkAuthor() +
                checkProtocol() +
                checkImage() +"}";
    }

    private String checkStatus() {
        return status != null ? "\"status\": \""+ status +"\"," : "";
    }

    private String checkAuthor() {
        return author != null ? "\"author\":"+ author.toPlainJson() +",": "";
    }

    private String checkProtocol() {
        return protocol != null ? "\"protocol\":\""+ protocol +"\"," : "";
    }

    private String checkImage() {
        return image != null ? "\"image\":\""+ ImageParser.stringify( image ) +"\"" : "";
    }

    public void setStatus( String status ) { this.status = status; }
    public void setAuthor( Citizen author ) { this.author = author; }
    public void setAddress( Address address ) { this.address = address; }
    public void setDuty( String duty ) { this.duty = duty; }
    public void setDescription( String description ) { this.description = description; }
    public void setProtocol( String protocol ) { this.protocol = protocol; }
    public void setImage( Bitmap image ) { this.image = image; }
}
