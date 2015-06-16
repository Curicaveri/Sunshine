package me.armando.sunshine;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import me.armando.sunshine.forecast.Utilities;
import me.armando.sunshine.tasks.FetchWeatherTask;

public class ForecastFragment extends Fragment
{
    private ArrayAdapter<String> adptList;
    private View rootView;

    public ForecastFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        this.rootView=inflater.inflate(R.layout.fragment_main, container, false);
        this.adptList=new ArrayAdapter<String>(getActivity(), R.layout.list_item_forecast, R.id.lblItemForecast, new ArrayList<String>());
        final ListView lstForecast=(ListView)this.rootView.findViewById(R.id.lstForecast);
        lstForecast.setAdapter(this.adptList);
        lstForecast.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                String item=adptList.getItem(position);
                Intent detailIntent=new Intent(getActivity(), DetailActivity.class);
                detailIntent.putExtra("item", item);
                getActivity().startActivity(detailIntent);
            }
        });
        return rootView;
    }

    @Override
    public void onStart()
    {
        super.onStart();
        Utilities.updateWeather(this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        inflater.inflate(R.menu.forecastfragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id=item.getItemId();
        if(id==R.id.mnuRefresh)
        {
            Utilities.updateWeather(this);
        }
        return super.onOptionsItemSelected(item);
    }

    public ArrayAdapter<String> getAdptList()
    {
        return this.adptList;
    }

    public void setAdptList(ArrayAdapter<String> adptList)
    {
        this.adptList=adptList;
    }

    public View getRootView()
    {
        return this.rootView;
    }

    public void setRootView(View rootView)
    {
        this.rootView=rootView;
    }
}