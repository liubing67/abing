package com.liu.abing.dialog;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.flyco.animation.BounceEnter.BounceBottomEnter;
import com.flyco.animation.BounceEnter.BounceRightEnter;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideEnter.SlideBottomEnter;
import com.flyco.animation.SlideEnter.SlideTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.animation.SlideExit.SlideLeftExit;
import com.flyco.animation.SlideExit.SlideTopExit;
import com.flyco.dialog.widget.popup.BubblePopup;
import com.flyco.dialog.widget.popup.base.BasePopup;
import com.liu.abing.R;
import com.liu.abing.dialog.extra.CustomBubblePopup;
import com.tools.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomPopupActivity extends AppCompatActivity {
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
    private SimpleCustomPop mQuickCustomPopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_custom_popup);
        ButterKnife.bind(this);

        mQuickCustomPopup = new SimpleCustomPop(mContext);
//        mQuickCustomPopup.setCanceledOnTouchOutside(false);
    }

    @OnClick({R.id.tv_center, R.id.tv_top_left, R.id.tv_top_right, R.id.tv_bottom_left, R.id.tv_bottom_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_center:
                mQuickCustomPopup
                        .alignCenter(true)
                        .anchorView(mTvCenter)
                        .gravity(Gravity.BOTTOM)
                        .showAnim(new SlideTopEnter())
                        .dismissAnim(new SlideTopExit())
                        .offset(0, 0)
                        .dimEnabled(false)
                        .show();
                break;
            case R.id.tv_top_left: {
                mQuickCustomPopup
                        .anchorView(mTvTopLeft)
                        .gravity(Gravity.BOTTOM)
                        .offset(0, 0)
                        .showAnim(new BounceTopEnter())
                        .dismissAnim(new SlideTopExit())
                        .dimEnabled(false)
                        .show();
            }
            break;
            case R.id.tv_top_right: {
                mQuickCustomPopup
                        .anchorView(mTvTopRight)
                        .offset(-10, 5)
                        .gravity(Gravity.BOTTOM)
                        .showAnim(new BounceTopEnter())
                        .dismissAnim(new SlideTopExit())
                        .dimEnabled(false)
                        .show();
            }

            break;
            case R.id.tv_bottom_left: {
                mQuickCustomPopup
                        .anchorView(mTvBottomLeft)
                        .offset(10, -5)
                        .gravity(Gravity.TOP)
                        .showAnim(new BounceBottomEnter())
                        .dismissAnim(new SlideBottomExit())
                        .dimEnabled(true)
                        .show();
            }

            break;
            case R.id.tv_bottom_right: {
                mQuickCustomPopup
                        .showAnim(null)
                        .dismissAnim(null)
                        .dimEnabled(true)
                        .anchorView(mTvBottomRight)
                        .offset(-10, -5)
                        .dimEnabled(false)
                        .gravity(Gravity.TOP)
                        .show();
            }
            break;
        }
    }


    class SimpleCustomPop extends BasePopup<SimpleCustomPop> {

        @BindView(R.id.tv_item_1) TextView mTvItem1;
        @BindView(R.id.tv_item_2) TextView mTvItem2;
        @BindView(R.id.tv_item_3) TextView mTvItem3;
        @BindView(R.id.tv_item_4) TextView mTvItem4;

        public SimpleCustomPop(Context context) {
            super(context);
//            setCanceledOnTouchOutside(false);
        }

        @Override
        public View onCreatePopupView() {
            View inflate = View.inflate(mContext, R.layout.popup_custom, null);
            ButterKnife.bind(this, inflate);
            return inflate;
        }

        @Override
        public void setUiBeforShow() {
            mTvItem1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.showShort(mContext, mTvItem1.getText());
                }
            });
            mTvItem2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.showShort(mContext, mTvItem2.getText());
                }
            });
            mTvItem3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.showShort(mContext, mTvItem3.getText());
                }
            });
            mTvItem4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.showShort(mContext, mTvItem4.getText());
                }
            });
        }
    }
}
