package com.liu.abing.roll;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ImageSpan;
import android.util.TypedValue;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.liu.abing.R;
import com.liu.abing.base.BaseActivity;
import com.liu.abing.home.MainActivity;
import com.tools.Tools;
import com.tools.views.MarqueeText;
import com.tools.views.roll.ComplexViewMF;
import com.tools.views.roll.MarqueeFactory;
import com.tools.views.roll.MarqueeView;
import com.tools.views.roll.NoticeMF;

import java.util.Arrays;
import java.util.List;

/**
 * 项目名称：abing
 * 类描述：
 * 创建人：liubing
 * 创建时间：2016/12/19 16:30
 * 修改人：Administrator
 * 修改时间：2016/12/19 16:30
 * 修改备注：
 */
public class TextViewRollActivity extends BaseActivity {

    private MarqueeText text_notify;
    private TextView tv1,tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textroll);

        initView();
        init();
    }
    private void initView()
    {
        text_notify= (MarqueeText) findViewById(R.id.text_notify);
        tv1= (TextView) findViewById(R.id.tv1);
        tv2= (TextView) findViewById(R.id.tv2);

        List<String> datas = Arrays.asList("《赋得古原草送别》", "离离原上草，一岁一枯荣。", "野火烧不尽，春风吹又生。", "远芳侵古道，晴翠接荒城。", "又送王孙去，萋萋满别情。");
        MarqueeView marqueeView1 = (MarqueeView) findViewById(R.id.marqueeView1);
        MarqueeView marqueeView2 = (MarqueeView) findViewById(R.id.marqueeView2);
        MarqueeView marqueeView3 = (MarqueeView) findViewById(R.id.marqueeView3);
        MarqueeView marqueeView4 = (MarqueeView) findViewById(R.id.marqueeView4);
        MarqueeView marqueeView5 = (MarqueeView) findViewById(R.id.marqueeView5);

        MarqueeFactory<TextView, String> marqueeFactory1 = new NoticeMF(this);
        marqueeView1.setMarqueeFactory(marqueeFactory1);
        marqueeView1.startFlipping();
        marqueeFactory1.setOnItemClickListener(new MarqueeFactory.OnItemClickListener<TextView, String>() {
            @Override
            public void onItemClickListener(MarqueeFactory.ViewHolder<TextView, String> holder) {
                Toast.makeText(TextViewRollActivity.this, holder.data, Toast.LENGTH_SHORT).show();
            }
        });
        marqueeFactory1.setData(datas);

        MarqueeFactory<TextView, String> marqueeFactory2 = new NoticeMF(this);
        marqueeFactory2.setOnItemClickListener(new MarqueeFactory.OnItemClickListener<TextView, String>() {
            @Override
            public void onItemClickListener(MarqueeFactory.ViewHolder<TextView, String> holder) {
                Toast.makeText(TextViewRollActivity.this, holder.data, Toast.LENGTH_SHORT).show();
            }
        });
        marqueeFactory2.setData(datas);
        marqueeView2.setMarqueeFactory(marqueeFactory2);
        marqueeView2.startFlipping();

        MarqueeFactory<TextView, String> marqueeFactory3 = new NoticeMF(this);
        marqueeFactory3.setOnItemClickListener(new MarqueeFactory.OnItemClickListener<TextView, String>() {
            @Override
            public void onItemClickListener(MarqueeFactory.ViewHolder<TextView, String> holder) {
                Toast.makeText(TextViewRollActivity.this, holder.data, Toast.LENGTH_SHORT).show();
            }
        });
        marqueeFactory3.setData(datas);
        marqueeView3.setMarqueeFactory(marqueeFactory3);
        marqueeView3.setAnimInAndOut(R.anim.left_in, R.anim.right_out);
        marqueeView3.setAnimDuration(2000);
        marqueeView3.setInterval(2500);
        marqueeView3.startFlipping();

        MarqueeFactory<TextView, String> marqueeFactory4 = new NoticeMF(this);
        marqueeFactory4.setOnItemClickListener(new MarqueeFactory.OnItemClickListener<TextView, String>() {
            @Override
            public void onItemClickListener(MarqueeFactory.ViewHolder<TextView, String> holder) {
                Toast.makeText(TextViewRollActivity.this, holder.data, Toast.LENGTH_SHORT).show();
            }
        });
        marqueeFactory4.setData(datas);
        marqueeView4.setAnimInAndOut(R.anim.top_in, R.anim.bottom_out);
        marqueeView4.setMarqueeFactory(marqueeFactory4);
        marqueeView4.startFlipping();



        MarqueeFactory<RelativeLayout, String> marqueeFactory5 = new ComplexViewMF(this);
        marqueeFactory5.setData(datas);
        marqueeView5.setAnimInAndOut(R.anim.bottom_in, R.anim.top_out);
        marqueeView5.setMarqueeFactory(marqueeFactory5);
        marqueeView5.startFlipping();

        ///////////////
        Tools.autoIncrement((TextView) findViewById(R.id.text_money),1,900,1000);//文字自动增加


//////////////////////////
        ViewFlipper vf = (ViewFlipper) findViewById(R.id.vf);

        vf.addView(View.inflate(this, R.layout.view_advertisement01, null));
        vf.addView(View.inflate(this, R.layout.view_advertisement02, null));
        vf.addView(View.inflate(this, R.layout.view_advertisement03, null));


//        adjustTvTextSize(tv2,18,"Hello world 字体大小");
    }





    private void adjustTvTextSize(TextView tv,int maxwidth,String text)
    {
        int avaiwidth=maxwidth-tv.getPaddingLeft()-tv.getPaddingRight()-10;
        if (avaiwidth<=0)
        {
            return;
        }
        TextPaint textPaint=new TextPaint(tv.getPaint());
        float trySize=textPaint.getTextSize();
        while (textPaint.measureText(text)>avaiwidth)
        {
            trySize--;
            textPaint.setTextSize(trySize);
        }
        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX,trySize);
    }


    private void init() {

        TextView tv_test = (TextView) findViewById(R.id.tv_test);
        SpannableString spannableString = new SpannableString("点击按钮有惊喜");

        //调用自定义的imageSpan,实现文字与图片的横向居中对齐
        CustomImageSpan imageSpan = new CustomImageSpan(this, R.mipmap.ic_launcher, 2);

        //setSpan插入内容的时候，起始位置不替换，会替换起始位置到终止位置间的内容，含终止位置。
        //Spanned.SPAN_EXCLUSIVE_EXCLUSIVE模式用来控制是否同步设置新插入的内容与start/end 位置的字体样式，此处没设置具体字体，所以可以随意设置
        spannableString.setSpan(imageSpan, 2, 3, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tv_test.setText(spannableString);
    }

    /**
     * 自定义imageSpan实现图片与文字的居中对齐
     */
    class CustomImageSpan extends ImageSpan {

        //自定义对齐方式--与文字中间线对齐
        private int ALIGN_FONTCENTER = 2;

        public CustomImageSpan(Context context, int resourceId) {
            super(context, resourceId);
        }

        public CustomImageSpan(Context context, int resourceId, int verticalAlignment) {
            super(context, resourceId, verticalAlignment);
        }

        @Override
        public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom,
                         Paint paint) {

            //draw 方法是重写的ImageSpan父类 DynamicDrawableSpan中的方法，在DynamicDrawableSpan类中，虽有getCachedDrawable()，
            // 但是私有的，不能被调用，所以调用ImageSpan中的getrawable()方法，该方法中 会根据传入的drawable ID ，获取该id对应的
            // drawable的流对象，并最终获取drawable对象
            Drawable drawable = getDrawable(); //调用imageSpan中的方法获取drawable对象
            canvas.save();

            //获取画笔的文字绘制时的具体测量数据
            Paint.FontMetricsInt fm = paint.getFontMetricsInt();

            //系统原有方法，默认是Bottom模式)
            int transY = bottom - drawable.getBounds().bottom;
            if (mVerticalAlignment == ALIGN_BASELINE) {
                transY -= fm.descent;
            } else if (mVerticalAlignment == ALIGN_FONTCENTER) {    //此处加入判断， 如果是自定义的居中对齐
                //与文字的中间线对齐（这种方式不论是否设置行间距都能保障文字的中间线和图片的中间线是对齐的）
                // y+ascent得到文字内容的顶部坐标，y+descent得到文字的底部坐标，（顶部坐标+底部坐标）/2=文字内容中间线坐标
                transY = ((y + fm.descent) + (y + fm.ascent)) / 2 - drawable.getBounds().bottom / 2;
            }

            canvas.translate(x, transY);
            drawable.draw(canvas);
            canvas.restore();
        }

        /**
         * 重写getSize方法，只有重写该方法后，才能保证不论是图片大于文字还是文字大于图片，都能实现中间对齐
         */
        public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
            Drawable d = getDrawable();
            Rect rect = d.getBounds();
            if (fm != null) {
                Paint.FontMetricsInt fmPaint = paint.getFontMetricsInt();
                int fontHeight = fmPaint.bottom - fmPaint.top;
                int drHeight = rect.bottom - rect.top;

                int top = drHeight / 2 - fontHeight / 4;
                int bottom = drHeight / 2 + fontHeight / 4;

                fm.ascent = -bottom;
                fm.top = -bottom;
                fm.bottom = top;
                fm.descent = top;
            }
            return rect.right;
        }
    }
}
