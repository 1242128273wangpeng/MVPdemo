package com.example.administrator.tempele.news.view;

import com.example.administrator.tempele.bean.NewsBean;

import java.util.List;

/**
 * Created by Administrator on 2016/3/27.
 */
public interface NewsView {
    void showProgress();

    void hideProgress();

    void addNews(List<NewsBean> newsList);

    void showFail();
}
