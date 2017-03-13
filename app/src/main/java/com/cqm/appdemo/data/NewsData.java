package com.cqm.appdemo.data;

import com.cqm.appdemo.bean.NewsBean;

import java.util.List;

/**
 * Created by chenqm on 2016/12/12.
 */

public class NewsData {

//        "stat":"1",
//         "data":[
//        {
//            "title":"单身女子半夜被陌生男子摸醒 对方被抓称心情不好",
//            "date":"2016-12-12 00:52",
//            "author_name":"中国青年网",
//            "thumbnail_pic_s":"http:\/\/01.imgmini.eastday.com\/mobile\/20161212\/20161212005229_6a325a0eb419c65306522aa4ea935fb1_1_mwpm_03200403.jpeg",
//            "thumbnail_pic_s02":"http:\/\/01.imgmini.eastday.com\/mobile\/20161212\/20161212005229_6a325a0eb419c65306522aa4ea935fb1_1_mwpl_05500201.jpeg",
//            "thumbnail_pic_s03":"http:\/\/01.imgmini.eastday.com\/mobile\/20161212\/20161212005229_6a325a0eb419c65306522aa4ea935fb1_1_mwpl_05500201.jpeg",
//            "url":"http:\/\/mini.eastday.com\/mobile\/161212005229294.html?qid=juheshuju",
//            "uniquekey":"161212005229294",
//            "type":"头条",
//            "realtype":"社会"
//        },...
//        ]

    private String stat;
    private List<NewsBean> data;

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public List<NewsBean> getData() {
        return data;
    }

    public void setData(List<NewsBean> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "NewsData{" +
                "stat='" + stat + '\'' +
                ", data=" + data +
                '}';
    }
}
