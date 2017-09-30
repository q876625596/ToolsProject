package com.ly.toolsproject.base.http;

import android.support.v7.app.AppCompatActivity;

import com.ly.toolsproject.util.LogUtils;
import com.othershe.nicedialog.BaseNiceDialog;
import com.othershe.nicedialog.NiceDialog;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by Administrator on 2017/4/8.
 */

public abstract class ProgressObservableSubscriber<T> extends DisposableObserver<T> {


    private NiceDialog niceDialog;
    private AppCompatActivity context;

    protected ProgressObservableSubscriber(AppCompatActivity context) {
        this.context = context;
        //初始化dialog
        if (niceDialog == null) {
            niceDialog = (NiceDialog) NiceDialog.init()
                    .setLayoutId(com.othershe.nicedialog.R.layout.loading_layout)
                    .setWidth(100)
                    .setHeight(100)
                    .setDimAmount(0.2f);
            //设置dialog关闭的监听
            niceDialog.setOnDialogListener(new BaseNiceDialog.DialogInterface() {
                @Override
                public void onDialogShow() {

                }

                @Override
                public void onDialogDismiss() {
                    //取消订阅
                    dispose();
                }
            });
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        showDialog();
    }

    @Override
    public void onNext(T t) {
        LogUtils.e("onNext");
        if (this.isDisposed()) {
            onComplete();
        }
    }

    @Override
    public void onError(Throwable t) {
        LogUtils.e("error");
        LogUtils.e(t.getMessage());
        //MyToast.myToastShow(YMApplication.getContext(), "数据异常，请重试");
        dismissDialog();
    }

    @Override
    public void onComplete() {
        LogUtils.e("complete");
        dismissDialog();

    }

    //显示dialog
    private void showDialog() {
        if (niceDialog == null) {
            niceDialog = (NiceDialog) NiceDialog.init()
                    .setLayoutId(com.othershe.nicedialog.R.layout.loading_layout)
                    .setWidth(100)
                    .setHeight(100)
                    .setDimAmount(0.2f);
        }
        niceDialog.show(context.getSupportFragmentManager());
    }

    //关闭dialog
    private void dismissDialog() {
        if (niceDialog != null) {
            niceDialog.dismiss();
        }
    }
}
