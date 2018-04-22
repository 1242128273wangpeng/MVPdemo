package com.example.administrator.tempele.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 2014wang on 2016/4/23.
 */
public class StockListRootResult implements Serializable {
    private String totalCount; /*总条数*/
    private String page; /*总条数*/
    private String num; /*显示条数*/
    private List<StockListBean> data; /*数据数组 里面就是StockListBean{ }*/

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public void setData(List<StockListBean> data) {
        this.data = data;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public String getPage() {
        return page;
    }

    public String getNum() {
        return num;
    }

    public List<StockListBean> getData() {
        return data;
    }

    @Override
    public String toString() {
        return "StockListRootResult{" +
                "totalCount='" + totalCount + '\'' +
                ", page='" + page + '\'' +
                ", num='" + num + '\'' +
                ", data=" + data +
                '}';
    }
}
