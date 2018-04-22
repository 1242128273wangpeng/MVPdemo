package com.example.administrator.tempele.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 2014wang on 2016/4/23.
 */
public class StockListRootBean implements Serializable {
    private int error_code; /*错误码*/
    private String reason; /*SUCCESSED*/
    private StockListRootResult result; /*Result里面套 Class{ }*/

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setResult(StockListRootResult result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public String getReason() {
        return reason;
    }

    public StockListRootResult getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "StockListRootBean{" +
                "error_code=" + error_code +
                ", reason='" + reason + '\'' +
                ", result=" + result +
                '}';
    }
}
