package com.tools.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.liu.abing.R;
import com.orhanobut.logger.Logger;

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
public class CustomA extends View {

    private Paint paint1;
    private Paint paint2;
    private Paint paint3;
    private Paint paint4;
    private Paint mPaint;
    private Paint paint5;
    private Paint paint6;

    private float mHourPointWidth = 8; //时针宽度
    private float mMinutePointWidth = 5; //分针宽度
    private float mSecondPointWidth = 3; //秒针宽度
    private int mPointRadius = 10; // 指针圆角
    private float mRadius = 200; //半径
    private  Bitmap mBitmap;

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

        canvas.drawText("兵",getWidth()/2-90,getHeight()/2+85,paint5);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, mRadius, paint1);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, 170, paint2);
        drawHourNumbers(canvas, paint3, paint4, getWidth() / 2, getHeight() / 2);
        drawMinuteLine(canvas, paint3, getWidth() / 2, getHeight() / 2);

        paintPointer(canvas, getWidth() / 2, getHeight() / 2);

        canvas.restore();
        //刷新
        postInvalidateDelayed(1000);

//        canvas.drawBitmap(mBitmap,getWidth()/2,getHeight()/2, paint6);

    }

    private void initPaint() {
        //最外层圆
        paint1 = new Paint();
        paint1.setColor(getResources().getColor(R.color.colorGreen));
        paint1.setStrokeWidth(40);
        paint1.setAntiAlias(true);//消除锯齿
        paint1.setStyle(Paint.Style.STROKE); //设置空心

        //最外层第二层圆
        paint2 = new Paint();
        paint2.setColor(getResources().getColor(R.color.colorGreen));
        paint2.setStrokeWidth(10);
        paint2.setAntiAlias(true);//消除锯齿
        paint2.setStyle(Paint.Style.STROKE); //设置空心

        //画平均线
        paint3 = new Paint();
        paint3.setColor(getResources().getColor(R.color.white));
        paint2.setAntiAlias(true);//消除锯齿

        //小时的字
        paint4 = new Paint();
        paint4.setColor(getResources().getColor(R.color.black));
        paint4.setAntiAlias(true);//消除锯齿

        //指针的画笔
        mPaint = new Paint();
        mPaint.setAntiAlias(true);

        paint5=new Paint();
        paint5.setAntiAlias(true);//去除锯齿
        paint5.setFilterBitmap(true);//对位图进行滤波处理
        paint5.setTextSize(190);
        paint5.setColor(getResources().getColor(R.color.shadow));



        paint6 = new Paint();
        paint6.setColor(Color.RED);
        paint6.setAntiAlias(true);
        paint6.setStyle(Paint.Style.STROKE);
        paint6.setStrokeWidth(5f);

        mBitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888); // ����һ��bitmap
       final Canvas mCanvas = new Canvas(mBitmap);

        new Thread() {
            @Override
            public void run() {
                super.run();
                drawLove(mCanvas);

            }
        }.start();
    }


    /**
     * 画小时的线
     *
     * @param canvas
     * @param paint
     * @param width
     * @param height
     */
    private void drawHourNumbers(Canvas canvas, Paint paint, Paint paint4, int width, int height) {
        int i = 0;
        for (i = 1; i < 13; i++) {

            canvas.save(); //save current state of canvas.
            canvas.rotate(360 / 12 * i, width, height);
            //绘制表盘
            canvas.drawLine(width, height - 190, width, height - 210, paint);
////            //绘制文字
            canvas.drawText("" + i, width, height - 150, paint4);
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
            canvas.rotate(360 / 12 / 5 * i, width, height);
            //绘制表盘
            canvas.drawLine(width, height - 190, width, height - 200, paint);
            //恢复开始位置
            canvas.restore();
        }
    }

    /**
     * 绘制时分秒针
     */

    private void paintPointer(Canvas canvas, int width, int height) {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY); //时
        int minute = calendar.get(Calendar.MINUTE); //分
        int second = calendar.get(Calendar.SECOND); //秒
        int angleHour = (hour % 12) * 360 / 12; //时针转过的角度
        int angleMinute = minute * 360 / 60; //分针转过的角度
        int angleSecond = second * 360 / 60; //秒针转过的角度
//        //绘制时针
        canvas.save();
        canvas.rotate(angleHour,width,height); //旋转到时针的角度
        RectF rectFHour = new RectF(width-3,height-70,width+3, height+10);
        mPaint.setColor(getResources().getColor(R.color.black)); //设置指针颜色
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mHourPointWidth); //设置边界宽度
        canvas.drawRoundRect(rectFHour, mPointRadius, mPointRadius, mPaint); //绘制时针
        canvas.restore();
////        //绘制分针
        canvas.save();
        canvas.rotate(angleMinute,width,height);
        RectF rectFMinute = new RectF(width-2,height-150,width+2, height+14);
        mPaint.setColor(getResources().getColor(R.color.user_bottom_text));
        mPaint.setStrokeWidth(mMinutePointWidth);
        canvas.drawRoundRect(rectFMinute, mPointRadius, mPointRadius, mPaint);
        canvas.restore();
////        //绘制秒针
        canvas.save();
        canvas.rotate(angleSecond,width,height);
        RectF rectFSecond = new RectF(width-1,height-200, width+1,height+18);
        mPaint.setColor(getResources().getColor(R.color.colorRed));
        mPaint.setStrokeWidth(mSecondPointWidth);
        canvas.drawRoundRect(rectFSecond, mPointRadius, mPointRadius, mPaint);
        canvas.restore();
        //绘制中心小圆
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(getResources().getColor(R.color.red));
        canvas.drawCircle(width, height, mPointRadius, mPaint);
    }

    private void drawLove(Canvas canvas) {
        // (17*(x^2))-(16*abs(x)*y)+(17*(y^2))<255 x(-5,5) y(-5,5) (���κ�������ʽ)
        int loveWidth = 30;// ���Ϳ�ȣ�������ż��
        int oneLine = loveWidth / 2;// һ���᳤��

        float scale = oneLine / 5f;// ʵ��������Ϸ���ʽ���꣬����

        for (int i = 0; i < oneLine; i++) {
            for (int j = 0; j < oneLine; j++) {

                // ���ݱ��ʽxy�ķ�Χ������Ҫ������ϵ�ķ�ΧҲ��С
                float xf = i / scale;
                float yf = j / scale;

                if ((17 * Math.pow(xf, 2) - 16 * Math.abs(xf) * yf + 17 * Math
                        .pow(yf, 2)) < 255) {

                    // ����1
                    // canvas.drawPoint(xf*scale+250,250+yf*scale, paint);
                    // ����2
                    canvas.drawPoint(250 - xf * scale, 250 - yf * scale, mPaint);
                    // this.postInvalidateDelayed(10);

                    // ����1
                    // canvas.drawPoint(-xf*scale+250,250+yf*scale, paint);
                    // ����2
                    canvas.drawPoint(250 + xf * scale, 250 - yf * scale, mPaint);
                    // this.postInvalidateDelayed(10);

                    // Log.i("TAG",
                    // "xf-->"+(xf*scale+250)+"yf-->"+(250-yf*scale));
                }

                if ((17 * Math.pow(xf, 2) - 16 * Math.abs(xf) * (-yf) + 17 * Math
                        .pow(yf, 2)) < 255) {

                    // ����2
                    canvas.drawPoint(250 - xf * scale, 250 + yf * scale, mPaint);
                    // ���� 1
                    // canvas.drawPoint(250+xf*scale,250-yf*scale, paint);

                    // this.postInvalidateDelayed(10);

                    // ����2
                    canvas.drawPoint(250 + xf * scale, 250 + yf * scale, mPaint);
                    // ���� 1
                    // canvas.drawPoint(250-xf*scale,250-yf*scale, paint);

                    // this.postInvalidateDelayed(10);

                }

                // ��ʱˢ�£���������Ч��
                delayedTime();
                this.postInvalidate();
            }
        }// end for

    }

    private void delayedTime() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
