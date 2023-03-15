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

    private TextView messageLogger;
    private JSONArray categories;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstance ) {

        var view = inflater.inflate( R.layout.fragment_home, container, false );
        var listLayout = ( LinearLayout ) view.findViewById( R.id.homeCategoriesList );

        buildMessageLogger( listLayout );
        buildButtonList( listLayout );

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

    private void buildButtonList( ViewGroup listLayout ) {
        try {
            fetchData();
            /* Todo: Solve the access to categories attributes before it's complete.
            var row = buildRow();

            for(  int i = 0; i < categories.length(); i++ ) {
                var btn = buildButton( categories.getJSONObject( i ).toString() );
                row.addView( btn );
                // flushes the row if its the last element
                if( i + 1 == categories.length() ) listLayout.addView( row );
                // otherwise if the row have 3 buttons, flushes the same
                // row and creates a new one for the remaining elements
                else if( row.getChildCount() == 3 ) {
                    listLayout.addView( row );
                    row = buildRow();
                }
            }*/
        }
        catch ( Exception ex ) {
            Toast.makeText( getActivity(), ex.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }

    private Button buildButton( String btnLabel ) {
        var btn = new Button( getContext() );
        var btnParams = new LinearLayout
                .LayoutParams( 0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f );
        btnParams.setMargins( 5, 5, 5, 5 );
        btn.setText( btnLabel );
        btn.setLayoutParams( btnParams );
        btn.setGravity( Gravity.CENTER );
        // Todo: Add event listener
        return btn;
    }

    private LinearLayout buildRow() {
        var row = new LinearLayout( getContext() );
        var rowParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 1.0f );
        row.setLayoutParams( rowParams );
        row.setOrientation( LinearLayout.HORIZONTAL );
        return row;
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
                            /* Todo: Add a keypair json even for array results spilled from the api*/
                            categories = new JSONArray( resp.body().source().readUtf8() );
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
