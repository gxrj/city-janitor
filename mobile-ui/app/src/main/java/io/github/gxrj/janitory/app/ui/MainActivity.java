package io.github.gxrj.janitory.app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

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
        return view -> {
            Toast.makeText( getApplicationContext(), "Authenticated", Toast.LENGTH_SHORT )
                    .show();
        };
    }

    private View.OnClickListener proceedAnonymously() {
        return view -> {
            Toast.makeText( getApplicationContext(), "Anonynous", Toast.LENGTH_SHORT )
                    .show();
        };
    }
}
