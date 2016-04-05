package com.example.administrator.mvpdemo.biz;

import com.example.administrator.mvpdemo.model.User;

/**
 * Created by Administrator on 2016/3/23.
 */
public interface OnLoginListener {
    void loginSuccess(User user);
    void loginFail();
}
