package io.github.gxrj.janitory.app.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import io.github.gxrj.janitory.app.R;
import io.github.gxrj.janitory.app.http.Client;

public class HomeFragment extends Fragment  {

    private JSONObject categories;
    private TextView textView;

    public HomeFragment() {}

    @Override
    public void onCreate( Bundle savedInstance ) {
        super.onCreate( savedInstance );
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstance ) {

        var view = inflater.inflate( R.layout.fragment_home, container, false );
        textView = view.findViewById( R.id.homeText );
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        fetchData();
        if( categories != null )
            textView.setText( categories.toString() );
    }

    private void fetchData() {
        var params = new HashMap<String, String>();
        params.put( "url", "http://10.0.2.2:8080/anonymous/category/all" );
        params.put( "method", "GET" );

        try {
            categories = Client.sendMessage( params );
        }
        catch ( Exception ex ) {
            textView.setText( ex.getMessage() );
            //Toast.makeText( getActivity(), ex.getMessage(), Toast.LENGTH_SHORT ).show();
        }
    }

}
