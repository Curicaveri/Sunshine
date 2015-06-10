package me.armando.sunshine;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

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
        this.adptList=new ArrayAdapter<String>(getActivity(), R.layout.list_item_forecast, R.id.lblItemForecast, this.getData());
        ListView lstForecast=(ListView)this.rootView.findViewById(R.id.lstForecast);
        lstForecast.setAdapter(this.adptList);
        return rootView;
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
            FetchWeatherTask weatherTask=new FetchWeatherTask(this);
            weatherTask.execute("94043");
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

    protected ArrayList<String> getData()
    {
        ArrayList<String> data=new ArrayList<>();
        data.add("Today  -  Sunny  -  27 / 9");
        data.add("Tomorrow  -  Foggy  -  23 / 7");
        data.add("Thursday  -  Cloudy  -  25 / 8");
        data.add("Friday  -  Rainy  -  20 / 5");
        data.add("Saturday  -  Foggy  -  23 / 7");
        data.add("Sunday  -  Sunny  -  27 / 9");
        data.add("Monday  -  Rainy  -  20 / 5");
        return data;
    }
}