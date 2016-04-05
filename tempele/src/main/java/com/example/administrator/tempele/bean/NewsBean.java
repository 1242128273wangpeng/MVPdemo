package com.example.administrator.tempele.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/3/27.
 */
public class NewsBean implements Serializable {
    private String title;
    private String content;
    private String pdate;
    private String img;
    private String url;
    public NewsBean(){

    }

    public NewsBean(String content, String img, String pdate, String title, String url) {
        this.content = content;
        this.img = img;
        this.pdate = pdate;
        this.title = title;
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPdate() {
        return pdate;
    }

    public void setPdate(String pdate) {
        this.pdate = pdate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "NewsBean{" +
                "content='" + content + '\'' +
                ", title='" + title + '\'' +
                ", pdate='" + pdate + '\'' +
                ", img='" + img + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
