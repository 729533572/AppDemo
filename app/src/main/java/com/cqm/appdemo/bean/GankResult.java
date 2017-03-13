package com.cqm.appdemo.bean;

import java.util.List;

/**
 * Created by chenqunming on 2017/3/9.
 */

public class GankResult {

    private boolean error;
    private List<GankBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<GankBean> getResults() {
        return results;
    }

    public void setResults(List<GankBean> results) {
        this.results = results;
    }
}
