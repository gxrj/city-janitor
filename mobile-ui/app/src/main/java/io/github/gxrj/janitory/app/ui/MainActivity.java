package io.github.gxrj.janitory.app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import io.github.gxrj.janitory.app.R;

public class MainActivity extends AppCompatActivity {
    private ImageButton authBtn, anonymousBtn;
    @Override
    protected void onCreate( Bundle savedInstance ) {
        super.onCreate( savedInstance );
        setContentView( R.layout.activity_main );

        authBtn = findViewById( R.id.authenticatedModeButton );
        anonymousBtn = findViewById( R.id.anonymousModeButton );

        authBtn.setOnClickListener( login() );
        anonymousBtn.setOnClickListener( proceedAnonymously() );
    }

    private View.OnClickListener login() {
        return ( View view ) -> {};
    }

    private View.OnClickListener proceedAnonymously() {
        return ( View view ) ->
            startActivity( new Intent( this, HomeActivity.class ) );
    }
}
