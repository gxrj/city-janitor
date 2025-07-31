package io.github.gxrj.janitory.ui.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts.GetContent;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import io.github.gxrj.janitory.R;
import io.github.gxrj.janitory.domain.models.District;

public class CallFormActivity extends AppCompatActivity {

    private static List<District> districts = new ArrayList<>();
    Button backBtn, addImageBtn, removeImageBtn, sendFormBtn;
    AutoCompleteTextView districtDropdownList;
    ImageView imageView;
    ActivityResultLauncher<String> photoPickerActivity;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_call_form );
        registerPhotoPickerActivity(); // In case of errors try putting above super.onCreate() instruction
        bindComponents();
        setListeners();
    }

    private void bindComponents() {
        backBtn = findViewById( R.id.back_btn );
        districtDropdownList = findViewById( R.id.districts );
        imageView = findViewById( R.id.image_view );
        addImageBtn = findViewById( R.id.add_image_btn );
        removeImageBtn = findViewById( R.id.remove_image_btn );
        sendFormBtn = findViewById( R.id.send_form_btn );
        bindDistrictList();
    }

    private void bindDistrictList() {
        String plainDuty = getData().getString( "duty" );
        String plainDistricts = getData().getString( "districts" );

        EditText dutyFormField = findViewById( R.id.duty_form_field );

        try{
            JSONObject json = new JSONObject( plainDuty );
            districts = District.fromJsonArray( new JSONArray( plainDistricts ) );
            dutyFormField.setText( json.getString( "name" ) );
        }
        catch( JSONException e ) {
            Log.e( "error", "JSONException at CallFormActivity render" );
        }
    }

    private void setListeners() {

        backBtn.setOnClickListener( view -> finish() );

        ArrayAdapter<District> adapter =
                new ArrayAdapter<>( this, R.layout.item_districts, districts ); // Todo change to popup menu
        districtDropdownList.setAdapter( adapter ); // Todo change to popup menu

        addImageBtn.setOnClickListener( view -> photoPickerActivity.launch( "image/*" ) );

        removeImageBtn.setOnClickListener( view -> removeImage() );

        sendFormBtn.setOnClickListener( view -> sendForm() );
    }

    /**
     *  PhotoPicker activity must be unconditionally registered every time your activity is created
     */
    private void registerPhotoPickerActivity() {
        GetContent contract = new GetContent();
        photoPickerActivity = registerForActivityResult( contract, this::invokeFileChooser );
    }

    private void invokeFileChooser( Uri uri ) {

        if( uri == null ) return;

        try {
            
            InputStream is = getContentResolver()
                    .openInputStream( uri );

            setImageContent( is );
        }
        catch( FileNotFoundException ex ) {
            Log.e( "Error", ex.getMessage() );
        }

        addImageBtn.setVisibility( View.GONE );
        removeImageBtn.setVisibility( View.VISIBLE );
    }

    private Bundle getData() {
        return getIntent().getExtras();
    }

    private void setImageContent( InputStream is ) {

        Bitmap decodedFile = BitmapFactory.decodeStream( is );
        int visibility = decodedFile != null ? View.VISIBLE : View.GONE;

        imageView.setVisibility( visibility );
        imageView.setImageBitmap( decodedFile );
    }

    private void removeImage() {
        setImageContent( null );
        addImageBtn.setVisibility( View.VISIBLE );
        removeImageBtn.setVisibility( View.GONE );
    }

    private void sendForm() {
        String requestBody;
        //Todo: start automated tests and ui tests
    }
}
