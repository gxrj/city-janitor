package io.github.gxrj.janitory.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import io.github.gxrj.janitory.R;
import io.github.gxrj.janitory.data.providers.WebClient;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState) ;
        setContentView( R.layout.activity_main );

        setListeners();
    }

    private void setListeners() {
        Button anonymousBtn = findViewById( R.id.anonymous_btn );
        Button authenticatedBtn = findViewById( R.id.authenticated_btn );
        anonymousBtn.setOnClickListener( listener -> fakeCategories() );
    }

    private void fakeCategories() {
        StringBuilder builder = new StringBuilder()
                .append( "[ { \"id\": 0, \"name\": \"Agua pluvial, bueiros e esgoto\" }" )
                .append( ",{ \"id\": 1, \"name\": \"Iluminacao e energia\" }" )
                .append( ",{ \"id\": 2, \"name\": \"Irregularidades\" }" )
                .append( ",{ \"id\": 3, \"name\": \"Limpeza e conservacao\" } ]" );
        String json = builder.toString();
        proceedToCategoriesActivity( json );
    }

    private void fetchCategories() {
        WebClient.fetchData(
                            this,
                            "TODO: put categories endpoint here",
                            null,
                            json -> proceedToCategoriesActivity( json.toString() ),
                            error -> Log.e( "error", error.toString() ) );
    }

    private void proceedToCategoriesActivity( String json ) {
        Intent categoriesActivity = new Intent( this, CategoriesActivity.class );
        categoriesActivity.putExtra( "data", json );
        startActivity( categoriesActivity );
    }
}