package com.liu.abing.animator;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
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
        slideview(0,500);

    }
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


}
