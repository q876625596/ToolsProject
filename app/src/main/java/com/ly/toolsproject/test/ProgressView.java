package com.ly.toolsproject.test;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.ly.toolsproject.R;
import com.ly.toolsproject.util.LogUtils;

/**
 * Created by Administrator on 2017/9/27 0027.
 */

public class ProgressView extends View {

    private Context mContext;
    private float progress = 0;
    private Paint progressPaint;
    private TextPaint textPaint;
    private float textX;
    private float textY;
    RectF rectF = new RectF(500, 500, 800, 800);
    RectF rectF1 = new RectF(520, 520, 780, 780);
    private boolean isDrawFirst = false;

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        progressPaint = new Paint();
        textPaint = new TextPaint();
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(60);
        progressPaint.setColor(ContextCompat.getColor(context, R.color.colorAccent));
        progressPaint.setAntiAlias(true);
        progressPaint.setStyle(Paint.Style.STROKE);
        progressPaint.setStrokeJoin(Paint.Join.ROUND);
        progressPaint.setStrokeCap(Paint.Cap.ROUND);
        progressPaint.setStrokeWidth(20);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textX = rectF.left + (rectF.right - rectF.left) / 2;
        textY = rectF.top + (rectF.bottom - rectF.top) / 2 + (textPaint.getFontMetrics().leading - textPaint.getFontMetrics().ascent) / 2;
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        isDrawFirst = true;
        progressPaint.setColor(Color.WHITE);
        progressPaint.setStrokeCap(Paint.Cap.SQUARE);
        progressPaint.setStrokeWidth(5);
        for (int i = 45; i <= 315; i += 15) {
            LogUtils.e("i:" + i);
            if (i >= 45 && i <= 90) {
                canvas.drawLine(650 - ((float) Math.sin(i * Math.PI / 180) * 130),
                        650 + ((float) Math.cos(i * Math.PI / 180) * 130),
                        650 - ((float) Math.sin(i * Math.PI / 180) * 130) * 9 / 10,
                        650 + ((float) Math.cos(i * Math.PI / 180) * 130) * 9 / 10,
                        progressPaint);
            } else if (i > 90 && i <= 180) {
                canvas.drawLine(650 - ((float) Math.cos((i - 90) * Math.PI / 180) * 130),
                        650 - ((float) Math.sin((i - 90) * Math.PI / 180) * 130),
                        650 - ((float) Math.cos((i - 90) * Math.PI / 180) * 130) * 9 / 10,
                        650 - ((float) Math.sin((i - 90) * Math.PI / 180) * 130) * 9 / 10,
                        progressPaint);
            } else if (i > 180 && i <= 270) {
                canvas.drawLine(650 + ((float) Math.sin((i - 180) * Math.PI / 180) * 130),
                        650 - ((float) Math.cos((i - 180) * Math.PI / 180) * 130),
                        650 + ((float) Math.sin((i - 180) * Math.PI / 180) * 130) * 9 / 10,
                        650 - ((float) Math.cos((i - 180) * Math.PI / 180) * 130) * 9 / 10,
                        progressPaint);
            } else {
                canvas.drawLine(650 + ((float) Math.cos((i - 270) * Math.PI / 180) * 130),
                        650 + ((float) Math.sin((i - 270) * Math.PI / 180) * 130),
                        650 + ((float) Math.cos((i - 270) * Math.PI / 180) * 130) * 9 / 10,
                        650 + ((float) Math.sin((i - 270) * Math.PI / 180) * 130) * 9 / 10,
                        progressPaint);
            }
        }
        progressPaint.setColor(ContextCompat.getColor(mContext, R.color.colorAccent));
        progressPaint.setStrokeCap(Paint.Cap.ROUND);
        progressPaint.setStrokeWidth(20);
        canvas.drawArc(rectF, 135, progress * 2.7f, false, progressPaint);
        canvas.drawText(String.valueOf((int) progress), textX, textY, textPaint);

    }

    public void startProgress() {
        Keyframe keyframe1 = Keyframe.ofFloat(0f, 0f);
        Keyframe keyframe2 = Keyframe.ofFloat(0.4f, 100f);
        Keyframe keyframe3 = Keyframe.ofFloat(1f, 85f);
        PropertyValuesHolder propertyValuesHolder = PropertyValuesHolder.ofKeyframe("progress", keyframe1, keyframe2, keyframe3);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(this, propertyValuesHolder);
        animator.setDuration(1000L);
        // 执行动画
        animator.start();
    }
}
