package com.liu.abing.animator;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;

import com.liu.abing.R;
import com.liu.abing.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 项目名称：abing
 * 类描述：
 * 创建人：liubing
 * 创建时间：2017-5-20 10:58
 * 修改人：Administrator
 * 修改时间：2017-5-20 10:58
 * 修改备注：
 */
public class AnimatorActivity extends BaseActivity {

    @BindView(R.id.button)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button)
    public void onClick() {
//        slideview(0,500);
//        scale();
    }

    //平移动画
    public void slideview(final float p1, final float p2) {
        TranslateAnimation animation = new TranslateAnimation(0, 0, p1, p2);
        animation.setInterpolator(new OvershootInterpolator());
        animation.setDuration(4000);
//        animation.setStartOffset(delayMillis);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
//                int left = button.getLeft() + (int) (p2 - p1);
//                int top = button.getTop();
//                int width = button.getWidth();
//                int height = button.getHeight();
                button.clearAnimation();
//                button.layout(left, top, left + width, top + height);
            }
        });
        button.startAnimation(animation);
    }

    //缩放动画
    private void scale() {
         /*
                AnimationSet相当于一个动画的集合，true表示使用Animation的interpolator
                false则是使用自己的。
                Interpolator 被用来修饰动画效果，定义动画的变化率，可以使存在的动画效果
                accelerated(加速)，decelerated(减速),repeated(重复),bounced(弹跳)等。
             */

        AnimationSet animationSet = new AnimationSet(true);
            /*
                参数解释：
                    第一个参数：X轴水平缩放起始位置的大小（fromX）。1代表正常大小
                    第二个参数：X轴水平缩放完了之后（toX）的大小，0代表完全消失了
                    第三个参数：Y轴垂直缩放起始时的大小（fromY）
                    第四个参数：Y轴垂直缩放结束后的大小（toY）
                    第五个参数：pivotXType为动画在X轴相对于物件位置类型
                    第六个参数：pivotXValue为动画相对于物件的X坐标的开始位置
                    第七个参数：pivotXType为动画在Y轴相对于物件位置类型
                    第八个参数：pivotYValue为动画相对于物件的Y坐标的开始位置

                   （第五个参数，第六个参数），（第七个参数,第八个参数）是用来指定缩放的中心点
                    0.5f代表从中心缩放
             */
        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        //3秒完成动画
        scaleAnimation.setDuration(2000);
        //将AlphaAnimation这个已经设置好的动画添加到 AnimationSet中
        animationSet.addAnimation(scaleAnimation);
        //启动动画
        button.startAnimation(animationSet);
    }

}
