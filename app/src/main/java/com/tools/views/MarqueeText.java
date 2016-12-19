package com.tools.views;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 项目名称：BingYao
 * 类描述：
 * 创建人：liubing
 * 创建时间：2016/11/16 16:18
 * 修改人：Administrator
 * 修改时间：2016/11/16 16:18
 * 修改备注：
 */
public class MarqueeText extends TextView {
    public MarqueeText(Context con) {
        super(con);
    }
    public MarqueeText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public MarqueeText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    public boolean isFocused() {
        return true;
    }
    @Override
    protected void onFocusChanged(boolean focused, int direction,
                                  Rect previouslyFocusedRect) {
    }
}
