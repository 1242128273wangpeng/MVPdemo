package com.example.administrator.weathermvp.biz;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/3/24.
 */
public class WeatherBiz implements IWeatherBiz {
    @Override
    public void loadWeather(String weatherurl, final OnWeatherListener onWeatherListener) {
        //
        final OkHttpClient httpClient = new OkHttpClient();
        final Request request = new Request.Builder().url(weatherurl).build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                httpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        onWeatherListener.getfail();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        onWeatherListener.getsuccess(response.body().string().toString());
                    }
                });
            }
        }).start();
    }
}
