package com.tools.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.liu.abing.R;

import java.util.Calendar;

/**
 * 项目名称：abing
 * 类描述：
 * 创建人：liubing
 * 创建时间：2017-5-12 16:58
 * 修改人：Administrator
 * 修改时间：2017-5-12 16:58
 * 修改备注：
 */
public class CustomA extends View{

    private Paint paint1;
    private Paint paint2;
    private Paint paint3;
    private Paint paint4;
    private Paint mPaint;

    private float mHourPointWidth=8; //时针宽度
    private float mMinutePointWidth=5; //分针宽度
    private float mSecondPointWidth=3; //秒针宽度
    private int mPointRadius=5; // 指针圆角
    private float mPointEndLength=20; //指针末尾的长度


    public CustomA(Context context) {
        super(context);

        initPaint();
    }

    public CustomA(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        canvas.drawCircle(getWidth()/2,getHeight()/2,200,paint1);
        canvas.drawCircle(getWidth()/2,getHeight()/2,170,paint2);
        drawHourNumbers(canvas,paint3,paint4,getWidth()/2,getHeight()/2);
        drawMinuteLine(canvas,paint3,getWidth()/2,getHeight()/2);

        paintPointer(canvas,getWidth()/2,getHeight()/2);
    }

    private void initPaint()
    {
        //最外层圆
        paint1=new Paint();
        paint1.setColor(getResources().getColor(R.color.colorGreen));
        paint1.setStrokeWidth(40);
        paint1.setAntiAlias(true);//消除锯齿
        paint1.setStyle(Paint.Style.STROKE); //设置空心

        //最外层第二层圆
        paint2=new Paint();
        paint2.setColor(getResources().getColor(R.color.colorGreen));
        paint2.setStrokeWidth(10);
        paint2.setAntiAlias(true);//消除锯齿
        paint2.setStyle(Paint.Style.STROKE); //设置空心

        //画平均线
        paint3=new Paint();
        paint3.setColor(getResources().getColor(R.color.white));
        paint2.setAntiAlias(true);//消除锯齿

        //小时的字
        paint4=new Paint();
        paint4.setColor(getResources().getColor(R.color.black));
        paint4.setAntiAlias(true);//消除锯齿

        //指针的画笔
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
    }


    /**
     * 画小时的线
     *
     * @param canvas
     * @param paint
     * @param width
     * @param height
     */
    private void drawHourNumbers(Canvas canvas, Paint paint,Paint paint4, int width, int height) {
        int i = 0;
        for (i = 1; i < 13; i++) {

            canvas.save(); //save current state of canvas.
            canvas.rotate(360/12*i, width, height);
            //绘制表盘
            canvas.drawLine(width, height-190, width,height-210, paint);
////            //绘制文字
            canvas.drawText("" + i, width, height-150, paint4);
            canvas.restore();
        }
    }
    /**
     * 画分钟的线
     *
     * @param canvas
     * @param paint
     * @param width
     * @param height
     */
    private void drawMinuteLine(Canvas canvas, Paint paint, int width, int height) {

        for (int i = 1; i < 61; i++) {

            canvas.save(); //save current state of canvas.
            canvas.rotate(360 / 12 / 5 * i, width , height);
            //绘制表盘
            canvas.drawLine(width, height-190, width,height-200, paint);
            //恢复开始位置
            canvas.restore();
        }
    }
    /**
     * 绘制时分秒针
     */

    private void paintPointer(Canvas canvas,int width, int height)
    {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY); //时
        int minute = calendar.get(Calendar.MINUTE); //分
        int second = calendar.get(Calendar.SECOND); //秒
        int angleHour = (hour % 12) * 360 / 12; //时针转过的角度
        int angleMinute = minute * 360 / 60; //分针转过的角度
        int angleSecond = second * 360 / 60; //秒针转过的角度
        //绘制时针
        canvas.save();
        canvas.rotate(angleHour); //旋转到时针的角度
        RectF rectFHour = new RectF(-mHourPointWidth / 2, -200 * 3 / 5, mHourPointWidth / 2, mPointEndLength);
        mPaint.setColor(getResources().getColor(R.color.black)); //设置指针颜色
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(6); //设置边界宽度
        canvas.drawRoundRect(rectFHour, width, height, mPaint); //绘制时针
        canvas.restore();
//        //绘制分针
//        canvas.save();
//        canvas.rotate(angleMinute);
//        RectF rectFMinute = new RectF(-mMinutePointWidth / 2, -mRadius * 3.5f / 5, mMinutePointWidth / 2, mPointEndLength);
//        mPaint.setColor(mMinutePointColor);
//        mPaint.setStrokeWidth(mMinutePointWidth);
//        canvas.drawRoundRect(rectFMinute, mPointRadius, mPointRadius, mPaint);
//        canvas.restore();
//        //绘制秒针
//        canvas.save();
//        canvas.rotate(angleSecond);
//        RectF rectFSecond = new RectF(-mSecondPointWidth / 2, -mRadius + 15, mSecondPointWidth / 2, mPointEndLength);
//        mPaint.setColor(mSecondPointColor);
//        mPaint.setStrokeWidth(mSecondPointWidth);
//        canvas.drawRoundRect(rectFSecond, mPointRadius, mPointRadius, mPaint);
//        canvas.restore();
        //绘制中心小圆
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(getResources().getColor(R.color.red));
        canvas.drawCircle(width,height,10, mPaint);
    }

}
