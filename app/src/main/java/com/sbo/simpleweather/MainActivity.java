package com.sbo.simpleweather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    static String TAG = "URL Created";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        URL url = NetworkUtil.buildURLForWeather();
        Log.i(TAG, "onCreate: URL" + url);
        new FetchWeatherData().execute(url);
    }
    private class FetchWeatherData extends AsyncTask<URL, Void, String>{

        @Override
        protected String doInBackground(URL... urls) {
            URL weatherURL = urls[0];
            String weatherResults = null;

            try {
                weatherResults = NetworkUtil.getResponseFromHttpUrl(weatherURL);
            }catch (IOException e){
                e.printStackTrace();
            }
            Log.i(TAG, "doInBackground: " + weatherResults);
            return weatherResults;
        }
    }
}
