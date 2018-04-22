package com.example.administrator.tempele.stock.presenter;

import com.example.administrator.tempele.bean.StockListBean;
import com.example.administrator.tempele.bean.StockListRootBean;
import com.example.administrator.tempele.bean.StockListRootResult;
import com.example.administrator.tempele.stock.model.StockModel;
import com.example.administrator.tempele.stock.model.StockModelImpl;
import com.example.administrator.tempele.stock.view.StockView;
import com.example.administrator.tempele.url.MURL;

import java.util.List;

/**
 * Created by 2014wang on 2016/4/22.
 */
public class StockPresenterImpl implements StockPresenter, StockModelImpl.OnLoadStockListener {
    private static final String TAG = StockPresenterImpl.class.getSimpleName();
    private StockModel stockModel;
    private StockView stockView;
  public StockPresenterImpl(StockView stockView){
      this.stockView = stockView;
      this.stockModel = new StockModelImpl();
  }
    @Override
    public void loadStock(int page) {
        String url = createUrl(page);
        stockView.showProgress();
        stockModel.loadingStock(url,this);
    }

    public String createUrl(int page) {
        // 创建返回url
//        if(type==1){
//
//            //
//        }else if(type==2){
//
//        }
        String url = MURL.STOCKLISTHOST+"&key="+MURL.SKEY+"&page="+page;
        System.out.println(TAG+"--createUrl-->"+url);
        return url;
    }


    @Override
    public void onSuccess(StockListRootResult stockListRootResult) {
        stockView.hideProgress();
        List<StockListBean> stockListBeanList = stockListRootResult.getData();
        stockView.addStocks(stockListBeanList);
    }

    public void onFail(String string) {
        stockView.hideProgress();
        stockView.showFail();
    }
}
