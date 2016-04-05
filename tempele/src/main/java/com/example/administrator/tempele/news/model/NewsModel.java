package com.example.administrator.tempele.news.model;

/**
 * Created by Administrator on 2016/3/27.
 */
public interface NewsModel {
    void loadingNews(String url,NewsModelImpl.OnLoadNewsListener listener);

    void loadingNewsDetails(String docid,NewsModelImpl.OnLoadDetailsNewsListener listener);
}
