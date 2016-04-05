package com.example.administrator.tempele.bean;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by Administrator on 2016/3/28.
 */
public class NewsNetBean implements Serializable {
    private String reason;
    private String[] result;
    private String error_code;
    public NewsNetBean(){

    }
    public NewsNetBean(String error_code, String reason, String[] result) {
        this.error_code = error_code;
        this.reason = reason;
        this.result = result;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String[] getResult() {
        return result;
    }

    public void setResult(String[] result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "NewsNetBean{" +
                "error_code='" + error_code + '\'' +
                ", reason='" + reason + '\'' +
                ", result=" + Arrays.toString(result) +
                '}';
    }
}
