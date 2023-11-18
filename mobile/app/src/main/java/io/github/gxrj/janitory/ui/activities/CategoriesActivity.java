package io.github.gxrj.janitory.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.github.gxrj.janitory.R;
import io.github.gxrj.janitory.domain.models.Category;
import io.github.gxrj.janitory.domain.models.Duty;

public class CategoriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_categories );
        render();
        setListeners();
    }

    private void render() {
        String plainJson = getData().getString( "data" );

        try {
            JSONArray array = new JSONArray( plainJson );
            populateList( array );
        } catch ( JSONException e ) {
            Log.e( "error", "JSONException at Categories list initialization"  );
        }
    }

    private Bundle getData() {
        return getIntent().getExtras();
    }

    private void populateList( JSONArray array ) throws JSONException {
        ListView listView = findViewById( R.id.categories );
        List<Category> list = Category.fromJsonArray( array );

        ArrayAdapter<Category> adapter =
                new ArrayAdapter<>(
                        this, R.layout.categories_item, R.id.category_name, list );
        listView.setAdapter( adapter );
    }

    private void setListeners() {
        Button backBtn = findViewById( R.id.back_btn );
        backBtn.setOnClickListener( listener -> finish() );

        ListView lv = findViewById( R.id.categories );
        lv.setOnItemClickListener(
                ( parent, view, position, id )  -> proceedToDutiesActivity( parent, position ) );
    }

    private void proceedToDutiesActivity( AdapterView<?> parent, int position ) {
        Category category = ( Category ) parent.getItemAtPosition( position );
        Intent dutiesActivity = new Intent( this, DutiesActivity.class );

        try {
            String categoryJson = Category.toJsonObject( category ).toString();
            dutiesActivity.putExtra( "category", categoryJson );
            startActivity( dutiesActivity );
        }
        catch( JSONException e ) {
            Log.e( "error", "JSONException at Duties list conversion to JsonArray" );
        }
    }
}