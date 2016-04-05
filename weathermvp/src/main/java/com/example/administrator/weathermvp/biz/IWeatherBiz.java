package com.example.administrator.weathermvp.biz;

/**
 * Created by Administrator on 2016/3/24.
 */
public interface IWeatherBiz {
    void loadWeather(String weatherurl,OnWeatherListener onWeatherListener);
}
