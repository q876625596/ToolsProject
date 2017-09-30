package com.ly.toolsproject;

import com.ly.toolsproject.util.LogUtils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void basicMain() {

        //创建基本流，Observable可以替换成其他的，比如Flowable（支持背压）
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                //上游发送数据
                e.onNext("数据1");
                //如果数据全部发送完成，必须调用onComplete来完成数据的发送
                e.onComplete();
                //一旦调用了onComplete，那么之后无论怎么操作都不会再发送数据
                e.onNext("数据2");
                e.onNext("数据3");
                e.onComplete();
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("onSubscribe");
            }

            @Override
            public void onNext(@NonNull String s) {
                //下游接收数据
                System.out.println(s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("onError");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });
    }

    @Test
    public void mapMain() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                //上游发送数据
                e.onNext("数据1");
                e.onNext("数据2");
                e.onNext("数据3");
                e.onComplete();
            }
            //map可以对每一次发送的事件进行操作,并返回相应的数据
        }).map(new Function<String, String>() {
            @Override
            public String apply(@NonNull String s) throws Exception {
                return "我是新的" + s;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("onSubscribe");
            }

            @Override
            public void onNext(@NonNull String s) {
                //下游接收数据
                System.out.println(s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("onError");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });
    }

    //zip操作符用于合并数据，将两个数据流合并，并按顺序一一配对，最终只输出配对成功的数据（输出数据的条数为发送事件最少的流的条数）
    @Test
    public void zipMain() {
        Observable<String> observable1 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                //observable1发送了10条数据
                e.onNext("数据1");
                e.onNext("数据2");
                e.onNext("数据3");
                e.onNext("数据4");
                e.onNext("数据5");
                e.onNext("数据6");
                e.onNext("数据7");
                e.onNext("数据8");
                e.onNext("数据9");
                e.onNext("数据10");
                e.onComplete();
            }
        });
        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                //observable2发送了6条数据
                e.onNext("数据A");
                e.onNext("数据B");
                e.onNext("数据C");
                e.onNext("数据D");
                e.onNext("数据E");
                e.onNext("数据F");
                e.onComplete();
            }
        });

        Observable.zip(observable1, observable2, new BiFunction<String, String, String>() {
            @Override
            public String apply(@NonNull String s, @NonNull String s2) throws Exception {
                //依次配对,最终输出的数据为： 数据6配对数据F
                return s + "配对" + s2;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("onSubscribe");
            }

            @Override
            public void onNext(@NonNull String s) {
                //下游接收数据
                System.out.println(s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("onError");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });
    }

    //concat操作符用于连接两个数据流不同于zip，他是两个数据流按顺序发送数据
    @Test
    public void concatMain() {
        Observable<String> observable1 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                //observable1发送了10条数据
                e.onNext("数据1");
                e.onNext("数据2");
                e.onNext("数据3");
                e.onNext("数据4");
                e.onNext("数据5");
                e.onNext("数据6");
                e.onNext("数据7");
                e.onNext("数据8");
                e.onNext("数据9");
                e.onNext("数据10");
                e.onComplete();
            }
        });
        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                //observable2发送了6条数据
                e.onNext("数据A");
                e.onNext("数据B");
                e.onNext("数据C");
                e.onNext("数据D");
                e.onNext("数据E");
                e.onNext("数据F");
                e.onComplete();
            }
        });

        Observable.concat(observable1, observable2)
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        System.out.println("onSubscribe");
                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        //下游接收数据
                        System.out.println(s);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        System.out.println("onError");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete");
                    }
                });
    }


    //flatMap操作符是将一个Observable数据流发送的数据分成多个observable数据流
    //比如observable发送了data1，data2，data3，
    // 那么flatMap会分解运行三次，可以在flapMap的每一次中重新实例化observable来发送想要的数据
    //*****flatMap分解之后的observable是无序的*****
    @Test
    public void flatMapMain() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                //observable1发送了10条数据
                e.onNext("数据1");
                e.onNext("数据2");
                e.onNext("数据3");
                e.onNext("数据4");
                e.onNext("数据5");
                e.onNext("数据6");
                e.onNext("数据7");
                e.onNext("数据8");
                e.onNext("数据9");
                e.onNext("数据10");
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .flatMap(new Function<String, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(@NonNull String s) throws Exception {
                List<String> list = new ArrayList<>();
                list.add(s);
                list.add(s);
                list.add(s);
                return Observable.fromIterable(list).delay(10L, TimeUnit.MILLISECONDS);
            }
        }).subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        System.out.println("onSubscribe");
                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        //下游接收数据
                        System.out.println(s);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        System.out.println("onError");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete");
                    }
                });
    }
}