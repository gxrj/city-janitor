package io.github.gxrj.janitory.app.ui;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.HashMap;

import io.github.gxrj.janitory.app.R;
import io.github.gxrj.janitory.app.http.Client;

public class HomeFragment extends Fragment  {
    //Todo: fix this junky code
    private TextView messageLogger;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstance ) {

        var view = inflater.inflate( R.layout.fragment_home, container, false );
        var listLayout = ( LinearLayout ) view.findViewById( R.id.homeCategoriesList );

        buildMessageLogger( listLayout );
        try {
            fetchData( listLayout );
        }
        catch ( Exception ex ) {
            Toast.makeText( getActivity(), ex.getMessage(), Toast.LENGTH_LONG ).show();
        }
        return view;
    }

    private void buildMessageLogger( ViewGroup listLayout ) {
        messageLogger = new TextView( getContext() );
        messageLogger.setLayoutParams(
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT ) );
        messageLogger.setGravity( Gravity.CENTER );
        listLayout.addView( messageLogger );
    }

    private void fetchData( ViewGroup listLayout ) throws Exception {

        var params = new HashMap<String, String>();
        params.put( "url", "http://10.0.2.2:8080/anonymous/category/all" );
        params.put( "method", "GET" );
        params.put( "content-type", "application/json" );

        Client.builder()
            .onResponse(
                ( call, resp ) -> {
                    if( resp.isSuccessful() && resp.body() != null ) {
                        try{
                            /* Todo: Add a keypair json even for array results spilled from the api*/
                            var categories = new JSONArray( resp.body().source().readUtf8() );
                            listLayout.post( () ->  messageLogger.setText( categories.toString() ) );
                        } catch ( IOException | JSONException ex ) {
                            messageLogger
                                .setText( String.format( "Parsing error: %s",ex.getMessage() ) );
                        }
                        catch ( Exception ex ) {
                            messageLogger
                                    .setText( String.format( "General error: %s",ex.getMessage() ) );
                        }
                    }
                    if( !resp.isSuccessful() )
                        messageLogger.setText( String.format( "Http status: %s", resp.code() ) );
                    if( resp.body() == null )
                        messageLogger.setText( "Null response body" );
                }
            )
            .onFailure(
                ( call, error ) ->
                    messageLogger.setText( String.format( "Error: %s", error.getMessage() ) )
            )
            .sendMessage( params );
    }
}
