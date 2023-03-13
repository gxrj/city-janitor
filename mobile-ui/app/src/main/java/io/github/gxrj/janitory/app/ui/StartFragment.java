package io.github.gxrj.janitory.app.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import io.github.gxrj.janitory.app.R;

public class StartFragment extends Fragment {

    public StartFragment() {}

    @Override
    public void onCreate( Bundle savedInstance ) {
        super.onCreate( savedInstance );
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstance ) {

        View view = inflater.inflate(
                                R.layout.fragment_start, container, false );

        ImageButton authBtn = view.findViewById( R.id.authenticatedModeButton );
        ImageButton anonymousBtn = view.findViewById( R.id.anonymousModeButton );

        authBtn.setOnClickListener( login() );
        anonymousBtn.setOnClickListener( proceedAnonymously() );

        return view;
    }

    private View.OnClickListener login() {
        return view ->
            Toast.makeText( getActivity(), "Authenticated", Toast.LENGTH_SHORT )
                    .show();
    }

    private View.OnClickListener proceedAnonymously() {
        return view -> Navigation
                        .findNavController( view )
                            .navigate( R.id.homeFragment );
    }
}
