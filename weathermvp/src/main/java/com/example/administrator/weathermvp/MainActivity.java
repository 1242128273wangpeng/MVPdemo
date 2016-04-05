package com.example.administrator.weathermvp;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.weathermvp.presenter.WeatherPresenter;
import com.example.administrator.weathermvp.view.IWeatherView;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity implements IWeatherView {
    @Bind(R.id.weatherinfo_tv)
    TextView weatherinfoTv;
    @Bind(R.id.butgetweather)
    Button butgetweather;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    private WeatherPresenter mweatherPresenter;
    String url2;
    // edittvurl = "http://open.weather.com.cn/data/?areaid=101240101&type=forecast_v&date="+datestr+"&appid="+"28e27ca6da4c7dbe";
    //String Private_Key = "3c4274_SmartWeatherAPI_fc7ed69";
    //101240101 forecast_v yyyyMMddHHmm
    // AppId：28e27ca6da4c7dbe Private_Key：3c4274_SmartWeatherAPI_fc7ed69
    //http://open.weather.com.cn/data/?areaid=101240101&type=forecast_v&date=""&appid=""&key=".urlencode($key);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        weatherinfoTv.setMovementMethod(ScrollingMovementMethod.getInstance());
        mweatherPresenter = new WeatherPresenter(this);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        String datestr = sdf.format(new Date());
        weatherinfoTv.setText(datestr);
        url2 = "http://imshopping.applinzi.com/NearByServlet?lat=37.75343&lon=116.95909&raidus=1000";
    }

    @OnClick(R.id.butgetweather)
    public void WeatherButtonClick() {
        mweatherPresenter.getWeather();
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hindLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showWeather(String weatherinfo) {
        weatherinfoTv.setText(weatherinfo);
    }

    @Override
    public void showError() {
        Toast.makeText(MainActivity.this, "Eroor...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public String userChoose() {
        return url2;
    }
}
