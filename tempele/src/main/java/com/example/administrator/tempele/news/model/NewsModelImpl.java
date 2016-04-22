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
    int newcount = 1;
    static int TOPMSG = 1;
    static int NBAMSG = 2;
    static int ANDROIDMSG = 3;
    static int VRMSG = 4;
    static int ERRORMSG = 9;
    @Override
    public void loadingNews(String url, final OnLoadNewsListener listener) {
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == VRMSG||msg.what == NBAMSG||msg.what == ANDROIDMSG||msg.what == TOPMSG) {
                    listener.onSuccess((List<NewsBean>)msg.obj);
                }else if (msg.what == ERRORMSG) {
                    listener.onFailure(((Exception)msg.obj).toString());
                }
            }
        };
        final OkHttpClient httpClient = new OkHttpClient();
        final Request request = new Request.Builder().url(url).build();
        if(url.contains("VR")||url.contains("ANDROID")||url.contains("NBA")) {
            // 不是头条的处理方式
            new Thread(new Runnable() {
                @Override
                public void run() {
                    httpClient.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Message msg = new Message();
                            msg.what = ERRORMSG;
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
                            System.out.println("netbean.getResult()-->"+netbean.getResult());
                            if(topnew!=null){
                                for (int i = 0; i < topnew.length; i++) {
                                    NewsBean nbean = JSON.parseObject(topnew[i],NewsBean.class);
                                    System.out.println("nbean.toString()"+nbean.toString());
                                    if (!"".equals(nbean.getImg())) {
                                        newslists.add(nbean);
                                    }
                                    if (newslists != null && i==topnew.length-1) {
                                        Message msg =handler.obtainMessage();
                                        msg.what = VRMSG;
                                        msg.obj = newslists;
                                        handler.sendMessage(msg);
                                        System.out.println("VRnewslists" + newslists.size());
                                    }
                                }
                            }
                        }
                    });
                }
            }).start();
            System.out.println("不是头条的处理方式");
        }else{
            new Thread(new Runnable() {
                @Override
                public void run() {
                    httpClient.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Message msg = new Message();
                            msg.what = ERRORMSG;
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
                                for (int i = 0; i < 20; i++) {
                                    final OkHttpClient httpClienta = new OkHttpClient();
                                    String urla = MURL.QUERYHOST + "&key=" + MURL.KEY + "&q=" + topnew[i];
                                    System.out.println("======" + urla);
                                    final Request requesta = new Request.Builder().url(urla).build();
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            httpClienta.newCall(requesta).enqueue(new Callback() {
                                                @Override
                                                public void onFailure(Call call, IOException e) {
                                                    Message msg = new Message();
                                                    msg.what = ERRORMSG;
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
                                                    if (!"".equals(newsBean.getImg())) {
                                                        newslists.add(newsBean);
                                                    }
                                                    newcount++;
                                                    if (newcount == 20) {
                                                        Message msg = new Message();
                                                        msg.what = TOPMSG;
//                                                        Collections.sort(newslists);
                                                        msg.obj = newslists;
                                                        handler.sendMessage(msg);
                                                        System.out.println("TOPnewslists" + newslists.size());
                                                        newcount = 0;
                                                    }
                                                }
                                            });
                                        }
                                    }).start();
                                }
                            }
                        }
                    });
                }
            }).start();
        }
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
