package io.github.gxrj.janitory.app.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import io.github.gxrj.janitory.app.R;

public class HomeFragment extends Fragment  {

    public HomeFragment() {}

    @Override
    public void onCreate( Bundle savedInstance ) {
        super.onCreate( savedInstance );
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstance ) {
        return inflater.inflate( R.layout.fragment_home, container, false );
    }
}
