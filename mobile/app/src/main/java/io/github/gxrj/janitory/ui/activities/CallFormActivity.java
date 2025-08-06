package io.github.gxrj.janitory.ui.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts.GetContent;
import androidx.appcompat.app.AppCompatActivity;

import io.github.gxrj.janitory.data.providers.WebClient;
import io.github.gxrj.janitory.domain.builders.AddressBuilder;
import io.github.gxrj.janitory.domain.builders.CallBuilder;
import io.github.gxrj.janitory.domain.exceptions.NotValidFormException;
import io.github.gxrj.janitory.domain.models.Address;
import io.github.gxrj.janitory.domain.models.Call;
import io.github.gxrj.janitory.domain.models.Citizen;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import io.github.gxrj.janitory.R;
import io.github.gxrj.janitory.domain.models.District;
import org.json.JSONObject;

public class CallFormActivity extends AppCompatActivity {

    private static List<District> districts = new ArrayList<>();
    Button backBtn, addImageBtn, removeImageBtn, sendFormBtn;
    TextView districtDropdownList;
    AlertDialog districtsDialog;
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
        EditText dutyFormField = findViewById( R.id.duty_form_field );
        dutyFormField.setText( getData().getString( "duty" ) );

        backBtn = findViewById( R.id.back_btn );
        districtDropdownList = findViewById( R.id.districts );
        imageView = findViewById( R.id.image_view );
        addImageBtn = findViewById( R.id.add_image_btn );
        removeImageBtn = findViewById( R.id.remove_image_btn );
        sendFormBtn = findViewById( R.id.send_form_btn );
        bindDistrictList();
    }

    private void bindDistrictList() {
        String plainDistricts = getData().getString( "districts" );

        try {
            districts = District.fromJsonArray( new JSONArray( plainDistricts ) );
        }
        catch( JSONException e ) {
            Log.e( "error", "JSONException at CallFormActivity render" );
            return;
        }

        ArrayAdapter<District> adapter =
                new ArrayAdapter<>( this, R.layout.item_districts, districts );

        AlertDialog.Builder builder = new AlertDialog.Builder( this );

        builder.setTitle( "Selecione o bairro" );
        builder.setSingleChoiceItems( adapter, 0, districtDialogListener() );
        builder.setPositiveButton( "Confirmar", districtDialogListener() );
        builder.setNeutralButton( "Cancelar", districtDialogListener() );

        districtsDialog = builder.create();
    }

    private Bundle getData() {
        return getIntent().getExtras();
    }

    private void setListeners() {

        backBtn.setOnClickListener( view -> finish() );

        addImageBtn.setOnClickListener( view -> photoPickerActivity.launch( "image/*" ) );

        removeImageBtn.setOnClickListener( view -> removeImage() );

        sendFormBtn.setOnClickListener( view -> confirmFormSend() );

        districtDropdownList.setOnClickListener( view -> districtsDialog.show() );
    }

    private DialogInterface.OnClickListener districtDialogListener() {
        return ( dialog, index ) -> {
            String item = districtDropdownList.getText().toString();

            if( index > -1 )
                districtDropdownList.setText( districts.get( index ).toString() );
            else if( item.equals( "Bairro" ) )
                districtDropdownList.setText( districts.get( 0 ).toString() );

            dialog.dismiss();
        };
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

    private void confirmFormSend() {
        AlertDialog.Builder builder = new AlertDialog.Builder( this );
        try {
            String requestBody = buildCall().toPlainJson();
            builder.setTitle( "Confirmar envio" );
            builder.setMessage( "Deseja enviar o formulário?" );
            builder.setPositiveButton( "Confirmar",
                    ( dialog, index ) -> sendForm( requestBody ) );
            builder.setNeutralButton( "Cancelar", null );
        }
        catch ( NotValidFormException formException ) {
            builder.setTitle( "Formulário inválido!" );
            builder.setMessage( formException.getMessage() + "\nPor favor preencha os campos exigidos" );
            builder.setNeutralButton( "Ok", null );
        }

        builder.create().show();
    }

    private Call buildCall() {
        EditText duty = findViewById( R.id.duty_form_field );
        EditText description = findViewById( R.id.description_form_field );
        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        Citizen author = buildAuthor();

        CallBuilder builder = new CallBuilder()
                .address( buildAddress() )
                .duty( duty.getText().toString() )
                .description( description.getText().toString() );

        if( drawable != null )
            builder.image( drawable.getBitmap() );

        if( author != null )
            builder.author( author );

        return builder.build();
    }

    private Address buildAddress() {
        EditText zipCode = findViewById( R.id.zip_code_form_field );
        TextView districts = findViewById( R.id.districts );
        EditText pubPlace = findViewById( R.id.pub_place_form_field );
        EditText addressNumber = findViewById( R.id.address_num_form_field );
        EditText addressRef = findViewById( R.id.address_ref_form_field );

        return new AddressBuilder()
                .zipCode( zipCode.getText().toString() )
                .district( districts.getText().toString() )
                .pubPlace( pubPlace.getText().toString() )
                .number( addressNumber.getText().toString() )
                .reference( addressRef.getText().toString() )
                .build();
    }

    private Citizen buildAuthor() {
        //Todo: check whether the author is anonymous or not, if not build author
        return null;
    }

    private void sendForm( String requestBody ) {

        AlertDialog.Builder builder = new AlertDialog.Builder( this );

        builder.setTitle( "Objeto a enviar" );
        builder.setMessage( requestBody );
        builder.setPositiveButton( "Ok", null );
        /**
        WebClient.callApi(
                "post", "Todo: put server endpoint here",
                processRequest( requestBody ),
                this,
                json -> builder.setMessage( json.toString() ),
                error -> builder.setMessage( error.toString() ) );
        */
        //Todo: start automated tests and ui tests
        //Todo: improve image handling performance
        builder.create().show();
    }

    private JSONObject processRequest( String requestBody ) {
        try {
            return new JSONObject( requestBody );
        }
        catch ( JSONException e ) {
            Log.e( "Error: ", e.getMessage() );
            return null;
        }
    }
}
