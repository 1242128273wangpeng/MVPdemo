package com.example.administrator.weathermvp.presenter;

import android.os.Handler;
import android.os.Message;

import com.example.administrator.weathermvp.biz.IWeatherBiz;
import com.example.administrator.weathermvp.biz.OnWeatherListener;
import com.example.administrator.weathermvp.biz.WeatherBiz;
import com.example.administrator.weathermvp.view.IWeatherView;

/**
 * Created by Administrator on 2016/3/24.
 */
public class WeatherPresenter implements IWeatherPresenter {
    private IWeatherView weatherView;
    private IWeatherBiz weatherBiz;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
                if(msg.what==1){
                    weatherView.showWeather(msg.obj.toString());
                    weatherView.hindLoading();
                }else if(msg.what == 9){
                    weatherView.showError();
                    weatherView.hindLoading();
                }
        }
    };

    public WeatherPresenter(IWeatherView weatherView) {
        this.weatherView = weatherView;
        weatherBiz = new WeatherBiz();
    }

    @Override
    public void getWeather() {
        weatherView.showLoading();
        weatherBiz.loadWeather(weatherView.userChoose(), new OnWeatherListener() {
            @Override
            public void getsuccess(final String weatherinfostr) {
                Message handlemsg = handler.obtainMessage();
                handlemsg.what=1;
                handlemsg.obj = weatherinfostr;
                handler.sendMessage(handlemsg);
            }
            @Override
            public void getfail() {
                Message handlemsg = handler.obtainMessage();
                handlemsg.what=9;
                handler.sendMessage(handlemsg);
            }
        });
    }
}
