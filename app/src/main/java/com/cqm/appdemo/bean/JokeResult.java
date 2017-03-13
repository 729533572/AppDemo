package com.cqm.appdemo.bean;

/**
 * Created by chenqunming on 2017/3/10.
 */

public class JokeResult {
    private String error_code;
    private String reason;
    private JokeData result;

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

    public JokeData getResult() {
        return result;
    }

    public void setResult(JokeData result) {
        this.result = result;
    }
}
