package io.github.gxrj.janitory.app.http;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class Client {

    private static final OkHttpClient client = new OkHttpClient();

    public static JSONObject sendMessage( Map<String, String> params ) throws Exception {
        return sendMessage( params, null );
    }

    public static JSONObject sendMessage(
            Map<String, String> params, RequestBody requestBody ) throws Exception {

        var request = buildRequest( params, requestBody );

        try ( var response = client.newCall( request ).execute() ) {
            if( !response.isSuccessful() ) throw new IOException( "Error: " + response );
            return response.body() != null ?
                        new JSONObject( response.body().toString() ) : null ;
        }
    }

    private static Request buildRequest(
            Map<String, String> params, RequestBody requestBody ) throws Exception {

        var url = params.get( "url" );

        if( url == null )
            throw new Exception( "URL must be specified!" );

        var builder = new Request.Builder();
        var method = params.get( "method" );
        var accept = params.get( "accept" );
        var contentType = params.get( "content-type" );
        var authorization = params.get( "authorization" );

        if( accept == null ) accept = "*/*";
        if( method == null ) method = "GET";

        builder.method( method, requestBody );
        builder.addHeader( "Accept", accept );

        if( contentType != null )
            builder.addHeader( "Content-Type", contentType );
        if( authorization != null )
            builder.addHeader( "Authorization", authorization );

        builder.url( url );

        return builder.build();
    }
}
