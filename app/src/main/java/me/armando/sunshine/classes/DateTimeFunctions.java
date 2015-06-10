package me.armando.sunshine.classes;

import java.text.SimpleDateFormat;

/**
 * Created by armando on 6/10/15.
 */
public class DateTimeFunctions
{
    public DateTimeFunctions() { }

    public static String getReadableDateString(long time)
    {
        SimpleDateFormat shortenedDateFormat=new SimpleDateFormat("EEE MMM dd");
        return shortenedDateFormat.format(time);
    }
}
