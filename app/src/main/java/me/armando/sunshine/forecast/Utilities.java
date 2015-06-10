package me.armando.sunshine.forecast;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import me.armando.sunshine.classes.DateTimeFunctions;

/**
 * Created by armando on 6/10/15.
 */
public class Utilities
{
    public Utilities() { }

    public static String formatHighLows(Double high, Double low)
    {
        long roundedHigh=Math.round(high);
        long roundedLow=Math.round(low);
        String highLowStr=roundedHigh+"/"+roundedLow;
        return highLowStr;
    }

    public static ArrayList<String> getWeatherDataFromJson(String forecastJsonStr, int numDays) throws JSONException
    {
        final String OWM_LIST="list";
        final String OWM_WEATHER="weather";
        final String OWM_TEMPERATURE="temp";
        final String OWM_MAX="max";
        final String OWM_MIN="min";
        final String OWM_DESCRIPTION="main";
        JSONObject forecastJson=new JSONObject(forecastJsonStr);
        JSONArray weatherArray=forecastJson.getJSONArray(OWM_LIST);
        Date dayTime=new Date();
        dayTime.setTime(System.currentTimeMillis());
        int julianStartDay=Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
        dayTime=new Date();
        ArrayList<String> resultStrs=new ArrayList<String>();
        for(int i=0; i<weatherArray.length(); i++)
        {
            String day;
            String description;
            String highAndLow;
            JSONObject dayForecast=weatherArray.getJSONObject(i);
            long dateTime;
            Calendar today=Calendar.getInstance();
            today.set(Calendar.DAY_OF_YEAR, julianStartDay+i);
            dateTime=today.getTimeInMillis();
            day=DateTimeFunctions.getReadableDateString(dateTime);
            JSONObject weatherObject=dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(0);
            description=weatherObject.getString(OWM_DESCRIPTION);
            JSONObject temperatureObject=dayForecast.getJSONObject(OWM_TEMPERATURE);
            double high=temperatureObject.getDouble(OWM_MAX);
            double low=temperatureObject.getDouble(OWM_MIN);
            highAndLow=Utilities.formatHighLows(high, low);
            resultStrs.add(day+" - "+description+" - "+highAndLow);
        }
        return resultStrs;
    }
}
