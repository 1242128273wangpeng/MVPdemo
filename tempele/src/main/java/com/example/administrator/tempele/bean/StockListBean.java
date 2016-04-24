package com.example.administrator.tempele.bean;

import java.io.Serializable;

/**
 * Created by 2014wang on 2016/4/23.
 */
public class StockListBean implements Serializable {
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

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTrade() {
        return trade;
    }

    public void setTrade(String trade) {
        this.trade = trade;
    }

    public String getPricechange() {
        return pricechange;
    }

    public void setPricechange(String pricechange) {
        this.pricechange = pricechange;
    }

    public String getChangepercent() {
        return changepercent;
    }

    public void setChangepercent(String changepercent) {
        this.changepercent = changepercent;
    }

    public String getBuy() {
        return buy;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }

    public String getSell() {
        return sell;
    }

    public void setSell(String sell) {
        this.sell = sell;
    }

    public String getSettlement() {
        return settlement;
    }

    public void setSettlement(String settlement) {
        this.settlement = settlement;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTicktime() {
        return ticktime;
    }

    public void setTicktime(String ticktime) {
        this.ticktime = ticktime;
    }

    @Override
    public String toString() {
        return "StockListBean{" +
                "symbol='" + symbol + '\'' +
                ", name='" + name + '\'' +
                ", trade='" + trade + '\'' +
                ", pricechange='" + pricechange + '\'' +
                ", changepercent='" + changepercent + '\'' +
                ", buy='" + buy + '\'' +
                ", sell='" + sell + '\'' +
                ", settlement='" + settlement + '\'' +
                ", open='" + open + '\'' +
                ", high='" + high + '\'' +
                ", low='" + low + '\'' +
                ", volume=" + volume +
                ", amount=" + amount +
                ", code='" + code + '\'' +
                ", ticktime='" + ticktime + '\'' +
                '}';
    }
}
