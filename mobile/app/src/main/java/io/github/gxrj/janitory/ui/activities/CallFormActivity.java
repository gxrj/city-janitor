package io.github.gxrj.janitory.ui.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

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

    Button addImageBtn, removeImageBtn;
    ImageView imageView;

    ActivityResultLauncher<String> photoPickerActivity;

    private static List<District> districts = new ArrayList<>();
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_call_form );
        render();
        registerPhotoPickerActivity(); // In case of errors try put above super.onCreate() instruction
        setListeners();
    }

    /**
     *  PhotoPicker activity must be unconditionally registered every time your activity is created
     */
    private void registerPhotoPickerActivity() {
        GetContent contract = new GetContent();
        photoPickerActivity = registerForActivityResult( contract, this::invokeFileChooser );
    }

    private void invokeFileChooser( Uri uri ) {

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

        addImageBtn = findViewById( R.id.add_image_btn );
        addImageBtn.setOnClickListener( view -> photoPickerActivity.launch( "image/*" ) );

        removeImageBtn = findViewById( R.id.remove_image_btn );
        removeImageBtn.setOnClickListener( view -> removeImage() );

        imageView = findViewById( R.id.image_view );
    }

    private void setImageContent( InputStream is ) {

        Bitmap decodedFile = BitmapFactory.decodeStream( is );
        int visibility = decodedFile != null ? View.VISIBLE : View.GONE;

        imageView.setVisibility( visibility );
        imageView.setImageBitmap( decodedFile );
    }

    private void removeImage() {
        //Todo: Remove Image
        setImageContent( null );
        addImageBtn.setVisibility( View.VISIBLE );
        removeImageBtn.setVisibility( View.GONE );
    }
}
