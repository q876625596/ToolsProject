package com.ly.toolsproject.base.http;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.ly.toolsproject.base.BaseActivity;
import com.ly.toolsproject.util.LogUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2017/9/22 0022.
 */

public class HttpDemo extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //上传图文
    private void upLoadImageAndText() {
        String userName = "kumi";
        String password = "123456";
        String imagePath = "aaaaaaaaaaaaaa";
        RequestBody userNameBody = RequestBody.create(MediaType.parse("text/plain"), userName);
        RequestBody passwordBody = RequestBody.create(MediaType.parse("text/plain"), password);
        //第一种上传图片的方式,表单提交
        //在ApiManager使用@PartMap Map<String, RequestBody>参数
        File imageFile = new File(imagePath);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), imageFile);
        Map<String, RequestBody> map = new HashMap<>();
        map.put("userName", userNameBody);
        map.put("password", passwordBody);
        map.put("file\"; filename=\"image.png\"", requestFile);

        //第二种，
        // 直接在ApiManager使用@Body RequestBody参数
        //构建body
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("userName", userName)
                .addFormDataPart("password", password)
                .addFormDataPart("file", imageFile.getName(), RequestBody.create(MediaType.parse("image/*"), imageFile))
                .build();
        HttpRetrofit.observerAsync(HttpRetrofit.getDefaultApiManager().upLoad2(HttpRetrofit.HEAD_URL + HttpInterface.HOME, requestBody))
                .subscribe(new ProgressObservableSubscriber<Response>(this) {
                    @Override
                    public void onNext(Response response) {
                        super.onNext(response);
                    }
                });


                /*.subscribe(new ProgressObservableSubscriber<Object>(this){
                    @Override
                    public void onNext(Object o) {
                        super.onNext(o);
                        ((Response)o).getData()
                    }
                });*/
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {

    }
}
