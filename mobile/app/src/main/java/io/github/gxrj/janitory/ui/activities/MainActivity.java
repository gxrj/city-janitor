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
        anonymousBtn.setOnClickListener( listener -> mockCategories() );
    }

    private void mockCategories() {

        String json = "[ { \"name\": \"Agua pluvial, bueiros e esgoto\"," +
                "\"duties\": [ " +
                    "{\"name\":\"Alagamento\"}," +
                    "{\"name\":\"Bueiro sem tampa\"}," +
                    "{\"name\":\"Bueiro com tampa de madeira quebrado\"}," +
                    "{\"name\":\"Bueiro com tampa de ferro quebrado\"}," +
                    "{\"name\":\"Bueiro Entupido Internamente\"}," +
                    "{\"name\":\"Esgoto\"}," +
                    "{\"name\":\"Falta de agua\"}," +
                    "{\"name\":\"Vazamentos\"} ] }" +
                ",{ \"name\": \"Iluminacao e energia\"," +
                "\"duties\": [ " +
                    "{\"name\":\"Fiacao irregular\"}," +
                    "{\"name\":\"Iluminacao publica\"}," +
                    "{\"name\":\"Postes/Cabos\"} ] }" +
                ",{ \"name\": \"Irregularidades\"," +
                "\"duties\": [" +
                    "{\"name\":\"Ambulantes\"}," +
                    "{\"name\":\"Condicao sanitaria irregular\"}," +
                    "{\"name\":\"Estabelecimento irregular\"}," +
                    "{\"name\":\"Estacionamento irregular\"}," +
                    "{\"name\":\"Emissao de poluentes\"}," +
                    "{\"name\":\"Obra irregular\"}," +
                    "{\"name\":\"Ocupacao irregular\"}," +
                    "{\"name\":\"Poluicao sonora\"}," +
                    "{\"name\":\"Publicidade irregular\"}," +
                    "{\"name\":\"Coleta de lixo organico\"}," +
                    "{\"name\":\"Mercado popular/quiosques\"}," +
                    "{\"name\":\"Publicidade irregular em via\"} ] }" +
                ",{ \"name\": \"Limpeza e conservacao\"," +
                "\"duties\": [" +
                    "{\"name\":\"Capina e rocada\"}," +
                    "{\"name\":\"Mato alto\"}," +
                    "{\"name\":\"Coleta seletiva de lixo\"}," +
                    "{\"name\":\"Lixeiras publicas\"}," +
                    "{\"name\":\"Entulho na calcada/via publica\"}," +
                    "{\"name\":\"Manutencao de pracas\"}," +
                    "{\"name\":\"Limpeza de rua\"}," +
                    "{\"name\":\"Retirada de galhos e restos de poda\"}," +
                    "{\"name\":\"Retirada de Animais mortos na via\"} ] } ]";
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