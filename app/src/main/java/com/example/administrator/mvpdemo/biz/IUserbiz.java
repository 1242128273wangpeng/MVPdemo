package com.example.administrator.mvpdemo.biz;

/**
 * Created by Administrator on 2016/3/23.
 */
public interface IUserbiz {
    public void login(String name,String password,OnLoginListener onLoginListener);
}
