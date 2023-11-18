package io.github.gxrj.janitory.ui.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import io.github.gxrj.janitory.R;
import io.github.gxrj.janitory.domain.models.Category;
import io.github.gxrj.janitory.domain.models.Duty;

public class DutiesActivity  extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_duties );
        render();
        setListeners();
    }

    private void render() {
        TextView tv = findViewById( R.id.category_selected );
        String plainCategory = getData().getString( "category" );

        try {
            Category c = Category.fromJsonString( plainCategory );
            tv.setText( c.toString() );
            populateList( c.getDuties() );
        }
        catch( JSONException e) {
            Log.e( "error", "JSONException at duty list initialization" );
        }
    }

    private Bundle getData() {
        return getIntent().getExtras();
    }

    private void populateList( List<Duty> list ) throws JSONException {
        ListView listView = findViewById( R.id.duties );

        ArrayAdapter<Duty> adapter =
                new ArrayAdapter<>( this, R.layout.duties_item, R.id.duty_name, list );
        listView.setAdapter( adapter );
    }

    private void setListeners() {
        Button backBtn = findViewById( R.id.back_btn );
        backBtn.setOnClickListener( view -> finish() );
    }
}
