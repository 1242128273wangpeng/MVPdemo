package com.example.administrator.tempele.stock.model;

/**
 * Created by 2014wang on 2016/4/22.
 */
public interface StockModel {
    void loadingStock(String url, StockModelImpl.OnLoadStockListener listener);
    void loadingStockDetail();
}
