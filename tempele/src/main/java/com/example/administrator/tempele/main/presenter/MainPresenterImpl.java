package com.example.administrator.tempele.main.presenter;

import com.example.administrator.tempele.main.view.MainView;

/**
 * Created by Administrator on 2016/3/27.
 */
public class MainPresenterImpl implements MainPresenter {
    private MainView mainview;

    public MainPresenterImpl(MainView mainview) {
        this.mainview = mainview;
    }

    @Override
    public void switchNavigation(int type) {
        mainview.DrawerSwitch(type);
    }
}
