package com.tools.views.GListView;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.DecelerateInterpolator;
import android.widget.ListView;
import android.widget.Scroller;

public class GListView extends ListView {

    protected float mLastY = -1; // save event y
    protected Scroller mScroller; // used for scroll back

    // -- header view
    protected GListViewHeader mHeaderView;
    protected ViewPager mHeaderViewContent;
    protected int mHeaderViewHeight; // header view's height

    protected final static int SCROLL_DURATION = 400; // scroll back duration
    protected final static float OFFSET_RADIO = 1.8f; // support iOS like pull

    /**
     * @param context
     */
    public GListView(Context context) {
        super(context);
        initWithContext(context);
    }

    public GListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initWithContext(context);
    }

    public GListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initWithContext(context);
    }

    protected void initWithContext(Context context) {
        mScroller = new Scroller(context, new DecelerateInterpolator());

        // init header view
        mHeaderView = new GListViewHeader(context);
        if (isInEditMode()) {
            return;
        }
        mHeaderViewContent = mHeaderView.getContent();
        addHeaderView(mHeaderView);

        // init header height
        mHeaderView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mHeaderViewHeight = mHeaderViewContent.getHeight();
                getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
    }

    protected void updateHeaderHeight(float delta) {
        mHeaderView.setVisiableHeight((int) delta + mHeaderView.getVisiableHeight());
    }

    protected void resetHeaderHeight() {
        int height = mHeaderView.getVisiableHeight();
        if (height <= mHeaderViewHeight) {
            return;
        }
        mScroller.startScroll(0, height, 0, mHeaderViewHeight - height, SCROLL_DURATION);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mLastY == -1) {
            mLastY = ev.getRawY();
        }
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastY = ev.getRawY();
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                float deltaY = ev.getRawY() - mLastY;
                mLastY = ev.getRawY();
                updateHeaderHeight(deltaY / OFFSET_RADIO);
                break;
            default:
                getParent().requestDisallowInterceptTouchEvent(false);
                mLastY = -1; // reset
                resetHeaderHeight();
                break;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            mHeaderView.setVisiableHeight(mScroller.getCurrY());
        }
        super.computeScroll();
    }
}
