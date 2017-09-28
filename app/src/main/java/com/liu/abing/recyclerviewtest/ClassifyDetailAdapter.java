package com.liu.abing.recyclerviewtest;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liu.abing.R;
import com.liu.extend.entity.SortBean;

import java.util.List;


public class ClassifyDetailAdapter extends RvAdapter<SortBean> {

    public ClassifyDetailAdapter(Context context, List<SortBean> list, RvListener listener) {
        super(context, list, listener);
    }


    @Override
    protected int getLayoutId(int viewType) {
        return viewType == 0 ? R.layout.item_title : R.layout.item_classify_detail;
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).isTitle() ? 0 : 1;
    }

    @Override
    protected RvHolder getHolder(View view, int viewType) {
        return new ClassifyHolder(view, viewType, listener);
    }

    public class ClassifyHolder extends RvHolder<SortBean> {
        TextView tvCity;
        ImageView avatar;
        TextView tvTitle;
        LinearLayout content;

        public ClassifyHolder(View itemView, int type, RvListener listener) {
            super(itemView, type, listener);
            switch (type) {
                case 0:
                    tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
                    break;
                case 1:
                    tvCity = (TextView) itemView.findViewById(R.id.tvCity);
                    avatar = (ImageView) itemView.findViewById(R.id.ivAvatar);
                    content = (LinearLayout) itemView.findViewById(R.id.content);
                    break;
            }

        }

        @Override
        public void bindHolder(SortBean sortBean, int position) {
            int itemViewType = ClassifyDetailAdapter.this.getItemViewType(position);
            switch (itemViewType) {
                case 0:
                    tvTitle.setText(sortBean.getName());
                    break;
                case 1:
                        tvCity.setText(sortBean.getName());
                    if (sortBean.getImage()!=0)
                    {
                        avatar.setImageResource(sortBean.getImage());
                    }

                    break;
            }

        }
    }
}
