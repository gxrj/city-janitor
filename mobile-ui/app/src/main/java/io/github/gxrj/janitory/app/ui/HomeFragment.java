package io.github.gxrj.janitory.app.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    private TextView textView;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstance ) {

        var view = inflater.inflate( R.layout.fragment_home, container, false );
        textView = view.findViewById( R.id.homeText );

        try{
            fetchData();
        }
        catch ( Exception ex ) {
            Toast.makeText( getActivity(), ex.getMessage(), Toast.LENGTH_LONG ).show();
        }

        return view;
    }

    private void fetchData() throws Exception {

        var params = new HashMap<String, String>();
        params.put( "url", "http://10.0.2.2:8080/anonymous/category/all" );
        params.put( "method", "GET" );
        params.put( "content-type", "application/json" );

        Client.builder()
            .onResponse(
                ( call, resp ) -> {
                    if( resp.isSuccessful() && resp.body() != null ) {
                        try{
                            /* To do: Add a keypair json even for array results spilled from
                             * the api*/
                            var jsonArray = new JSONArray( resp.body().source().readUtf8() );
                            setTextView( jsonArray.toString() );
                        } catch ( IOException | JSONException ex ) {
                            setTextView( "Parsing error! "+ ex.getMessage() );
                        }
                    }
                    if( !resp.isSuccessful() )
                        setTextView( String.format( "Http status: %s", resp.code() ) );
                    if( resp.body() == null )
                        setTextView( "Null response body" );
                }
            )
            .onFailure(
                ( call, error ) ->
                    textView.setText( String.format( "Error: %s", error.getMessage() ) )
            )
            .sendMessage( params );
    }

    private void setTextView( String result ) {
        textView.setText( result );
    }
}
