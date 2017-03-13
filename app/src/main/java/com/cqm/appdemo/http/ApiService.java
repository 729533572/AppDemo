package com.cqm.appdemo.http;


import com.cqm.appdemo.bean.ConsBean;
import com.cqm.appdemo.bean.JokeResult;
import com.cqm.appdemo.bean.NewsResult;
import com.cqm.appdemo.bean.GankResult;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by chenqm on 2016/12/12.
 */

public interface ApiService {


    @GET("toutiao/index")
    Call<NewsResult> getNewsData(@Query("key") String key, @Query("type") String type);

    @GET
    Call<ConsBean> getConsData(@Url String url, @QueryMap Map<String, String> options);

    @GET
    Call<GankResult> getGankData(@Url String url);

    @GET
    Call<JokeResult> getJokeData(@Url String url, @QueryMap Map<String, String> options);

    @GET
    Call<String> getWeatherData(@Url String url, @Query("key") String key, @Query("cityname") String cityname);

    //文件上传
    @Multipart
    @POST("upload")
    Call<ResponseBody> upload(@Part("description") RequestBody description,
                              @Part MultipartBody.Part file);

    @Multipart
    @POST("upload/")
    Call<ResponseBody> uploadFiles(
            @Part("description") String description,
            @PartMap() Map<String, RequestBody> maps);

    @Multipart
    @POST("upload/")
    Call<ResponseBody> upload(
            @Body RequestBody body);

    @Streaming
    @GET
    Call<ResponseBody> downloadFile(@Url String fileUrl);


}
