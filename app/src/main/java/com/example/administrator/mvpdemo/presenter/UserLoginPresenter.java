package com.example.administrator.mvpdemo.presenter;

import android.os.Handler;

import com.example.administrator.mvpdemo.biz.IUserbiz;
import com.example.administrator.mvpdemo.biz.OnLoginListener;
import com.example.administrator.mvpdemo.biz.Userbiz;
import com.example.administrator.mvpdemo.model.User;
import com.example.administrator.mvpdemo.view.IUserLoginView;

/**
 * Created by Administrator on 2016/3/23.
 */
public class UserLoginPresenter {
    private IUserbiz userbiz;
    private IUserLoginView userloginview;
    private Handler handler = new Handler();
    public UserLoginPresenter(IUserLoginView userloginview){
        this.userloginview = userloginview;
        userbiz = new Userbiz();
    }
    public void login(){
        userloginview.showLoading();// 进度条显示正在登陆
        userbiz.login(userloginview.getuserName(),userloginview.getuserPassword(),new OnLoginListener(){
            @Override
            public void loginSuccess(User user) {
               handler.post(new Runnable() {
                   @Override
                   public void run() {
                       userloginview.toActivity();
                       userloginview.hideLoading();
                   }
               });
            }

            @Override
            public void loginFail() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        userloginview.showfailError();
                        userloginview.hideLoading();
                    }
                });
            }
        });
    }
}
