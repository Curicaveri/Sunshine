package me.armando.sunshine;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment
{

    public DetailActivityFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView=inflater.inflate(R.layout.fragment_detail, container, false);
        Intent detailIntent=getActivity().getIntent();
        String message=detailIntent.getStringExtra("item");
        TextView lblForecastString=(TextView)rootView.findViewById(R.id.lblForecastString);
        lblForecastString.setTextSize(40);
        lblForecastString.setText(message);
        return rootView;
    }
}