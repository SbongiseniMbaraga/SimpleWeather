package com.sbo.simpleweather;

import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtil {
    static String TAG = "URL Created";
    public static final String WEATHERBASE_URL = "https://dataservice.accuweather.com/forecasts/v1/daily/5day/305605";
    public static final String  PARAM_METRIC = "metric";
    private static final String METRIC_VALUE = "true";
    private static final String API_KEY = "OJ7cdThmsgDBT2nqlaz1GDhzvhIsbofs";
    private static final  String PARAM_API_KEY = "apikey";

    //Build URI
    public static URL buildURLForWeather(){
        Uri buildUri = Uri.parse(WEATHERBASE_URL).buildUpon()
                .appendQueryParameter(PARAM_METRIC, METRIC_VALUE) //passing in api param k + key
                .appendQueryParameter(PARAM_API_KEY, API_KEY) //passing in metric as measurement unit
                .build();
        URL url = null;

        try {
            url = new URL(buildUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "buildURLForWeather: " + url);
        return url;
    }
    public static  String getResponseFromHttpUrl(URL url) throws IOException{
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("//A");
            boolean hasInput = scanner.hasNext();

            if(hasInput){
                return scanner.next();
            }else {
                return null;
                //Toast.makeText(this, "Has no JSON found", Toast.LENGTH_SHORT).show();
            }
        }finally {
            urlConnection.disconnect();
        }
    }
}
