package com.tools.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 项目名称：SecondPay
 * 类描述： 广告上下跳动轮番
 * 创建人：liubing
 * 创建时间：2016/11/24 13:39
 * 修改人：Administrator
 * 修改时间：2016/11/24 13:39
 * 修改备注：
 */
public class UpRollView extends View {
    private Paint paint, paint_text;
    private Context context;
    private Bitmap[] bit;// 图片
    private String[] text;// 文字
    private int id = 0;
    private int y_offset = 0;// 偏移量
    private int delayed = 10;// 中间点延时
    private float width;
    private float height;
    private MyTimer mMyTimer;

    public UpRollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        // TODO Auto-generated constructor stub
    }

    public void init(Context context) {
        this.context = context;
        mMyTimer = new MyTimer();
        paint = new Paint();
        paint.setAntiAlias(true);// 抗锯齿
        paint.setStyle(Paint.Style.STROKE);// 填满
//        paint.setColor(context.getResources().getColor(R.color.colorAccent));
        paint_text = new Paint();
        paint_text.setAntiAlias(true);
        // paint_text.setTextAlign(Paint.Align.CENTER);//居中
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        width = canvas.getWidth();
        height = canvas.getHeight();
        paint.setStrokeWidth(height * 8 / 10);
        paint_text.setTextSize(height / 5);
        /**
         * 绘制图片
         */
        if (bit[id] != null && mMyTimer.isstart)
            canvas.drawBitmap(bit[id], new Rect(0, 0, bit[id].getWidth(), bit[id].getHeight()),
                    new RectF(0, height / 2 - y_offset, height, height * 3 / 2 - y_offset), paint);
        /**
         * 文字
         */
        if (text[id] != null && mMyTimer.isstart) {
            FontMetrics a = paint_text.getFontMetrics();
            float descent = a.descent;
            canvas.drawText(text[id], height + 20, height - y_offset + descent * 2, paint_text);
        }
    }

    class MyTimer {
        public Timer time;
        public int mydelayed = 0;
        Boolean isstart = false;

        public MyTimer() {
            time = new Timer();
            mydelayed = delayed;
        }

        public void start() {
            if (time != null)
                stop();
            y_offset = (int) (-height / 2);
            time = new Timer();
            isstart = true;
            time.schedule(new TimerTask() {
                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    if (y_offset < height / 2 + 5 && y_offset > height / 2 - 5 && mydelayed > 0) {
                        mydelayed--;
                        return;
                    }
                    mydelayed = delayed;
                    if (y_offset < height * 3 / 2) {
                        y_offset += 10;
                    } else {
                        y_offset = (int) (-height / 2);
                        if (text.length > id + 1) {
                            id++;
                        } else {
                            id = 0;
                        }
                    }
                    han.sendMessage(new Message());
                }
            }, 200, 30);
        }

        Handler han = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                // TODO Auto-generated method stub
                super.handleMessage(msg);
                invalidate();
            }
        };

        public void stop() {
            time.cancel();
            isstart = false;
            time = null;
        }
    }

    /**
     * String数组
     *
     * @param text
     */
    public void setText(String... text) {
        this.text = text;

        if (bit == null)
            bit = new Bitmap[text.length];
        mMyTimer.start();
    }

    /**
     * 图片id
     *
     * @param ids
     */
    public void setImageId(Integer... ids) {
        bit = new Bitmap[ids.length];
        if (text == null)
            text = new String[ids.length];
        for (int i = 0; i < ids.length; i++) {
            if (ids[i] != null)
                bit[i] = BitmapFactory.decodeResource(getResources(), ids[i]);
        }
        mMyTimer.start();
    }
}
