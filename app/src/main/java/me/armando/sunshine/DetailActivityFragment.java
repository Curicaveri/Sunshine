package me.armando.sunshine;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.ShareActionProvider;
import android.widget.TextView;

import org.w3c.dom.Text;

import me.armando.sunshine.forecast.Utilities;


/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment
{

    protected static final String FORECAST_TAG=" #SunshineApp";
    protected String forecastMessage;

    public DetailActivityFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView=inflater.inflate(R.layout.fragment_detail, container, false);
        Intent detailIntent=getActivity().getIntent();
        this.forecastMessage=detailIntent.getStringExtra("item");
        TextView lblForecastString=(TextView)rootView.findViewById(R.id.lblForecastString);
        lblForecastString.setTextSize(40);
        lblForecastString.setText(this.forecastMessage);
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        inflater.inflate(R.menu.menu_detail, menu);
        MenuItem menuItem=menu.findItem(R.id.action_share);
        ShareActionProvider shaProvider=(ShareActionProvider)MenuItemCompat.getActionProvider(menuItem);
        if(shaProvider!=null)
        {
            shaProvider.setShareIntent(this.createShareForecastIntent());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id=item.getItemId();
        if(id==R.id.action_share)
        {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected Intent createShareForecastIntent()
    {
        Intent shareIntent=new Intent(Intent.ACTION_SEND);
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, this.forecastMessage+DetailActivityFragment.FORECAST_TAG);
        return shareIntent;
    }
}