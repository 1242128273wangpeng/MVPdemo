package com.example.administrator.mvpdemo.view;

/**
 * Created by Administrator on 2016/3/23.
 */
public interface IUserLoginView {
    public void showLoading();
    public void hideLoading();
    public void toActivity();
    public void showfailError();
    public String getuserName();
    public String getuserPassword();
}
