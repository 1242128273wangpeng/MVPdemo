package com.example.administrator.tempele.bean;

import java.util.List;

/**
 * Created by 2014wang on 2016/4/22.
 */
public class StockListBean {
    private String symbol; /*股票代码*/
    private String name; /*股票名称*/
    private String trade; /*最新价*/
    private String pricechange; /*涨跌额*/
    private String changepercent; /*涨跌幅*/
    private String buy; /*买入*/
    private String sell; /*卖出*/
    private String settlement; /*昨收*/
    private String open; /*今开*/
    private String high; /*最高*/
    private String low; /*最低*/
    private int volume; /*成交量*/
    private int amount; /*成效额*/
    private String code; /*简码*/
    private String ticktime; /*时间*/
    class Result {
        private String totalCount; /*总条数*/
        private String page; /*总条数*/
        private String num; /*显示条数*/
        private List<StockListBean> data; /*数据数组 里面就是StockListBean{ }*/
    }
    class Root {
        private int error_code; /*错误码*/
        private String reason; /*SUCCESSED*/
        private Result result; /*Result里面套 Class{ }*/
    }
}
