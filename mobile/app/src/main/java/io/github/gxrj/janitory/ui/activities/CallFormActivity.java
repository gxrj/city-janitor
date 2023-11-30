package io.github.gxrj.janitory.ui.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.github.gxrj.janitory.R;
import io.github.gxrj.janitory.domain.models.District;

public class CallFormActivity extends AppCompatActivity {

    private static List<District> districts = new ArrayList<>();
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_call_form );
        render();
        setListeners();
    }

    private void render() {
        String plainDuty = getData().getString( "duty" );
        String plainDistricts = getData().getString( "districts" );

        EditText dutyFormField = findViewById( R.id.duty_form_field );

        try{
            JSONObject json = new JSONObject( plainDuty );
            districts = District.fromJsonArray( new JSONArray( plainDistricts ) );
            dutyFormField.setHint( json.getString( "name" ) );
        }
        catch( JSONException e ) {
            Log.e( "error", "JSONException at CallFormActivity render" );
        }
    }

    private Bundle getData() {
        return getIntent().getExtras();
    }

    private void setListeners() {
        Button backBtn = findViewById( R.id.back_btn );
        backBtn.setOnClickListener( view -> finish() );

        AutoCompleteTextView districtDropdownList = findViewById( R.id.district_list_container );

        ArrayAdapter<District> adapter =
                new ArrayAdapter<>( this, R.layout.item_districts, districts );
        districtDropdownList.setAdapter( adapter );
    }
}
