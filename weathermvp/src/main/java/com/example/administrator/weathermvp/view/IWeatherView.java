package com.example.administrator.weathermvp.view;

/**
 * Created by Administrator on 2016/3/24.
 */
public interface IWeatherView {
    void showLoading();
    void hindLoading();
    void showWeather(String weatherinfo);
    void showError();
    String userChoose();
 }
