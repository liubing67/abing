package com.tools.views.indexbar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.OvershootInterpolator;

import com.liu.abing.R;

/**
 * 项目名称：abing
 * 类描述：
 * 创建人：liubing
 * 创建时间：2016/12/23 11:21
 * 修改人：Administrator
 * 修改时间：2016/12/23 11:21
 * 修改备注：
 */
public class SwipeMenuLayout extends ViewGroup {
    private static final String TAG = "zxt";
    private int mScaleTouchSlop;
    private int mMaxVelocity;
    private int mPointerId;
    private int mHeight;
    private int mRightMenuWidths;
    private int mLimit;
    private View mContentView;
    private PointF mLastP;
    private boolean isUnMoved;
    private PointF mFirstP;
    private boolean isUserSwiped;
    private static SwipeMenuLayout mViewCache;
    private static boolean isTouching;
    private VelocityTracker mVelocityTracker;
    private Log LogUtils;
    private boolean isSwipeEnable;
    private boolean isIos;
    private boolean iosInterceptFlag;
    private boolean isLeftSwipe;
    private ValueAnimator mExpandAnim;
    private ValueAnimator mCloseAnim;
    private boolean isExpand;

    public SwipeMenuLayout(Context context) {
        this(context, (AttributeSet)null);
    }

    public SwipeMenuLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwipeMenuLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mLastP = new PointF();
        this.isUnMoved = true;
        this.mFirstP = new PointF();
        this.init(context, attrs, defStyleAttr);
    }

    public boolean isSwipeEnable() {
        return this.isSwipeEnable;
    }

    public void setSwipeEnable(boolean swipeEnable) {
        this.isSwipeEnable = swipeEnable;
    }

    public boolean isIos() {
        return this.isIos;
    }

    public SwipeMenuLayout setIos(boolean ios) {
        this.isIos = ios;
        return this;
    }

    public boolean isLeftSwipe() {
        return this.isLeftSwipe;
    }

    public SwipeMenuLayout setLeftSwipe(boolean leftSwipe) {
        this.isLeftSwipe = leftSwipe;
        return this;
    }

    public static SwipeMenuLayout getViewCache() {
        return mViewCache;
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        this.mScaleTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        this.mMaxVelocity = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
        this.isSwipeEnable = true;
        this.isIos = true;
        this.isLeftSwipe = true;
        TypedArray ta = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SwipeMenuLayout, defStyleAttr, 0);
        int count = ta.getIndexCount();

        for(int i = 0; i < count; ++i) {
            int attr = ta.getIndex(i);
            if(attr == R.styleable.SwipeMenuLayout_swipeEnable) {
                this.isSwipeEnable = ta.getBoolean(attr, true);
            } else if(attr == R.styleable.SwipeMenuLayout_ios) {
                this.isIos = ta.getBoolean(attr, true);
            } else if(attr == R.styleable.SwipeMenuLayout_leftSwipe) {
                this.isLeftSwipe = ta.getBoolean(attr, true);
            }
        }

        ta.recycle();
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.setClickable(true);
        this.mRightMenuWidths = 0;
        int contentWidth = 0;
        int childCount = this.getChildCount();
        boolean measureMatchParentChildren = MeasureSpec.getMode(heightMeasureSpec) != 1073741824;
        boolean isNeedMeasureChildHeight = false;

        for(int i = 0; i < childCount; ++i) {
            View childView = this.getChildAt(i);
            childView.setClickable(true);
            if(childView.getVisibility() != 8) {
                this.measureChild(childView, widthMeasureSpec, heightMeasureSpec);
                MarginLayoutParams lp = (MarginLayoutParams)childView.getLayoutParams();
                this.mHeight = Math.max(this.mHeight, childView.getMeasuredHeight());
                if(measureMatchParentChildren && lp.height == -1) {
                    isNeedMeasureChildHeight = true;
                }

                if(i > 0) {
                    this.mRightMenuWidths += childView.getMeasuredWidth();
                } else {
                    this.mContentView = childView;
                    contentWidth = childView.getMeasuredWidth();
                }
            }
        }

        this.setMeasuredDimension(this.getPaddingLeft() + this.getPaddingRight() + contentWidth, this.mHeight + this.getPaddingTop() + this.getPaddingBottom());
        this.mLimit = this.mRightMenuWidths * 4 / 10;
        if(isNeedMeasureChildHeight) {
            this.forceUniformHeight(childCount, widthMeasureSpec);
        }

    }

    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(this.getContext(), attrs);
    }

    private void forceUniformHeight(int count, int widthMeasureSpec) {
        int uniformMeasureSpec = MeasureSpec.makeMeasureSpec(this.getMeasuredHeight(), 1073741824);

        for(int i = 0; i < count; ++i) {
            View child = this.getChildAt(i);
            if(child.getVisibility() != 8) {
                MarginLayoutParams lp = (MarginLayoutParams)child.getLayoutParams();
                if(lp.height == -1) {
                    int oldWidth = lp.width;
                    lp.width = child.getMeasuredWidth();
                    this.measureChildWithMargins(child, widthMeasureSpec, 0, uniformMeasureSpec, 0);
                    lp.width = oldWidth;
                }
            }
        }

    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = this.getChildCount();
        int left = 0 + this.getPaddingLeft();
        int right = 0 + this.getPaddingLeft();

        for(int i = 0; i < childCount; ++i) {
            View childView = this.getChildAt(i);
            if(childView.getVisibility() != 8) {
                if(i == 0) {
                    childView.layout(left, this.getPaddingTop(), left + childView.getMeasuredWidth(), this.getPaddingTop() + childView.getMeasuredHeight());
                    left += childView.getMeasuredWidth();
                } else if(this.isLeftSwipe) {
                    childView.layout(left, this.getPaddingTop(), left + childView.getMeasuredWidth(), this.getPaddingTop() + childView.getMeasuredHeight());
                    left += childView.getMeasuredWidth();
                } else {
                    childView.layout(right - childView.getMeasuredWidth(), this.getPaddingTop(), right, this.getPaddingTop() + childView.getMeasuredHeight());
                    right -= childView.getMeasuredWidth();
                }
            }
        }

    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(this.isSwipeEnable) {
            this.acquireVelocityTracker(ev);
            VelocityTracker verTracker = this.mVelocityTracker;
            switch(ev.getAction()) {
                case 0:
                    this.isUserSwiped = false;
                    this.isUnMoved = true;
                    this.iosInterceptFlag = false;
                    if(isTouching) {
                        return false;
                    }

                    isTouching = true;
                    this.mLastP.set(ev.getRawX(), ev.getRawY());
                    this.mFirstP.set(ev.getRawX(), ev.getRawY());
                    if(mViewCache != null) {
                        if(mViewCache != this) {
                            mViewCache.smoothClose();
                            this.iosInterceptFlag = this.isIos;
                        }

                        this.getParent().requestDisallowInterceptTouchEvent(true);
                    }

                    this.mPointerId = ev.getPointerId(0);
                    break;
                case 1:
                case 3:
                    if(Math.abs(ev.getRawX() - this.mFirstP.x) > (float)this.mScaleTouchSlop) {
                        this.isUserSwiped = true;
                    }

                    if(!this.iosInterceptFlag) {
                        verTracker.computeCurrentVelocity(1000, (float)this.mMaxVelocity);
                        float velocityX = verTracker.getXVelocity(this.mPointerId);
                        if(Math.abs(velocityX) > 1000.0F) {
                            if(velocityX < -1000.0F) {
                                if(this.isLeftSwipe) {
                                    this.smoothExpand();
                                } else {
                                    this.smoothClose();
                                }
                            } else if(this.isLeftSwipe) {
                                this.smoothClose();
                            } else {
                                this.smoothExpand();
                            }
                        } else if(Math.abs(this.getScrollX()) > this.mLimit) {
                            this.smoothExpand();
                        } else {
                            this.smoothClose();
                        }
                    }

                    this.releaseVelocityTracker();
                    isTouching = false;
                    break;
                case 2:
                    if(!this.iosInterceptFlag) {
                        float gap = this.mLastP.x - ev.getRawX();
                        if(Math.abs(gap) > 10.0F || Math.abs(this.getScrollX()) > 10) {
                            this.getParent().requestDisallowInterceptTouchEvent(true);
                        }

                        if(Math.abs(gap) > (float)this.mScaleTouchSlop) {
                            this.isUnMoved = false;
                        }

                        this.scrollBy((int)gap, 0);
                        if(this.isLeftSwipe) {
                            if(this.getScrollX() < 0) {
                                this.scrollTo(0, 0);
                            }

                            if(this.getScrollX() > this.mRightMenuWidths) {
                                this.scrollTo(this.mRightMenuWidths, 0);
                            }
                        } else {
                            if(this.getScrollX() < -this.mRightMenuWidths) {
                                this.scrollTo(-this.mRightMenuWidths, 0);
                            }

                            if(this.getScrollX() > 0) {
                                this.scrollTo(0, 0);
                            }
                        }

                        this.mLastP.set(ev.getRawX(), ev.getRawY());
                    }
            }
        }

        return super.dispatchTouchEvent(ev);
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch(ev.getAction()) {
            case 1:
                if(this.isLeftSwipe) {
                    if(this.getScrollX() > this.mScaleTouchSlop && ev.getX() < (float)(this.getWidth() - this.getScrollX())) {
                        if(this.isUnMoved) {
                            this.smoothClose();
                        }

                        return true;
                    }
                } else if(-this.getScrollX() > this.mScaleTouchSlop && ev.getX() > (float)(-this.getScrollX())) {
                    if(this.isUnMoved) {
                        this.smoothClose();
                    }

                    return true;
                }

                if(this.isUserSwiped) {
                    return true;
                }
                break;
            case 2:
                if(Math.abs(ev.getRawX() - this.mFirstP.x) > (float)this.mScaleTouchSlop) {
                    return true;
                }
        }

        return this.iosInterceptFlag?true:super.onInterceptTouchEvent(ev);
    }

    public void smoothExpand() {
        mViewCache = this;
        if(null != this.mContentView) {
            this.mContentView.setLongClickable(false);
        }

        if(this.mCloseAnim != null && this.mCloseAnim.isRunning()) {
            this.mCloseAnim.cancel();
        }

        this.mExpandAnim = ValueAnimator.ofInt(new int[]{this.getScrollX(), this.isLeftSwipe?this.mRightMenuWidths:-this.mRightMenuWidths});
        this.mExpandAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                SwipeMenuLayout.this.scrollTo(((Integer)animation.getAnimatedValue()).intValue(), 0);
            }
        });
        this.mExpandAnim.setInterpolator(new OvershootInterpolator());
        this.mExpandAnim.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                SwipeMenuLayout.this.isExpand = true;
            }
        });
        this.mExpandAnim.setDuration(300L).start();
    }

    public void smoothClose() {
        mViewCache = null;
        if(null != this.mContentView) {
            this.mContentView.setLongClickable(true);
        }

        if(this.mExpandAnim != null && this.mExpandAnim.isRunning()) {
            this.mExpandAnim.cancel();
        }

        this.mCloseAnim = ValueAnimator.ofInt(new int[]{this.getScrollX(), 0});
        this.mCloseAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                SwipeMenuLayout.this.scrollTo(((Integer)animation.getAnimatedValue()).intValue(), 0);
            }
        });
        this.mCloseAnim.setInterpolator(new AccelerateInterpolator());
        this.mCloseAnim.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                SwipeMenuLayout.this.isExpand = false;
            }
        });
        this.mCloseAnim.setDuration(300L).start();
    }

    private void acquireVelocityTracker(MotionEvent event) {
        if(null == this.mVelocityTracker) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }

        this.mVelocityTracker.addMovement(event);
    }

    private void releaseVelocityTracker() {
        if(null != this.mVelocityTracker) {
            this.mVelocityTracker.clear();
            this.mVelocityTracker.recycle();
            this.mVelocityTracker = null;
        }

    }

    protected void onDetachedFromWindow() {
        if(this == mViewCache) {
            mViewCache.smoothClose();
            mViewCache = null;
        }

        super.onDetachedFromWindow();
    }

    public boolean performLongClick() {
        return Math.abs(this.getScrollX()) > this.mScaleTouchSlop?false:super.performLongClick();
    }

    public void quickClose() {
        if(this == mViewCache) {
            if(null != this.mExpandAnim && this.mExpandAnim.isRunning()) {
                this.mExpandAnim.cancel();
            }

            mViewCache.scrollTo(0, 0);
            mViewCache = null;
        }

    }
}