package com.liu.extend.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.liu.abing.R;
import com.liu.extend.entity.CityBean;
import com.tools.views.indexbar.SwipeMenuLayout;

import java.util.List;

/**
 * 和CityAdapter 一模一样，只是修改了 Item的布局
 * Created by zhangxutong .
 * Date: 16/08/28
 */

public class SwipeDelMenuAdapter extends RecyclerView.Adapter<SwipeDelMenuAdapter.ViewHolder> {
    protected Context mContext;
    protected List<CityBean> mDatas;
    protected LayoutInflater mInflater;

    public SwipeDelMenuAdapter(Context mContext, List<CityBean> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(mContext);
    }

    public List<CityBean> getDatas() {
        return mDatas;
    }

    public SwipeDelMenuAdapter setDatas(List<CityBean> datas) {
        mDatas = datas;
        return this;
    }

    @Override
    public SwipeDelMenuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.item_city_swipe, parent, false));
    }

    @Override
    public void onBindViewHolder(final SwipeDelMenuAdapter.ViewHolder holder, final int position) {
        final CityBean cityBean = mDatas.get(position);
        holder.tvCity.setText(cityBean.getCity());
        holder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "pos:" + position, Toast.LENGTH_SHORT).show();
            }
        });
        holder.btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SwipeMenuLayout) holder.itemView).quickClose();
                mDatas.remove(holder.getAdapterPosition());
                notifyDataSetChanged();
            }
        });
        holder.avatar.setImageResource(R.mipmap.ic_launcher);
    }

    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCity;
        ImageView avatar;
        View content;
        Button btnDel;
        public ViewHolder(View itemView) {
            super(itemView);
            tvCity = (TextView) itemView.findViewById(R.id.tvCity);
            avatar = (ImageView) itemView.findViewById(R.id.ivAvatar);
            btnDel = (Button) itemView.findViewById(R.id.btnDel);
            content = itemView.findViewById(R.id.content);
        }
    }
}
