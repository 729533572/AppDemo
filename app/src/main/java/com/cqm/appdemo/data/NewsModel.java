package com.cqm.appdemo.data;

/**
 * Created by chenqunming on 2017/3/6.
 */

public class NewsModel {
    private String keyType = "";
    private String title = "";
    private int position = 0;

    public NewsModel(String keyType, String title) {
        this.keyType = keyType;
        this.title = title;
    }

    public String getKeyType() {
        return keyType;
    }

    public String getTitle() {
        return title;
    }

    public void setKeyType(String keyType) {
        this.keyType = keyType;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
