package com.cqm.appdemo.bean;

public class JokeBean {
    private int unixtime;
    private String updatetime;
    private String hashId;
    private String content;

    public int getUnixtime() {
        return this.unixtime;
    }

    public void setUnixtime(int unixtime) {
        this.unixtime = unixtime;
    }

    public String getUpdatetime() {
        return this.updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getHashId() {
        return this.hashId;
    }

    public void setHashId(String hashId) {
        this.hashId = hashId;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
