package com.example.administrator.weathermvp.model;

/**
 * Created by Administrator on 2016/3/24.
 */
public class Mypojo
{
    private Weatherinfo weatherinfo;

    public Weatherinfo getWeatherinfo ()
    {
        return weatherinfo;
    }

    public void setWeatherinfo (Weatherinfo weatherinfo)
    {
        this.weatherinfo = weatherinfo;
    }

    @Override
    public String toString()
    {
        return "Classpojo [weatherinfo = "+weatherinfo+"]";
    }
}