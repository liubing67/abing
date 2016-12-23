package com.tools.views.roll;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liu.abing.R;

public class ComplexViewMF extends MarqueeFactory<RelativeLayout, String> {
    private LayoutInflater inflater;

    public ComplexViewMF(Context mContext) {
        super(mContext);
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public RelativeLayout generateMarqueeItemView(String data) {
        RelativeLayout mView = (RelativeLayout) inflater.inflate(R.layout.complex_view, null);
        TextView content= (TextView) mView.findViewById(R.id.secondTitle);
        content.setText(data);
        return mView;
    }
}