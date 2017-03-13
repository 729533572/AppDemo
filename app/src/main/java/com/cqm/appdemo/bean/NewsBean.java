package com.cqm.appdemo.bean;

/**
 * Created by chenqm on 2016/12/12.
 */

public class NewsBean {

//            "title":"单身女子半夜被陌生男子摸醒 对方被抓称心情不好",
//            "date":"2016-12-12 00:52",
//            "author_name":"中国青年网",
//            "thumbnail_pic_s":"http:\/\/01.imgmini.eastday.com\/mobile\/20161212\/20161212005229_6a325a0eb419c65306522aa4ea935fb1_1_mwpm_03200403.jpeg",
//            "url":"http:\/\/mini.eastday.com\/mobile\/161212005229294.html?qid=juheshuju",


    private String title;
    private String date;
    private String author_name;
    private String thumbnail_pic_s;
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getThumbnail_pic_s() {
        return thumbnail_pic_s;
    }

    public void setThumbnail_pic_s(String thumbnail_pic_s) {
        this.thumbnail_pic_s = thumbnail_pic_s;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    @Override
    public String toString() {
        return "NewsBean{" +
                "title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", author_name='" + author_name + '\'' +
                ", thumbnail_pic_s='" + thumbnail_pic_s + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
