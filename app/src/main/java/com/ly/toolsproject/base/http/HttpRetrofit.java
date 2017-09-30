package com.ly.toolsproject.base.http;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by kumi on 2017/3/16 0016.
 */

//普通http访问
public class HttpRetrofit {

    //接口域名
    //"http://test.lebaoqinzi.com/api/"
    //"http://www.xhrgogo.com/api/"
    public static final String HEAD_URL = "http://test.lebaoqinzi.com/api/";

    //可统一改变map的数据
    public static Map<String, String> getStringMap() {
        HashMap<String, String> stringMap = new HashMap<>();
        //可添加统一参数
        return stringMap;
    }

    //默认httpClient的retrofit
    private static Retrofit defaultRetrofit = new Retrofit.Builder()
            .baseUrl(HEAD_URL)
            .client(OkHttpClientUtils.getDefaultInstance())
            //增加返回值为String的支持
            .addConverterFactory(ScalarsConverterFactory.create())
            //增加返回值为Gson的支持(以实体类返回)
            .addConverterFactory(GsonConverterFactory.create())
            //增加返回值为Observable<T>的支持
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

    //默认httpClient的ApiManager
    private static ApiManager defaultApiManager = defaultRetrofit.create(ApiManager.class);

    //获取默认的ApiManager
    public static ApiManager getDefaultApiManager() {
        return defaultApiManager;
    }


    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null, null);
    }

    //获取单个自定义header的HttpClient的ApiManager
    public static <S> S createService(Class<S> serviceClass, String headerKey, String headerValue) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(OkHttpClientUtils.getCustomHeaderInstance(headerKey, headerValue))
                .baseUrl(HEAD_URL)
                //增加返回值为String的支持
                .addConverterFactory(ScalarsConverterFactory.create())
                //增加返回值为Gson的支持(以实体类返回)
                .addConverterFactory(GsonConverterFactory.create())
                //增加返回值为Observable<T>的支持
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(serviceClass);
    }

    //获取多个自定义header的HttpClient的ApiManager
    public static <S> S createService(Class<S> serviceClass, Map<String, String> headerMap) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(OkHttpClientUtils.getMultiHeaderInstance(headerMap))
                .baseUrl(HEAD_URL)
                //增加返回值为String的支持
                .addConverterFactory(ScalarsConverterFactory.create())
                //增加返回值为Gson的支持(以实体类返回)
                .addConverterFactory(GsonConverterFactory.create())
                //增加返回值为Observable<T>的支持
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(serviceClass);
    }

    /**
     * 使用多线程订阅flowable
     *
     * @param flowable 数据发送器（上游）
     * @param <T>      数据类型
     * @return 当前数据发送器
     */
    public static <T> Flowable<T> subscriberAsync(Flowable<T> flowable) {
        return flowable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io());
    }

    /**
     * 使用多线程订阅observable
     *
     * @param observable 数据发送器（上游）
     * @param <T>        数据类型
     * @return 当前数据发送器
     */
    public static <T> Observable<T> observerAsync(Observable<T> observable) {
        return observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io());
    }
}
