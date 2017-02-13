package com.liu.abing.dialog;

import android.Manifest;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.flyco.animation.BounceEnter.BounceRightEnter;
import com.flyco.animation.SlideEnter.SlideBottomEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.animation.SlideExit.SlideLeftExit;
import com.flyco.dialog.widget.popup.BubblePopup;
import com.liu.abing.R;
import com.liu.abing.dialog.extra.CustomBubblePopup;
import com.tools.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BubblePopupActivity extends AppCompatActivity {
    @BindView(R.id.tv_top_left)
    TextView mTvTopLeft;
    @BindView(R.id.tv_top_right)
    TextView mTvTopRight;
    @BindView(R.id.tv_bottom_left)
    TextView mTvBottomLeft;
    @BindView(R.id.tv_bottom_right)
    TextView mTvBottomRight;
    @BindView(R.id.tv_center)
    TextView mTvCenter;
    private Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_bubble_popup);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_center, R.id.tv_top_left, R.id.tv_top_right, R.id.tv_bottom_left, R.id.tv_bottom_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_center:
                View inflat = View.inflate(mContext, R.layout.popup_bubble_image, null);
                BubblePopup bubblePopu = new BubblePopup(mContext, inflat);
                bubblePopu.anchorView(mTvCenter)
                        .showAnim(new BounceRightEnter())
                        .dismissAnim(new SlideLeftExit())
                        .autoDismiss(true)
                        .show();
                break;
            case R.id.tv_top_left: {
                View inflate = View.inflate(mContext, R.layout.popup_bubble_text, null);
                TextView tv = ButterKnife.findById(inflate, R.id.tv_bubble);
                BubblePopup bubblePopup = new BubblePopup(mContext, inflate);
                tv.setText("最美的不是下雨天,是曾与你躲过雨的屋檐~");
                bubblePopup.anchorView(mTvTopLeft)
                        .gravity(Gravity.BOTTOM)
                        .show();

                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtil.showShort(mContext, "tv_bubble");
                    }
                });
            }
            break;
            case R.id.tv_top_right: {
                CustomBubblePopup customBubblePopup = new CustomBubblePopup(mContext);
//        customBubblePopup.setCanceledOnTouchOutside(false);
                customBubblePopup
                        .gravity(Gravity.BOTTOM)
                        .anchorView(mTvTopRight)
                        .triangleWidth(20)
                        .triangleHeight(10)
                        .showAnim(null)
                        .dismissAnim(null)
                        .show();
            }

            break;
            case R.id.tv_bottom_left: {
                View inflate = View.inflate(mContext, R.layout.popup_bubble_text, null);
                new BubblePopup(mContext, inflate)
                        .anchorView(mTvBottomLeft)
                        .showAnim(null)
                        .dismissAnim(null)
                        .show();
            }

            break;
            case R.id.tv_bottom_right: {
                View inflate = View.inflate(mContext, R.layout.popup_bubble_image, null);
                new BubblePopup(mContext, inflate).anchorView(mTvBottomRight)
                        .bubbleColor(Color.parseColor("#8BC34A"))
                        .showAnim(new SlideBottomEnter())
                        .dismissAnim(new SlideBottomExit())
                        .show();
            }
            break;
        }
    }


}
