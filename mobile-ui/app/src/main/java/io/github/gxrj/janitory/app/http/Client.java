package io.github.gxrj.janitory.app.http;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.util.Map;
import java.util.function.BiConsumer;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Client implements Callback {

    private static final OkHttpClient client = new OkHttpClient();
    private BiConsumer<Call, IOException> onFailureClbk;
    private BiConsumer<Call, Response> onResponseClbk;

    Client() {}

    public static Client builder() {
        return new Client();
    }

    public void sendMessage( Map<String, String> params ) throws Exception {
        sendMessage( params, null );
    }

    public void sendMessage(
            Map<String, String> params, RequestBody requestBody ) throws Exception {
        checkCallbacks();
        var request = RequestBuilder.build( params, requestBody );
        client.newCall( request )
                .enqueue( this );
    }

    public Client onFailure( BiConsumer<Call, IOException> onFailureClbk ) {
        this.onFailureClbk = onFailureClbk;
        return this;
    }

    public Client onResponse( BiConsumer<Call, Response> onResponseClbk ) {
        this.onResponseClbk = onResponseClbk;
        return this;
    }

    @Override
    public void onFailure( @NonNull Call call, @NonNull  IOException ex ) {
        onFailureClbk.accept( call, ex );
    }

    @Override
    public void onResponse(
            @NonNull Call call, @NonNull Response response ) {
        onResponseClbk.accept( call, response );
    }

    /**
     * Sets a function that does nothing for each null callback.
     * */
    private void checkCallbacks() {
        if( onFailureClbk == null ) onFailureClbk = ( call, error ) -> {};
        if( onResponseClbk == null ) onResponseClbk = ( call, response ) -> {};
    }
}
