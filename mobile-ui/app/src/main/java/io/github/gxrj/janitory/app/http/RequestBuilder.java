package io.github.gxrj.janitory.app.http;

import java.util.Map;

import okhttp3.Request;
import okhttp3.RequestBody;

public class RequestBuilder {
     static Request build(
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
