package com.ly.toolsproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.ly.toolsproject.base.BaseFragment;
import com.mindorks.nybus.NYBus;
import com.mindorks.nybus.annotation.Subscribe;
import com.mindorks.nybus.event.Channel;

/**
 * Created by Administrator on 2017/9/30 0030.
 */

public class Fragment1 extends BaseFragment {

    private TextView text1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NYBus.get().register(this, Channel.ONE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        NYBus.get().unregister(this, Channel.ONE);
    }

    @Override
    protected void getData(Bundle bundle) {

    }

    @Override
    protected void initView() {
        text1 = (TextView) mRootView.findViewById(R.id.text1);
    }

    @Subscribe(channelId = Channel.ONE)
    public void onEvent(String event) {
        text1.setText(event);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_1;
    }

    @Override
    protected void onLazyLoad() {

    }

    @Override
    protected void initData() {

    }
}
