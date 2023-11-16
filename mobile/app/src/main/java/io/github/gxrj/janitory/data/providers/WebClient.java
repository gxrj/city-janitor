package io.github.gxrj.janitory.data.providers;

import static com.android.volley.Request.Method;
import static com.android.volley.Response.Listener;
import static com.android.volley.Response.ErrorListener;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;

import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class WebClient {

    public static void fetchData(
            Context context, String url, JSONObject data,
            Listener<JSONObject> onSuccess, ErrorListener onError ) {

        JsonObjectRequest request = buildRequest( Method.GET, url, data, onSuccess, onError );
        RequestQueue queue = Volley.newRequestQueue( context );
        queue.add( request );
    }

    private static JsonObjectRequest buildRequest(
            int method, String url, JSONObject data,
            Listener<JSONObject> onSuccess, ErrorListener onError ) {

        return new JsonObjectRequest( method, url, data, onSuccess, onError );
    }
}
