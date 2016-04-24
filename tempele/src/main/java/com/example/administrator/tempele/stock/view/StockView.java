package com.example.administrator.tempele.stock.view;

import com.example.administrator.tempele.bean.NewsBean;
import com.example.administrator.tempele.bean.StockListBean;
import com.example.administrator.tempele.bean.StockListRootBean;

import java.util.List;

/**
 * Created by 2014wang on 2016/4/22.
 */
public interface StockView {
    void showProgress();

    void hideProgress();

    void addStocks(List<StockListBean> stockListBeans);

    void showFail();
}
