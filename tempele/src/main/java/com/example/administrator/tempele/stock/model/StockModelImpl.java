package com.example.administrator.tempele.stock.model;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.alibaba.fastjson.JSON;
import com.example.administrator.tempele.bean.StockListBean;
import com.example.administrator.tempele.bean.StockListRootBean;
import com.example.administrator.tempele.bean.StockListRootBean;
import com.example.administrator.tempele.bean.StockListRootResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 2014wang on 2016/4/22.
 */
public class StockModelImpl implements StockModel {
    private static final String TAG = StockModelImpl.class.getSimpleName();
    private static final int SUCCESS = 6;
    private static final int FAIL = 9;

    @Override
    public void loadingStock(String url, final OnLoadStockListener listener) {
        System.out.println(TAG+"loadingStock...");
        MyAsyncTask asyncTask = new MyAsyncTask(listener);
        asyncTask.execute(url);

    }
   class MyAsyncTask extends AsyncTask<String,Void,Bundle>{
       private OnLoadStockListener listener;
       public MyAsyncTask(OnLoadStockListener listener){
            this.listener = listener;
       }
       Bundle rztbundle = null;
       @Override
       protected Bundle doInBackground(String... params) {
           rztbundle = new Bundle();
           OkHttpClient okHttpClient = new OkHttpClient();
           Request request = new Request.Builder().url(params[0]).build();
           try {
               Response response = okHttpClient.newCall(request).execute();
               if (response.isSuccessful()) {
                   rztbundle.putInt("rezcode", 1);
                   String resultjson = response.body().string();
                   StockListRootBean root = JSON.parseObject(resultjson, StockListRootBean.class);
                   System.out.println("root.getResult()-->"+root.getResult().toString());
                   rztbundle.putSerializable("stocklistResultBean",root.getResult());
               } else {
                   rztbundle.putInt("rezcode", 7);
                   rztbundle.putString("exception",response.message());
               }
           } catch (IOException e) {
               e.printStackTrace();
           }
           return rztbundle;
       }
       @Override
       protected void onPostExecute(Bundle rzt) {
           super.onPostExecute(rzt);
           if(rzt.getInt("rezcode")==1){
              StockListRootResult stocklistResultBean = (StockListRootResult) rzt.getSerializable("stocklistResultBean");
              if (stocklistResultBean!=null){
                  listener.onSuccess(stocklistResultBean);
              }
               System.out.println(TAG+" onPostExecute Success");
           }else if(rzt.getInt("rezcode")==7){
               String msg = rztbundle.getString("exception");
                    listener.onFail(msg);
               System.out.println(TAG+" onPostExecute Fail");
           }
       }
   }
    @Override
    public void loadingStockDetail() {

    }

    public interface OnLoadStockListener {
        void onSuccess(StockListRootResult stockListResultBean);

        void onFail(String string);
    }

    public interface OnLoadStockDetailListener {
        void onSuccess();

        void onFail();
    }
}
