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

        String json = "[ { \"id\": 0, \"name\": \"Agua pluvial, bueiros e esgoto\"," +
                "\"duties\": [ " +
                    "{\"id\":0,\"name\":\"Alagamento\"}," +
                    "{\"id\":1,\"name\":\"Bueiro sem tampa\"}," +
                    "{\"id\":2,\"name\":\"Bueiro com tampa de madeira quebrado\"}," +
                    "{\"id\":3,\"name\":\"Bueiro com tampa de ferro quebrado\"}," +
                    "{\"id\":4,\"name\":\"Bueiro Entupido Internamente\"}," +
                    "{\"id\":5,\"name\":\"Esgoto\"}," +
                    "{\"id\":6,\"name\":\"Falta de agua\"}," +
                    "{\"id\":7,\"name\":\"Vazamentos\"} ] }" +
                ",{ \"id\": 1, \"name\": \"Iluminacao e energia\"," +
                "\"duties\": [ " +
                    "{\"id\":8,\"name\":\"Fiacao irregular\"}," +
                    "{\"id\":9,\"name\":\"Iluminacao publica\"}," +
                    "{\"id\":10,\"name\":\"Postes/Cabos\"} ] }" +
                ",{ \"id\": 2, \"name\": \"Irregularidades\"," +
                "\"duties\": [" +
                    "{\"id\":11,\"name\":\"Ambulantes\"}," +
                    "{\"id\":12,\"name\":\"Condicao sanitaria irregular\"}," +
                    "{\"id\":13,\"name\":\"Estabelecimento irregular\"}," +
                    "{\"id\":14,\"name\":\"Estacionamento irregular\"}," +
                    "{\"id\":15,\"name\":\"Emissao de poluentes\"}," +
                    "{\"id\":16,\"name\":\"Obra irregular\"}," +
                    "{\"id\":17,\"name\":\"Ocupacao irregular\"}," +
                    "{\"id\":18,\"name\":\"Poluicao sonora\"}," +
                    "{\"id\":19,\"name\":\"Publicidade irregular\"}," +
                    "{\"id\":20,\"name\":\"Coleta de lixo organico\"}," +
                    "{\"id\":21,\"name\":\"Mercado popular/quiosques\"}," +
                    "{\"id\":22,\"name\":\"Publicidade irregular em via\"} ] }" +
                ",{ \"id\": 3, \"name\": \"Limpeza e conservacao\"," +
                "\"duties\": [" +
                    "{\"id\":23,\"name\":\"Capina e rocada\"}," +
                    "{\"id\":24,\"name\":\"Mato alto\"}," +
                    "{\"id\":25,\"name\":\"Coleta seletiva de lixo\"}," +
                    "{\"id\":26,\"name\":\"Lixeiras publicas\"}," +
                    "{\"id\":27,\"name\":\"Entulho na calcada/via publica\"}," +
                    "{\"id\":28,\"name\":\"Manutencao de pracas\"}," +
                    "{\"id\":29,\"name\":\"Limpeza de rua\"}," +
                    "{\"id\":30,\"name\":\"Retirada de galhos e restos de poda\"}," +
                    "{\"id\":31,\"name\":\"Retirada de Animais mortos na via\"} ] } ]";
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