package com.abing.androidnote.androida笔记.Adapter创建回调示例;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


import com.example.activity.goodslist.GoodsListActivity;
import com.example.customshoppingcardemo.R;
import com.example.datas.goods.ContentData;
import com.example.datasave.GoodsDataBaseInterface;
import com.example.datasave.OperateGoodsDataBase;

import java.util.List;

/**
 * Created by Administrator on 2016/3/30 0030.
 */
public class ListContentAdapter extends RecyclerView.Adapter<ListContentAdapter.ViewHolder> {

    private Context context;
    private List<ContentData> stringListcontent;
    private OnContentItemClickListener onContentItemClickListener;
    GoodsDataBaseInterface mGoodsDataBaseInterface = null;
    public ListContentAdapter(Context context,List<ContentData> stringListcontent)
    {
        this.context=context;
        this.stringListcontent=stringListcontent;
        mGoodsDataBaseInterface= OperateGoodsDataBase.getInstance();
    }

    public interface OnContentItemClickListener{
        void onItemClick(ViewHolder holder);
        void onItemJiaClick(ViewHolder holder, int position);
        void onItemJianClick(ViewHolder holder, int position);
    }
    public void setOnContentItemClickListener(OnContentItemClickListener onContentItemClickListener)
    {
        this.onContentItemClickListener=onContentItemClickListener;
    }
    //创建Viewholder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_menu_content,null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }
    //绑定
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        holder.mImageView.setImageResource(DemoData.ListMenu_PIMAGES[position]);
        holder.mTitle.setText(stringListcontent.get(position).getGoodsname());
        holder.mYueSale.setText("月售" +"11"+position);
        holder.mPrice.setText("2"+position);

        holder.mRatingBar.setRating(Float.parseFloat(3+""));
        holder.mRatingBar.getRating();

        /** 获取存储的商品数量 */
        if (mGoodsDataBaseInterface.getSecondGoodsNumber(context, GoodsListActivity.SELECTPOSITION , Integer.parseInt(stringListcontent.get(holder.getPosition()).getGoodsid())) == 0) {
            holder.mNumber.setText("");
            holder.mNumber.setVisibility(View.GONE);
            holder.mImgJian.setVisibility(View.GONE);
        } else {
            holder.mNumber.setText("" + mGoodsDataBaseInterface.getSecondGoodsNumber(context, GoodsListActivity.SELECTPOSITION , Integer.parseInt(stringListcontent.get(holder.getPosition()).getGoodsid())));
            holder.mNumber.setVisibility(View.VISIBLE);
            holder.mImgJian.setVisibility(View.VISIBLE);
        }

        setOnContentItemClickListener(holder);
    }
    //触发点击事件
    private void setOnContentItemClickListener(final ViewHolder viewHolder)
    {
        final int index=viewHolder.getPosition();
        if (onContentItemClickListener!=null)
        {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onContentItemClickListener.onItemClick(viewHolder);
                }
            });
            viewHolder.mImgJia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onContentItemClickListener.onItemJiaClick(viewHolder,index);
                }
            });
            viewHolder.mImgJian.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onContentItemClickListener.onItemJianClick(viewHolder,index);
                }
            });
        }
    }
    @Override
    public int getItemCount() {
        return stringListcontent.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView , mImgJia , mImgJian;
        public TextView mTitle , mYueSale , mPrice , mNumber;
        public RatingBar mRatingBar;
        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.item_menu_content_img);
            mTitle = (TextView) itemView.findViewById(R.id.item_menu_content_title);
            mRatingBar = (RatingBar) itemView.findViewById(R.id.item_menu_content_star);
            mYueSale = (TextView) itemView.findViewById(R.id.item_menu_content_sale);
            mPrice = (TextView) itemView.findViewById(R.id.item_menu_content_price);
            mImgJia = (ImageView) itemView.findViewById(R.id.item_menu_content_jia);
            mImgJian = (ImageView) itemView.findViewById(R.id.item_menu_content_jian);
            mNumber = (TextView) itemView.findViewById(R.id.item_menu_content_number);
        }
    }
}
