package com.basics.module_view.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.basics.module_view.R;

/**
 * @Author: xueshijie
 * @CreateDate: 2020-03-20 13:32
 * @Version: 1.0
 * @Description: java类作用描述
 */
public class CircleButton extends View {


    private Paint mPaint;
    private Rect mRect;
    /**弧的角度*/
    private float mAngle;

    private int backgroundColor;
    private float textSize;
    private String text;
    private int textColor;


    /**通过接口回调的方式实现控件的点击事件添加和处理
     * 步骤：
     *   1.首先定义一个接口 interface CirclebuttonClickListener
     *   2.创建接口变量  CirclebuttonClickListener mListener
     *   3.暴露一个方法给调用者来注册接口回调，通过接口来获得回调者对接口方法的实现
     *   */
    private CirclebuttonClickListener mListener;
    public interface CirclebuttonClickListener{
        void circleButtonClick();
    }
    public void setOnCirclebuttonClickListener(CirclebuttonClickListener listener){
        mListener = listener;
    }

    /**构造方法*/
    public CircleButton(Context context) {
        this(context, null);
    }

    public CircleButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }



    /**
     * Founction：Init the View
     * NLTE
     * 2016/5/15
     * @param context
     * @param attrs
     */
    private void initView(Context context, AttributeSet attrs) {
        mPaint = new Paint();
        //给Paint加上抗锯齿标志
        mPaint.setAntiAlias(true);
        mRect = new Rect();
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //方法一：
               /* number--;
                //刷新视图
                invalidate();*/

                //方法二：
                mListener.circleButtonClick();
            }
        });

        /*获取自定义属性并赋值*/
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleButton);

        backgroundColor = typedArray.getColor(R.styleable.CircleButton_backgroundColor, Color.RED);
        textColor = typedArray.getColor(R.styleable.CircleButton_textColor, Color.WHITE);
        textSize = typedArray.getDimension(R.styleable.CircleButton_textSize, 80);
        text = typedArray.getString(R.styleable.CircleButton_text);
        if (text == null){
            text = "";
        }
        //回收，避免浪费
        typedArray.recycle();

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //画圆
        mPaint.setColor(backgroundColor);
        //设置圆弧的宽度
        mPaint.setStrokeWidth(4);
        //设置圆弧
        mPaint.setStyle(Paint.Style.STROKE);
        /**
         * 第一 二个参数：暂且理解为圆的外切正方形左上角的坐标
         * 第三 四个参数：暂且理解为圆的外切正方形右下角的坐标
         * 第五 六个参数：绘制的起始点和终点
         * 第七个参数：是否绘制扇形
         * 第八个参数：画笔
         * */
        canvas.drawArc(10, 10, getWidth()-10, getHeight()-10, -90, -360+mAngle, true, mPaint);

        //中间有一个白色的数字 mRect是数字四周的边距
        mPaint.setColor(textColor);
        mPaint.setTextSize(textSize);
        mPaint.setTypeface(Typeface.DEFAULT);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(0.1f);
        mPaint.getTextBounds(text, 0, text.length(), mRect);
        int textWidth = mRect.width();
        int textHeight = mRect.height();
        canvas.drawText(text, (getWidth()-textWidth) / 2, (getHeight()+textHeight) / 2, mPaint);
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    @Override
    public void setBackgroundColor(int backgroundColor) {
        invalidate();
        this.backgroundColor = backgroundColor;
    }

    public float getTextSize() {
        return textSize;
    }

    public void setTextSize(float textSize) {
        invalidate();
        this.textSize = textSize;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        invalidate();
        this.text = text;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        invalidate();
        this.textColor = textColor;
    }

    public void setAngle(float angle) {
        mAngle = angle;
        invalidate();
    }
}
