package com.ly.toolsproject;

import android.animation.Animator;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.ly.toolsproject.base.BaseActivity;
import com.ly.toolsproject.test.ProgressView;
import com.ly.toolsproject.util.AnimationUtils;
import com.ly.toolsproject.util.LogUtils;
import com.mindorks.nybus.NYBus;
import com.mindorks.nybus.event.Channel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends BaseActivity {


    private TextView open;
    private TextView close;
    private EditText commentEdit;
    private TextView messge;
    private ImageView image;
    private ImageView image2;
    private ProgressView progress;
    private FrameLayout fragment1;
    private FrameLayout fragment2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        open = (TextView) findViewById(R.id.open);
        close = (TextView) findViewById(R.id.close);
        commentEdit = (EditText) findViewById(R.id.commentEdit);
        messge = (TextView) findViewById(R.id.messge);
        image = (ImageView) findViewById(R.id.image);
        image2 = (ImageView) findViewById(R.id.image2);
        progress = (ProgressView) findViewById(R.id.progress);
        fragment1 = (FrameLayout) findViewById(R.id.fragment1);
        fragment2 = (FrameLayout) findViewById(R.id.fragment2);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment1,new Fragment1());
        fragmentTransaction.add(R.id.fragment2,new Fragment2());
        fragmentTransaction.commit();
        setTransparentStatusBar(false);
        //setStatusColor(ContextCompat.getColor(this,R.color.colorAccent),false);
        setStatusTextColorMain(true);

        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NYBus.get().post("sasasas", Channel.ONE);
               /* Map<String, String> map = HttpRetrofit.getStringMap();
                map.put("lat", "");
                map.put("lng", "");
                map.put("page", "1");
                HttpRetrofit.observerAsync(HttpRetrofit.getDefaultApiManager().getDataTwo(HttpRetrofit.HEAD_URL + HttpInterface.HOME, map))
                        .subscribe(new ProgressObservableSubscriber<Response>(MainActivity.this) {
                            @Override
                            public void onNext(Response response) {
                                super.onNext(response);
                                Gson gson = new Gson();
                                LogUtils.e(gson.toJson(response));
                                MainDataResponse data = (MainDataResponse) response.getData();
                                LogUtils.e(data.getAd().getNormal().getAdvertise_content());
                            }
                        });*/
                //progress.startProgress();
               /* Flowable<String> flowableT = Flowable.create(new FlowableOnSubscribe<String>() {
                    @Override
                    public void subscribe(@NonNull FlowableEmitter<String> e) throws Exception {
                        for (int i = 0; i < 100; i++) {
                            e.onNext("数据" + (i + 1));
                        }
                        e.onComplete();
                    }

                }, BackpressureStrategy.BUFFER);
                Observable<String> observableT = Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                        for (int i = 0; i < 100; i++) {
                            e.onNext("数据" + (i + 1));
                        }
                        e.onComplete();
                    }

                });*/
                /*RxOperatorsObservableUtils.subscriberFlatMap(observableT, new Function<String, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(@NonNull final String s) throws Exception {
                        List<String> list = new ArrayList<>();
                        list.add(s);
                        list.add(s);
                        list.add(s);
                        return Observable.fromIterable(list).delay(10L, TimeUnit.MILLISECONDS);
                    }
                }, new ProgressObservableSubscriber<String>(MainActivity.this) {
                    @Override
                    public void onNext(String s) {
                        super.onNext(s);
                        LogUtils.e(s);
                    }
                });*/
               /* RxOperatorsFlowbleUtils.subscriberConcatMap(flowableT, new Function<String, Publisher<String>>() {
                    @Override
                    public Publisher<String> apply(@NonNull final String s) throws Exception {
                        List<String> list = new ArrayList<String>();
                        list.add(s);
                        list.add(s);
                        list.add(s);
                        return Flowable.fromIterable(list).delay(10L, TimeUnit.MILLISECONDS);
                    }
                }, new ProgressFlowableSubscriber<String>(MainActivity.this) {
                    @Override
                    public void onNext(String s) {
                        super.onNext(s);
                        LogUtils.e(s);
                    }

                });*/
                /*try {
                    Class<?> aClass = Class.forName("com.ly.toolsproject.util.AnimationUtils");
                    Class<AnimationUtils> animationUtilsClass = AnimationUtils.class;
                    Field[] declaredFields = animationUtilsClass.getDeclaredFields();
                    AnimationUtils animationUtils = animationUtilsClass.newInstance();
                    Field aaa = aClass.getDeclaredField("aaa");
                    aaa.setAccessible(true);
                    aaa.setInt(animationUtils, 2);
                    LogUtils.e("aaa:"+aaa.get(animationUtils));
                } catch (ClassNotFoundException e) {
                    LogUtils.e("ClassNotFoundException:"+e.getMessage());
                    e.printStackTrace();
                } catch (NoSuchFieldException e) {
                    LogUtils.e("NoSuchFieldException:"+e.getMessage());
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    LogUtils.e("IllegalAccessException:"+e.getMessage());
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    LogUtils.e("InstantiationException:"+e.getMessage());
                    e.printStackTrace();
                }*/

                //showSoftKeyboard(commentEdit);
                //LogUtils.e("aaaa:" + BezierUtils.method(9, 5));
               /* List<Point> points = new ArrayList<>();
                points.add(new Point((int) image2.getX() - 225, (int) image2.getY() - 350));
                points.add(new Point((int) image2.getX() - 400, (int) image2.getY() - 175));
                AnimationUtils.bezierMultiAnimation(image2,
                        new Point((int) image2.getX(), (int) image2.getY()),
                        new Point((int) image2.getX() - 400, (int) image2.getY()), points);*/
                /*AnimationUtils.ScaleBean scaleBean = new AnimationUtils.ScaleBean(1f, 0f, 1f, 0f, 0.5f, 0.5f);
                Animator animator1 = AnimationUtils.scaleAnimationObject(image2, scaleBean, 1000L, null, null, null);
                Animator animator = AnimationUtils.transAnimationObject(image,
                        new Point((int) image.getX(), (int) image.getY()),
                        new Point((int) image.getX() + 100, (int) image.getY() + 100),
                        1000L,
                        null,
                        null,
                        null);
                Animator animator2 = AnimationUtils.multiPropertyAnimation(image, 1000L, null, null, null,
                        PropertyValuesHolder.ofFloat("X", image.getX() + 100, image.getX() - 200),
                        PropertyValuesHolder.ofFloat("Y", image.getY() + 100, image.getY() - 200),
                        PropertyValuesHolder.ofFloat("scaleX", scaleBean.getStartX(), scaleBean.getEndX()),
                        PropertyValuesHolder.ofFloat("scaleY", scaleBean.getStartY(), scaleBean.getEndY()));
                Animator animator3 = AnimationUtils.bezierThreeAnimation(image,
                        new Point((int) image.getX(), (int) image.getY()),
                        new Point((int) image.getX(), (int) image.getY() - 600),
                        new Point((int) image.getX() + 200, (int) image.getY() - 200),
                        new Point((int) image.getX() - 200, (int) image.getY() - 400), 1000L, new AccelerateDecelerateInterpolator(), null, null);
                animator3.start();*/
                //Animator animator4 = AnimationUtils.multiAnimationObjectSequence(animator3, animator1);
                //AnimationUtils.multiAnimationObjectSequence(animator, animator1, animator2,animator4);

            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.e("x" + image2.getX());
                LogUtils.e("y" + image2.getY());
                // hideSoftKeyboard(commentEdit);
                List<Point> points = new ArrayList<>();
                points.add(new Point((int) image2.getX(), (int) image2.getY() - 25));
                points.add(new Point((int) image2.getX() - 25, (int) image2.getY() - 50));
                points.add(new Point((int) image2.getX() - 50, (int) image2.getY() - 50));
                points.add(new Point((int) image2.getX() - 75, (int) image2.getY() - 50));
                points.add(new Point((int) image2.getX() - 100, (int) image2.getY() - 25));
                points.add(new Point((int) image2.getX() - 100, (int) image2.getY()));
                points.add(new Point((int) image2.getX() - 100, (int) image2.getY() + 50));
                points.add(new Point((int) image2.getX() - 50, (int) image2.getY() + 100));
                points.add(new Point((int) image2.getX(), (int) image2.getY() + 100));
                points.add(new Point((int) image2.getX() + 50, (int) image2.getY() + 100));
                points.add(new Point((int) image2.getX() + 100, (int) image2.getY() + 50));
                points.add(new Point((int) image2.getX() + 100, (int) image2.getY()));
                points.add(new Point((int) image2.getX() + 100, (int) image2.getY() - 75));
                points.add(new Point((int) image2.getX() + 25, (int) image2.getY() - 150));
                points.add(new Point((int) image2.getX() - 50, (int) image2.getY() - 150));
                points.add(new Point((int) image2.getX() - 125, (int) image2.getY() - 150));
                points.add(new Point((int) image2.getX() - 200, (int) image2.getY() - 75));
                points.add(new Point((int) image2.getX() - 200, (int) image2.getY()));
                points.add(new Point((int) image2.getX() - 200, (int) image2.getY() + 100));
                points.add(new Point((int) image2.getX() - 100, (int) image2.getY() + 200));
                points.add(new Point((int) image2.getX(), (int) image2.getY() + 200));
                points.add(new Point((int) image2.getX() + 100, (int) image2.getY() + 200));
                points.add(new Point((int) image2.getX() + 200, (int) image2.getY() + 100));
                points.add(new Point((int) image2.getX() + 200, (int) image2.getY()));
                points.add(new Point((int) image2.getX() + 200, (int) image2.getY() - 125));
                points.add(new Point((int) image2.getX() + 75, (int) image2.getY() - 250));
                points.add(new Point((int) image2.getX() - 50, (int) image2.getY() - 250));
                points.add(new Point((int) image2.getX() - 175, (int) image2.getY() - 250));
                points.add(new Point((int) image2.getX() - 300, (int) image2.getY() - 125));
                points.add(new Point((int) image2.getX() - 300, (int) image2.getY()));
                points.add(new Point((int) image2.getX() - 300, (int) image2.getY() + 150));
                points.add(new Point((int) image2.getX() - 200, (int) image2.getY() + 300));
                points.add(new Point((int) image2.getX() - 50, (int) image2.getY() + 300));
                points.add(new Point((int) image2.getX() + 100, (int) image2.getY() + 300));
                points.add(new Point((int) image2.getX() + 250, (int) image2.getY() + 150));
                points.add(new Point((int) image2.getX() + 250, (int) image2.getY()));
                points.add(new Point((int) image2.getX() + 250, (int) image2.getY() - 175));
                points.add(new Point((int) image2.getX() + 125, (int) image2.getY() - 350));
                points.add(new Point((int) image2.getX() - 50, (int) image2.getY() - 350));
                points.add(new Point((int) image2.getX() - 225, (int) image2.getY() - 350));
                points.add(new Point((int) image2.getX() - 400, (int) image2.getY() - 175));
                Animator animator = AnimationUtils.bezierMultiAnimation(image2,
                        new Point((int) image2.getX(), (int) image2.getY()),
                        new Point((int) image2.getX() - 400, (int) image2.getY()), points, 3000L, new AccelerateDecelerateInterpolator(), null, null);
                animator.start();
            }
        });
        messge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //AnimationUtils.alphaAnimationObject(image,1,0,true);
                //AnimationUtils.transAnimationObject(image,image.getY(),image.getY()+100,true);
                //AnimationUtils.transAnimationObjectXY(image, image.getX(), image.getX() + 100, image.getY(), image.getY() + 100);
               /* AnimationUtils.bezierTwoAnimation(image,
                        new Point((int) image.getX(), (int) image.getY()),
                        new Point((int) image.getX()+300, (int) image.getY()+300),
                        new Point((int) image.getX()+200, (int) image.getY()));*/
                AnimationUtils.bezierThreeAnimation(image,
                        new Point((int) image.getX(), (int) image.getY()),
                        new Point((int) image.getX(), (int) image.getY() - 600),
                        new Point((int) image.getX() + 200, (int) image.getY() - 200),
                        new Point((int) image.getX() - 200, (int) image.getY() - 400), 1000L, new AccelerateDecelerateInterpolator(), null, null);
            }
        });
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //AnimationUtils.alphaAnimationObject(image,0,1,true);
            }
        });
    }


    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {

    }
}
