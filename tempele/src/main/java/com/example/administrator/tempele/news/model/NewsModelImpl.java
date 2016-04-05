package com.example.administrator.tempele.news.model;

import android.os.Handler;
import android.os.Message;

import com.alibaba.fastjson.JSON;
import com.example.administrator.tempele.bean.NewsBean;
import com.example.administrator.tempele.bean.NewsNetBean;
import com.example.administrator.tempele.url.MURL;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/3/27.
 */
public class NewsModelImpl implements NewsModel {

    @Override
    public void loadingNews(String url, final OnLoadNewsListener listener) {
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    listener.onSuccess((List<NewsBean>)msg.obj);
                } else if (msg.what == 9) {
                    listener.onFailure(((Exception)msg.obj).toString());
                }
            }
        };
        final OkHttpClient httpClient = new OkHttpClient();
        final Request request = new Request.Builder().url(url).build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                httpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Message msg = new Message();
                        msg.what = 9;
                        msg.obj = e;
                        handler.sendMessage(msg);
                        System.out.println("fail........");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final List<NewsBean> newslists = new ArrayList<NewsBean>();
                        String resultjson = response.body().string();
                        NewsNetBean netbean = JSON.parseObject(resultjson, NewsNetBean.class);
                        String topnew[] = netbean.getResult();
                        int sum = topnew.length;
                        if (sum >= 10 && topnew != null) {
                            for (int i = 0; i < 10; i++) {
                                final OkHttpClient httpClienta = new OkHttpClient();
                                String urla = MURL.QUERYHOST + "&key=" + MURL.KEY + "&q=" + topnew[i];
                                System.out.println(urla);
                                final Request requesta = new Request.Builder().url(urla).build();
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        httpClienta.newCall(requesta).enqueue(new Callback() {
                                            @Override
                                            public void onFailure(Call call, IOException e) {
                                                Message msg = new Message();
                                                msg.what = 9;
                                                msg.obj = e;
                                                handler.sendMessage(msg);
                                                System.out.println("fail........");
                                            }

                                            @Override
                                            public void onResponse(Call call, Response response) throws IOException {
                                                String jsona = response.body().string();
                                                NewsNetBean netbean = JSON.parseObject(jsona, NewsNetBean.class);
                                                String str[] = netbean.getResult();
                                                String newitema = str[1];
                                                NewsBean newsBean = JSON.parseObject(newitema, NewsBean.class);
                                                newslists.add(newsBean);
                                                System.out.println(newsBean.toString());
                                                if (newslists.size() >= 10) {
                                                    Message msg = new Message();
                                                    msg.what = 1;
                                                    msg.obj = newslists;
                                                    handler.sendMessage(msg);
                                                    System.out.println("newslists" + newslists.size());
                                                }
                                            }
                                        });
                                    }
                                }).start();
                            }
                        } else {
                             // 不是头条的处理方式
                        }
                    }
                });
            }
        }).start();
    }

    @Override
    public void loadingNewsDetails(String docid, OnLoadDetailsNewsListener listener) {

    }

    public interface OnLoadNewsListener {
        void onSuccess(List<NewsBean> list);
        void onFailure(String msg);
    }

    public interface OnLoadDetailsNewsListener {
    }
}
