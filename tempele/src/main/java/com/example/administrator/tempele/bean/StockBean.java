package com.example.administrator.tempele.bean;

import java.util.List;

/**
 * Created by 2014wang on 2016/4/22.
 */
public class StockBean {
    private String buyFive; /*买五*/
    private String buyFivePri; /*买五报价*/
    private String buyFour; /*买四*/
    private String buyFourPri; /*买四报价*/
    private String buyOne; /*买一*/
    private String buyOnePri; /*买一报价*/
    private String buyThree; /*买三*/
    private String buyThreePri; /*买三报价*/
    private String buyTwo; /*买二*/
    private String buyTwoPri; /*买二报价*/
    private String competitivePri; /*竞买价*/
    private String date; /*日期*/
    private String gid; /*股票编号*/
    private String increPer;   /*涨跌百分比*/
    private String increase;  /*涨跌额*/
    private String name; /*股票名称*/
    private String nowPri; /*当前价格*/
    private String reservePri; /*竞卖价*/
    private String sellFive; /*卖五*/
    private String sellFivePri; /*卖五报价*/
    private String sellFour; /*卖四*/
    private String sellFourPri; /*卖四报价*/
    private String sellOne; /*卖一*/
    private String sellOnePri; /*卖一报价*/
    private String sellThree; /*卖三*/
    private String sellThreePri; /*卖三报价*/
    private String sellTwo; /*卖二*/
    private String sellTwoPri; /*卖二报价*/
    private String time; /*时间*/
    private String todayMax; /*今日最高价*/
    private String todayMin; /*今日最低价*/
    private String todayStartPri; /*今日开盘价*/
    private String traAmount; /*成交金额*/
    private String traNumber; /*成交量*/
    private String yestodEndPri; /*昨日收盘价*/

    class Dapandata {     /*大盘类*/
        private String dot;
        private String name;
        private String nowPic;
        private String rate;
        private String traAmount;
        private String traNumber;

    }

    class Gopicture { /*图片*/
        private String minurl; // 分钟的折线图水印图
        private String dayurl; // 日的折线图水印图
        private String weekurl; // 周的折线图水印图
        private String monthurl; // 月的折线图水印图
    }

    class Result {
        private StockBean data;  /*单个股票数据类 class StockBean{ }*/
        private Dapandata dapandata; /*大盘数据可通过字段type单独查询 class Dapandata{ }*/
        private Gopicture gopicture; /*股票图片的折线有水印不可用 class Gopicture{ }*/

    }

    class Root {
        private String resultcode; /*返回码，200:正常*/
        private int error_code; /*错误码*/
        private String reason; /*"SUCCESSED"*/
        private List<Result> result; /*Result里面套 class Result{ }*/
    }
}

