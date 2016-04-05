package com.example.administrator.tempele.news.presenter;

import com.example.administrator.tempele.bean.NewsBean;
import com.example.administrator.tempele.news.model.NewsModel;
import com.example.administrator.tempele.news.model.NewsModelImpl;
import com.example.administrator.tempele.news.view.NewsView;
import com.example.administrator.tempele.news.widget.NewsFragment;
import com.example.administrator.tempele.url.MURL;

import java.util.List;

/**
 * Created by Administrator on 2016/3/27.
 */
public class NewsPresenterImpl implements NewsPresenter, NewsModelImpl.OnLoadNewsListener {
    private NewsModel newsmodel;
    private NewsView newsview;

    public NewsPresenterImpl(NewsView newsview) {
        this.newsmodel = new NewsModelImpl();
        this.newsview = newsview;
    }

    @Override
    public void loadNews(int type) {
        String url = createUrl(type);
        newsview.showProgress();
        newsmodel.loadingNews(url,this);
    }

    // 根据NewsFragment的type构建url
    public String createUrl(int type) {
        StringBuffer sb = new StringBuffer();
        switch (type) {
            case NewsFragment.NEWS_TYPE_TOP:
                sb.append(MURL.NOWHOST).append("&key=").append(MURL.KEY);
                break;
            case NewsFragment.NEWS_TYPE_NANCHANG:
                sb.append(MURL.QUERYHOST).append("&key=").append(MURL.KEY).append("&q=南昌");
                break;
            case NewsFragment.NEWS_TYPE_VR:
                sb.append(MURL.QUERYHOST).append("&key=").append(MURL.KEY).append("&q=VR");
                break;
            case NewsFragment.NEWS_TYPE_NBA:
                sb.append(MURL.QUERYHOST).append("&key=").append(MURL.KEY).append("&q=NBA");
                break;
        }
        System.out.println("sb---->"+sb.toString());
        return sb.toString();
    }

    @Override
    public void onSuccess(List<NewsBean> list) {
        newsview.hideProgress();
        System.out.println("hideProgress");
        newsview.addNews(list);
        System.out.println("addNews");
    }

    @Override
    public void onFailure(String msg) {
        newsview.hideProgress();
        newsview.showFail();
    }
}
