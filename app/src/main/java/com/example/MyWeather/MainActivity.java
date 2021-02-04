package com.example.MyWeather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tv_weather;

    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_weather = (TextView)findViewById(R.id.tv_weather);

        NewAsyncTask task = new NewAsyncTask();
        task.execute();
    }

    private class NewAsyncTask extends AsyncTask{
        @Override
        protected void onPostExecute(Object o) {
            tv_weather.setText(result);
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            result = new WeatherString().getStr();
            return null;
        }
    }
}