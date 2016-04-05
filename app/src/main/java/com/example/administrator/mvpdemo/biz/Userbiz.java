package com.example.administrator.mvpdemo.biz;

import com.example.administrator.mvpdemo.model.User;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/3/23.
 */
public class Userbiz implements IUserbiz {
    @Override
    public void login(final String name, final String password, final OnLoginListener onLoginListener) {
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            TimeUnit.SECONDS.sleep(3);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if ("wang2014".equals(name) && "wang2014".equals(password)) {
                            User user = new User();
                            user.setPassword(password);
                            user.setUsername(name);
                            onLoginListener.loginSuccess(user);
                        } else {
                           onLoginListener.loginFail();
                        }
                    }
                }
        ).start();
    }
}
