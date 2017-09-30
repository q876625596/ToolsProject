package com.ly.toolsproject.base.http;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.flowables.ConnectableFlowable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.internal.operators.flowable.FlowableFlatMap;
import io.reactivex.internal.operators.flowable.FlowableFromPublisher;
import io.reactivex.internal.operators.flowable.FlowablePublish;
import io.reactivex.internal.operators.flowable.FlowableReduce;
import io.reactivex.internal.operators.flowable.FlowableSamplePublisher;
import io.reactivex.processors.PublishProcessor;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by Administrator on 2017/9/26 0026.
 */

public class RxOperatorsFlowbleUtils {


    /**
     * 普通订阅
     *
     * @param flowable                   数据发送器（上游）
     * @param progressFlowableSubscriber 下游接收器（下游）
     * @param <T>                        数据类型
     */
    public static <T> void subscriberNormal(Flowable<T> flowable, ProgressFlowableSubscriber<T> progressFlowableSubscriber) {
        flowable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(progressFlowableSubscriber);
    }

    /**
     * map过滤订阅
     *
     * @param flowable                   数据发送器（上游）
     * @param function                   数据过滤器
     * @param progressFlowableSubscriber 下游接收器（下游）
     * @param <T>                        初始数据类型
     * @param <K>                        过滤之后的数据类型
     */
    public static <T, K> void subscriberMap(Flowable<T> flowable, Function<T, K> function, ProgressFlowableSubscriber<K> progressFlowableSubscriber) {
        flowable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .map(function)
                .subscribe(progressFlowableSubscriber);
    }

    /**
     * zip合并订阅
     *
     * @param flowableT                   第一个数据流
     * @param flowableK                   第二个数据流
     * @param progressFlowableSubscriberR 合并之后的数据接收器
     * @param <T>                         第一个数据流的数据类型
     * @param <K>                         第二个数据流的数据类型
     * @param <R>                         第三个数据流的数据类型
     */
    public static <T, K, R> void subscriberZip(Flowable<T> flowableT,
                                               Flowable<K> flowableK,
                                               BiFunction<T, K, R> biFunction,
                                               ProgressFlowableSubscriber<R> progressFlowableSubscriberR) {
        flowableT
                .onBackpressureDrop()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io());
        flowableK
                .onBackpressureDrop()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io());
        Flowable.zip(flowableT, flowableK, biFunction)
                .onBackpressureDrop()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(progressFlowableSubscriberR);
    }


    //concat操作符用于连接两个相同数据类型的数据流，不同于zip，他是两个数据流按顺序发送数据
    public static <T> void subscriberConcat(Flowable<T> flowableT,
                                            Flowable<T> flowableK,
                                            ProgressFlowableSubscriber<T> progressFlowableSubscriber) {

        Flowable.concat(flowableT, flowableK)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(progressFlowableSubscriber);
    }


    /**
     * flatMap操作符是将一个Observable数据流发送的数据分成多个observable数据流
     * 比如observable发送了data1，data2，data3，
     * 那么flatMap会分解运行三次，可以在flapMap的每一次中重新实例化observable来发送想要的数据
     * flatMap分解之后的observable是无序的*****
     *
     * @param flowableT
     * @param function
     * @param progressFlowableSubscriber
     * @param <T>
     * @param <R>
     */
    public static <T, R> void subscriberFlatMap(Flowable<T> flowableT,
                                                Function<T, Publisher<R>> function,
                                                ProgressFlowableSubscriber<R> progressFlowableSubscriber) {
        flowableT.subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .flatMap(function).subscribe(progressFlowableSubscriber);
    }

    /**
     * 和flatMap一样
     * 注意：concatMap分解之后是有序的*****
     *
     * @param flowableT                  数据源
     * @param function                   flatMap转换器
     * @param progressFlowableSubscriber 转换后的数据源下游
     * @param <T>                        源数据类型
     * @param <R>                        转换后数据类型
     */
    public static <T, R> void subscriberConcatMap(Flowable<T> flowableT,
                                                  Function<T, Publisher<R>> function,
                                                  ProgressFlowableSubscriber<R> progressFlowableSubscriber) {
        flowableT.subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .concatMap(function).subscribe(progressFlowableSubscriber);
    }

    /**
     * 筛选数据操作符
     *
     * @param flowable                   数据源
     * @param predicate                  筛选器
     * @param progressFlowableSubscriber 筛选后的接收器
     * @param <T>                        数据类型
     */
    public static <T> void subscriberFilter(Flowable<T> flowable,
                                            Predicate<T> predicate,
                                            ProgressFlowableSubscriber<T> progressFlowableSubscriber) {
        flowable.subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .filter(predicate)
                .subscribe(progressFlowableSubscriber);

    }

    /**
     * buffer 操作符接受两个参数，buffer(count,skip)，
     * 作用是将 Observable 中的数据按 skip (步长) 分成最大不超过 count 的 buffer ，
     * 然后生成一个  Observable
     *
     * @param flowable                   数据源
     * @param count                      每组数据长度上限
     * @param skip                       每组数据起始位置相对于上一组的间隔
     * @param progressFlowableSubscriber 新的接收器
     * @param <T>                        数据类型
     */
    public static <T> void subscriberBuffer(Flowable<T> flowable,
                                            int count,
                                            int skip,
                                            ProgressFlowableSubscriber<List<T>> progressFlowableSubscriber) {
        flowable.subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .buffer(count, skip)
                .subscribe(progressFlowableSubscriber);

    }

    /**
     * 定时任务
     *
     * @param delay    时间间隔（秒）
     * @param consumer 操作器
     * @return 返回一个disposable控制生命周期
     */
    public static Disposable subscriberTimer(long delay,
                                             Consumer<Long> consumer) {
        return Flowable.timer(delay, TimeUnit.MILLISECONDS)
                .onBackpressureDrop()
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(consumer);

    }

    /**
     * 定时任务
     *
     * @param initDelay 第一个延迟间隔（秒）
     * @param delay     时间间隔（秒）
     * @param consumer  操作器
     * @return 返回一个disposable控制生命周期
     */
    public static Disposable subscriberTimer(long initDelay,
                                             long delay,
                                             Consumer<Long> consumer) {
        return Flowable.interval(initDelay, delay, TimeUnit.MILLISECONDS)
                .onBackpressureDrop()
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(consumer);

    }

}
