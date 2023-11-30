package io.github.gxrj.janitory.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;

import java.util.List;

import io.github.gxrj.janitory.R;
import io.github.gxrj.janitory.data.providers.WebClient;
import io.github.gxrj.janitory.domain.models.Category;
import io.github.gxrj.janitory.domain.models.Duty;

public class DutiesActivity  extends AppCompatActivity {

    private static Category selectedCategory;
    private static String plainJsonDistricts = "[]";

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_duties );
        fakeDistricts();
        render();
        setListeners();
    }

    private void render() {
        TextView tv = findViewById( R.id.category_selected );
        String plainCategory = getData().getString( "category" );

        try {
            selectedCategory = Category.fromJsonString( plainCategory );
            tv.setText( selectedCategory.toString() );
            populateList( selectedCategory.getDuties() );
        }
        catch( JSONException e ) {
            Log.e( "error", "JSONException at duty list initialization" );
        }
    }

    private Bundle getData() {
        return getIntent().getExtras();
    }

    private void populateList( List<Duty> list ) throws JSONException {
        ListView listView = findViewById( R.id.duties );

        ArrayAdapter<Duty> adapter =
                new ArrayAdapter<>( this, R.layout.item_duties, R.id.duty_name, list );
        listView.setAdapter( adapter );
    }

    private void setListeners() {
        Button backBtn = findViewById( R.id.back_btn );
        backBtn.setOnClickListener( view -> finish() );

        ListView lv = findViewById( R.id.duties );
        lv.setOnItemClickListener(
                ( parent, view, position, id ) -> proceedToCallForm( parent, position ) );
    }

    private void proceedToCallForm( AdapterView<?> parent, int position ) {
        Duty duty = ( Duty ) parent.getItemAtPosition( position );
        Intent callFormActivity = new Intent( this, CallFormActivity.class );

        callFormActivity.putExtra( "duty", Duty.toPlainJson( duty, selectedCategory ) );
        callFormActivity.putExtra( "districts", plainJsonDistricts );
        startActivity( callFormActivity );
    }

    private void fakeDistricts() {
        String[] districts = {
                "Ajuda de Baixo", "Ajuda de Cima", "Alto dos Cajueiros",
                "Aroeira", "Bairro da Glória", "Barra de Macaé",
                "Botafogo", "Cabiúnas", "Cajueiros", "Campo D'Oeste",
                "Cancela Preta", "Cavaleiros", "Centro", "Costa do Sol",
                "Engenho da Praia", "Fronteira", "Granja dos Cavaleiros",
                "Horto", "Imbetiba", "Imboassica", "Jardim Santo Antonio",
                "Jardim Vitória", "Lagoa", "Lagomar", "Malvinas", "Miramar",
                "Mirante da Lagoa", "Nova Esperança", "Nova Holanda",
                "Novo Cavaleiros", "Novo Horizonte", "Parque Aeroporto",
                "Parque Atlantico", "Parque União", "Praia Campista",
                "Praia do Pecado", "Riviera Fluminense", "Sol Y Mar",
                "São José do Barreto", "São Marcos", "Vale Encantado",
                "Virgem Santa", "Visconde de Araújo" };

        String json = "[";
        for( int i = 0; i < districts.length; i++ ) {
            json += "{ \"id\":" + i + ", \"name\": \"" + districts[i] + "\" }";
            json += i == districts.length - 1 ? "" : ",";
        }

        json += "]";

       fillDistricts( json );
    }

    private void fetchDistricts() {
        WebClient.fetchData(
                this,
                "TODO: put districts endpoint here",
                null,
                json -> fillDistricts( json.toString() ),
                error -> Log.e( "error", error.toString() ) );
    }

    private void fillDistricts( String json ) {
        plainJsonDistricts = json;
    }
}
