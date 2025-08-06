package io.github.gxrj.janitory.data.providers;

import static com.android.volley.Request.Method;
import static com.android.volley.Response.Listener;
import static com.android.volley.Response.ErrorListener;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;

import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.Locale;

public class WebClient {

    /**
     * Performs an http call to the server
     * @param httpMethod GET | POST
     * @param url refers to the server endpoint
     * @param data refers to the request body
     * @param context refers to the callers context
     * @param onSuccess define what actions must be performed on success
     * @param onError define what actions must be performed on error
     */
    public static void callApi(
            String httpMethod, String url,
            JSONObject data, Context context,
            Listener<JSONObject> onSuccess, ErrorListener onError ) {

        int method = checkHttpMethod( httpMethod );

        JsonObjectRequest request = buildRequest( method, url, data, onSuccess, onError );
        RequestQueue queue = Volley.newRequestQueue( context );
        queue.add( request );
    }

    private static JsonObjectRequest buildRequest(
            int method, String url, JSONObject data,
            Listener<JSONObject> onSuccess, ErrorListener onError ) {

        return new JsonObjectRequest( method, url, data, onSuccess, onError );
    }

    private static int checkHttpMethod( String httpMethod ) {
        httpMethod = httpMethod.toLowerCase();
        return httpMethod.equals( "post" ) ? Method.POST : Method.GET;
    }

}
