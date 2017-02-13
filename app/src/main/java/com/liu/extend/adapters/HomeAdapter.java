package com.liu.extend.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.liu.abing.R;
import com.liu.abing.dialog.LiuDialogActivity;

import butterknife.ButterKnife;


public class HomeAdapter extends BaseExpandableListAdapter {
    private Context mContext;

    public HomeAdapter(Context context) {
        this.mContext = context;
    }

    // --->group
    @Override
    public int getGroupCount() {
        return LiuDialogActivity.mGroups.length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return LiuDialogActivity.mGroups[groupPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.ad_dialog_home, null);
        }

        TextView tv = ButterKnife.findById(convertView, R.id.tv_bubble);
        tv.setText(LiuDialogActivity.mGroups[groupPosition]);
        return convertView;
    }

    // --->child
    @Override
    public int getChildrenCount(int groupPosition) {
        return LiuDialogActivity.mChilds[groupPosition].length;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return LiuDialogActivity.mChilds[groupPosition][childPosition];
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
                             ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.ad_dialog_home, null);
        }

        TextView tv = ButterKnife.findById(convertView, R.id.tv_bubble);
        View v_line = ButterKnife.findById(convertView, R.id.v_line);

        v_line.setVisibility(View.INVISIBLE);
        tv.setTextColor(Color.parseColor("#383838"));
        tv.setText(LiuDialogActivity.mChilds[groupPosition][childPosition]);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
