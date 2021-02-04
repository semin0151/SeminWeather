package com.example.MyWeather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tv_weather;

    private Handler mHandler;
    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_weather = (TextView)findViewById(R.id.tv_weather);

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                tv_weather.setText(result);
            }
        } ;

        NewRunnable nr = new NewRunnable();
        Thread t = new Thread(nr);
        t.start();

    }

    class NewRunnable implements Runnable {
        @Override
        public void run() {

            try {
                result = new WeatherString().getStr();
            } catch (Exception e) {
                e.printStackTrace() ;
            }
            mHandler.sendEmptyMessage(0) ;
        }
    }


}