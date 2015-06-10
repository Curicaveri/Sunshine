package me.armando.sunshine.classes;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by armando on 6/10/15.
 */
public class HttpRequest
{
    public HttpRequest() { }

    public static String makeHTTPRequest(String route, String method)
    {
        HttpURLConnection urlConnection=null;
        BufferedReader reader=null;
        String res=null;
        try
        {
            URL url=new URL(route);
            urlConnection=(HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod(method);
            urlConnection.connect();
            InputStream inputStream=urlConnection.getInputStream();
            StringBuffer buffer=new StringBuffer();
            if(inputStream==null)
            {
                res=null;
            }
            else
            {
                reader=new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while((line=reader.readLine())!=null)
                {
                    buffer.append(line + "\n");
                }
                if(buffer.length()==0)
                {
                    res=null;
                }
                else
                {
                    res=buffer.toString();
                }
            }
        }
        catch(IOException e)
        {
            Log.e("HttpRequest", "Error ", e);
            res=null;
        }
        finally
        {
            if(urlConnection!=null)
            {
                urlConnection.disconnect();
            }
            if(reader!=null)
            {
                try
                {
                    reader.close();
                }
                catch(final IOException e)
                {
                    Log.e("HttpRequest", "Error closing stream", e);
                }
            }
        }
        return res;
    }
}
