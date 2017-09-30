package com.ly.toolsproject.base.http;

import org.reactivestreams.Publisher;

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
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/9/26 0026.
 */

public class RxOperatorsObservableUtils {


    /**
     * 使用多线程订阅
     *
     * @param observable 数据发送器（上游）
     * @param <T>        数据类型
     */
    public static <T> Observable observerAsync(Observable<T> observable) {
        return observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io());
    }

    /**
     * 普通数据流
     *
     * @param observable                   数据发送器（上游）
     * @param progressObservableSubscriber 下游接收器（下游）
     * @param <T>                          数据类型
     */
    public static <T> void observerNormal(Observable<T> observable, ProgressObservableSubscriber<T> progressObservableSubscriber) {
        observable
                .subscribe(progressObservableSubscriber);
    }

    /**
     * 普通数据流doOnNext
     *
     * @param observable                   数据发送器（上游）
     * @param progressObservableSubscriber 下游接收器（下游）
     * @param doOnNext                     接收数据前的操作器，通常用于保存数据持久化
     * @param <T>                          数据类型
     */
    public static <T> void observerNormal(Observable<T> observable, Consumer<T> doOnNext, ProgressObservableSubscriber<T> progressObservableSubscriber) {
        observable
                .doOnNext(doOnNext)
                .subscribe(progressObservableSubscriber);
    }

    /**
     * map过滤订阅
     *
     * @param observable                   数据发送器（上游）
     * @param function                     数据过滤器
     * @param progressObservableSubscriber 下游接收器（下游）
     * @param <T>                          初始数据类型
     * @param <K>                          过滤之后的数据类型
     */
    public static <T, K> void observerMap(Observable<T> observable,
                                          Function<T, K> function,
                                          ProgressObservableSubscriber<K> progressObservableSubscriber) {
        observable
                .map(function)
                .subscribe(progressObservableSubscriber);
    }

    /**
     * map过滤订阅doOnNext
     *
     * @param observable                   数据发送器（上游）
     * @param doOnNext                     接收数据前的操作器，通常用于保存数据持久化
     * @param function                     数据过滤器
     * @param progressObservableSubscriber 下游接收器（下游）
     * @param <T>                          初始数据类型
     * @param <K>                          过滤之后的数据类型
     */
    public static <T, K> void observerMap(Observable<T> observable,
                                          Consumer<T> doOnNext,
                                          Function<T, K> function,
                                          ProgressObservableSubscriber<K> progressObservableSubscriber) {
        observable
                .doOnNext(doOnNext)
                .map(function)
                .subscribe(progressObservableSubscriber);
    }

    /**
     * zip合并订阅
     *
     * @param observableT                   第一个数据流
     * @param observableK                   第二个数据流
     * @param biFunction                    合并操作器
     * @param progressObservableSubscriberR 合并之后的数据接收器
     * @param <T>                           第一个数据流的数据类型
     * @param <K>                           第二个数据流的数据类型
     * @param <R>                           第三个数据流的数据类型
     */
    public static <T, K, R> void observerZip(Observable<T> observableT,
                                             Observable<K> observableK,
                                             BiFunction<T, K, R> biFunction,
                                             ProgressObservableSubscriber<R> progressObservableSubscriberR) {
        Observable.zip(observableT, observableK, biFunction)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(progressObservableSubscriberR);
    }

    /**
     * zip合并订阅
     *
     * @param observableT                   第一个数据流
     * @param observableK                   第二个数据流
     * @param biFunction                    合并操作器
     * @param doOnNext                      接收数据前的操作器，通常用于保存数据持久化
     * @param progressObservableSubscriberR 合并之后的数据接收器
     * @param <T>                           第一个数据流的数据类型
     * @param <K>                           第二个数据流的数据类型
     * @param <R>                           第三个数据流的数据类型
     */
    public static <T, K, R> void observerZip(Observable<T> observableT,
                                             Observable<K> observableK,
                                             BiFunction<T, K, R> biFunction,
                                             Consumer<R> doOnNext,
                                             ProgressObservableSubscriber<R> progressObservableSubscriberR) {
        Observable.zip(observableT, observableK, biFunction)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(progressObservableSubscriberR);
    }

    //concat操作符用于连接两个相同数据类型的数据流，不同于zip，他是两个数据流按顺序发送数据
    public static <T> void observerConcat(Observable<T> observableT,
                                          Observable<T> observableK,
                                          ProgressObservableSubscriber<T> progressObservableSubscriberR) {

        Observable.concat(observableT, observableK)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(progressObservableSubscriberR);
    }


    /**
     * flatMap操作符是将一个Observable数据流发送的数据分成多个observable数据流
     * 比如observable发送了data1，data2，data3，
     * 那么flatMap会分解运行三次，可以在flapMap的每一次中重新实例化observable来发送想要的数据
     * flatMap分解之后的observable是无序的*****
     *
     * @param observableT                  数据源
     * @param function                     flatMap转换器
     * @param progressObservableSubscriber 转换后的数据源下游
     * @param <T>                          源数据类型
     * @param <R>                          转换后数据类型
     */
    public static <T, R> void observerFlatMap(Observable<T> observableT,
                                              Function<T, ObservableSource<R>> function,
                                              ProgressObservableSubscriber<R> progressObservableSubscriber) {
        observableT.subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .flatMap(function).subscribe(progressObservableSubscriber);
    }

    /**
     * 和flatMap一样
     * 注意：concatMap分解之后是有序的*****
     *
     * @param observableT                  数据源
     * @param function                     flatMap转换器
     * @param progressObservableSubscriber 转换后的数据源下游
     * @param <T>                          源数据类型
     * @param <R>                          转换后数据类型
     */
    public static <T, R> void observerConcatMap(Observable<T> observableT,
                                                Function<T, ObservableSource<R>> function,
                                                ProgressObservableSubscriber<R> progressObservableSubscriber) {
        observableT.subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .concatMap(function).subscribe(progressObservableSubscriber);
    }

    /**
     * 筛选数据操作符
     *
     * @param observable                   数据源
     * @param predicate                    筛选器
     * @param progressObservableSubscriber 筛选后的接收器
     * @param <T>                          数据类型
     */
    public static <T> void observerFilter(Observable<T> observable,
                                          Predicate<T> predicate,
                                          ProgressObservableSubscriber<T> progressObservableSubscriber) {
        observable.subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .filter(predicate)
                .subscribe(progressObservableSubscriber);

    }

    /**
     * buffer 操作符接受两个参数，buffer(count,skip)，
     * 作用是将 Observable 中的数据按 skip (步长) 分成最大不超过 count 的 buffer ，
     * 然后生成一个  Observable
     *
     * @param observable                   数据源
     * @param count                        每组数据长度上限
     * @param skip                         每组数据起始位置相对于上一组的间隔
     * @param progressObservableSubscriber 新的接收器
     * @param <T>                          数据类型
     */
    public static <T> void observerBuffer(Observable<T> observable,
                                          int count,
                                          int skip,
                                          ProgressObservableSubscriber<List<T>> progressObservableSubscriber) {
        observable.subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .buffer(count, skip)
                .subscribe(progressObservableSubscriber);

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
        return Observable.timer(delay, TimeUnit.MILLISECONDS)
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
        return Observable.interval(initDelay, delay, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(consumer);

    }

    public static <T> void subscriberDoOnNext() {

    }

}
