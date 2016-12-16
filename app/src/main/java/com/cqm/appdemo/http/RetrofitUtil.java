package com.cqm.appdemo.http;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by chenqm on 2016/12/12.
 */

public class RetrofitUtil {

    public static final String BASE_SERVICE = "http://v.juhe.cn/";
    //读超时长，单位：毫秒
    public static final int READ_TIME_OUT = 7676;
    //连接时长，单位：毫秒
    public static final int CONNECT_TIME_OUT = 7676;

    private RetrofitUtil() {

    }

    public static <T> T create(final Class<T> service) {

        //开启Log
//        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
//        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //缓存
//        File cacheFile = new File(BaseApplication.getAppContext().getCacheDir(), "cache");
//        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb
        //增加头部信息
//        Interceptor headerInterceptor =new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request build = chain.request().newBuilder()
//                        .addHeader("Content-Type", "application/json")
//                        .build();
//                return chain.proceed(build);
//            }
//        };

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(READ_TIME_OUT, TimeUnit.MILLISECONDS)
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
//                .addInterceptor(mRewriteCacheControlInterceptor)
//                .addNetworkInterceptor(mRewriteCacheControlInterceptor)
//                .addInterceptor(headerInterceptor)
//                .addInterceptor(logInterceptor)
//                .cache(cache)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_SERVICE)
                .build();

        return retrofit.create(service);
    }


    /**
     * 云端响应头拦截器，用来配置缓存策略
     * Dangerous interceptor that rewrites the server's cache-control header.
     */
//    private final Interceptor mRewriteCacheControlInterceptor = new Interceptor() {
//        @Override
//        public Response intercept(Chain chain) throws IOException {
//            Request request = chain.request();
//            String cacheControl = request.cacheControl().toString();
//            if (!NetWorkUtils.isNetConnected(BaseApplication.getAppContext())) {
//                request = request.newBuilder()
//                        .cacheControl(TextUtils.isEmpty(cacheControl)? CacheControl.FORCE_NETWORK:CacheControl.FORCE_CACHE)
//                        .build();
//            }
//            Response originalResponse = chain.proceed(request);
//            if (NetWorkUtils.isNetConnected(BaseApplication.getAppContext())) {
//                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
//
//                return originalResponse.newBuilder()
//                        .header("Cache-Control", cacheControl)
//                        .removeHeader("Pragma")
//                        .build();
//            } else {
//                return originalResponse.newBuilder()
//                        .header("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_STALE_SEC)
//                        .removeHeader("Pragma")
//                        .build();
//            }
//        }
//    };
}
