package com.ly.toolsproject.base.http;


import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by kumi on 2017/3/16 0016.
 */

public interface ApiManager {

    //将文件转换成RequestBody的方法
    //RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
    //将字符串转换成RequestBody的方法
    //RequestBody.create(MediaType.parse("text/plain"), s);


    //Observable<>中接收的返回数值可以为实体类，
    // retrofit中设置的addConverterFactory()方法可以将返回的json数据通过Gson转换成实体类

    //在GET中，可以不传参数，
    // @Query()注解表示一个参数字段，
    // @QueryMap表示参数的集合Map，类型为Map<String,String>
    @GET()
    Flowable<Response> getData(@Url() String url, @QueryMap() Map<String, String> paramsMap);

    //在GET中，必须传参数，
    // @Field()注解表示一个参数字段
    //@FieldMap表示参数的集合Map，类型为Map<String,String> key==字段名，value==参数值
    @FormUrlEncoded
    @POST()
    Observable<Response> getDataTwo(@Url() String url, @FieldMap() Map<String, String> paramsMap);

    //第一种上传文件的方法,表单上传
    //@PartMap用于上传带图片的参数集合Map，Map的类型为Map<String, RequestBody>
    @Multipart
    @POST()
    Observable<Response> upLoad1(@Url() String url, @PartMap Map<String, RequestBody> params);

    //第二种
    @Multipart
    @POST()
    Observable<Response> upLoad2(@Url() String url, @Body RequestBody bodyParams);


    //传json需要添加头，并用@Body
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("user/loginsession")
    Observable<Response> sss(@Body Request requestRequest);


}
