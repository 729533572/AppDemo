package com.cqm.appdemo.bean;

/**
 * Created by chenqm on 2016/12/12.
 */

public class NewsResult {


//    "reason":"成功的返回",
//    "result":{
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
//    },
//    "error_code":0


    private String error_code;
    private String reason;
    private NewsData result;

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public NewsData getResult() {
        return result;
    }

    public void setResult(NewsData result) {
        this.result = result;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "NewsResult{" +
                "error_code='" + error_code + '\'' +
                ", reason='" + reason + '\'' +
                ", result=" + result +
                '}';
    }
}
