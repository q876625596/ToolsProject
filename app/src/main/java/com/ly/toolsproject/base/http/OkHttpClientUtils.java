package com.ly.toolsproject.base.http;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.*;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/9/26 0026.
 */

public class OkHttpClientUtils {

    //默认的interceptor，如需添加默认header则在此处改写key和value值*************
    private static Interceptor interceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request original = chain.request();
            Request.Builder builder = original.newBuilder();
            builder.addHeader("key", "value");
            Request request = builder.method(original.method(), original.body())
                    .build();
            return chain.proceed(request);
        }
    };

    private static final int DEFAULT_TIMEOUT = 10;

    //默认的httpClient，如需设置默认header则在build之后addInterceptor进行设置************
    private static OkHttpClient defaultHttpClient = new OkHttpClient
            .Builder()
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS).build();

    //自定义header的httpClient
    private static OkHttpClient customHttpClient = new OkHttpClient
            .Builder()
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS).build();


    //获取默认的httpClient
    public static OkHttpClient getDefaultInstance() {
        return defaultHttpClient;
    }

    //获取自定义一个header的httpClient
    public static OkHttpClient getCustomHeaderInstance(final String headerKey, final String headerValue) {
        customHttpClient.interceptors().clear();
        customHttpClient.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder builder = original.newBuilder();
                builder.addHeader(headerKey, headerValue);
                Request request = builder.method(original.method(), original.body())
                        .build();
                return chain.proceed(request);
            }
        });
        return customHttpClient;
    }

    //获取自定义多个header的httpClient
    public static OkHttpClient getMultiHeaderInstance(final Map<String, String> headerMap) {
        customHttpClient.interceptors().clear();
        customHttpClient.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder builder = original.newBuilder();
                while (headerMap.entrySet().iterator().hasNext()) {
                    builder.addHeader(headerMap.entrySet().iterator().next().getKey(), headerMap.entrySet().iterator().next().getValue());
                }
                Request request = builder.method(original.method(), original.body())
                        .build();
                return chain.proceed(request);
            }
        });
        return customHttpClient;
    }
}
