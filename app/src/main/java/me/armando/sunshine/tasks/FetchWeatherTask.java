package me.armando.sunshine.tasks;

import android.net.Uri;
import android.os.AsyncTask;

import java.util.ArrayList;

import me.armando.sunshine.ForecastFragment;
import me.armando.sunshine.classes.HttpRequest;
import me.armando.sunshine.forecast.Utilities;

/**
 * Created by armando on 6/10/15.
 */
public class FetchWeatherTask extends AsyncTask<String, Void, ArrayList<String>>
{
    protected ForecastFragment outer=null;

    public FetchWeatherTask(ForecastFragment outer)
    {
        this.outer=outer;
    }

    @Override
    protected ArrayList<String> doInBackground(String... params)
    {
        ArrayList<String> response=null;
        try
        {
            if(params.length>0)
            {
                String postCode=params[0];
                String temperatureUnits=params[1];
                Uri.Builder builder=new Uri.Builder();
                builder.scheme("http");
                builder.authority("api.openweathermap.org");
                builder.appendPath("data");
                builder.appendPath("2.5");
                builder.appendPath("forecast");
                builder.appendPath("daily");
                builder.appendQueryParameter("q", postCode);
                builder.appendQueryParameter("mode", "json");
                builder.appendQueryParameter("units", "metric");
                builder.appendQueryParameter("cnt", "7");
                String route=builder.build().toString();
                String res=HttpRequest.makeHTTPRequest(route, "GET");
                response=Utilities.getWeatherDataFromJson(res, 7, temperatureUnits);
            }
        }
        catch(Exception e)
        {
            response=null;
        }
        return response;
    }

    @Override
    protected void onPostExecute(ArrayList<String> data)
    {
        super.onPostExecute(data);
        if(data!=null)
        {
            this.outer.getAdptList().clear();
            this.outer.getAdptList().addAll(data);
        }
    }
}
