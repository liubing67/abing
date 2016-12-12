package com.liu.extend.adapters;

import java.util.ArrayList;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.liu.abing.R;
import com.liu.extend.entity.NewsEntity;

public class NewsAdapter extends BaseAdapter {
	ArrayList<NewsEntity> newsList;
	Activity activity;
	LayoutInflater inflater = null;
	public NewsAdapter(Activity activity, ArrayList<NewsEntity> newsList) {
		this.activity = activity;
		this.newsList = newsList;
		inflater = LayoutInflater.from(activity);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return newsList == null ? 0 : newsList.size();
	}

	@Override
	public NewsEntity getItem(int position) {
		// TODO Auto-generated method stub
		if (newsList != null && newsList.size() != 0) {
			return newsList.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder mHolder;
		ViewHolder viewHolder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.listitem_new, null);
			mHolder = new ViewHolder();
			mHolder.item_layout = (RelativeLayout)convertView.findViewById(R.id.item_layout);
			mHolder.comment_layout = (RelativeLayout)convertView.findViewById(R.id.comment_layout);
			mHolder.item_title = (TextView)convertView.findViewById(R.id.item_title);
			mHolder.item_source = (TextView)convertView.findViewById(R.id.item_source);
			mHolder.list_item_local = (TextView)convertView.findViewById(R.id.list_item_local);
			mHolder.comment_count = (TextView)convertView.findViewById(R.id.comment_count);
			mHolder.publish_time = (TextView)convertView.findViewById(R.id.publish_time);
			mHolder.item_abstract = (TextView)convertView.findViewById(R.id.item_abstract);
			mHolder.alt_mark = (ImageView)convertView.findViewById(R.id.alt_mark);
			mHolder.right_image = (ImageView)convertView.findViewById(R.id.right_image);
			mHolder.item_image_layout = (LinearLayout)convertView.findViewById(R.id.item_image_layout);
			mHolder.large_image = (ImageView)convertView.findViewById(R.id.large_image);
			mHolder.popicon = (ImageView)convertView.findViewById(R.id.popicon);
			mHolder.comment_content = (TextView)convertView.findViewById(R.id.comment_content);
			mHolder.right_padding_view = (View)convertView.findViewById(R.id.right_padding_view);

			convertView.setTag(mHolder);
		} else {
			mHolder = (ViewHolder) convertView.getTag();
		}

		mHolder.item_title.setText(newsList.get(position).getTitle());
		return convertView;
	}

	static class ViewHolder {
		RelativeLayout item_layout;
		//title
		TextView item_title;
		//图片源
		TextView item_source;
		//类似推广之类的标签
		TextView list_item_local;
		//评论数量
		TextView comment_count;
		//发布时间
		TextView publish_time;
		//新闻摘要
		TextView item_abstract;
		//右上方TAG标记图片
		ImageView alt_mark;
		//右边图片
		ImageView right_image;
		//3张图片布局
		LinearLayout item_image_layout; //3张图片时候的布局
		ImageView large_image;
		//pop按钮
		ImageView popicon;
		//评论布局
		RelativeLayout comment_layout;
		TextView comment_content;
		//paddingview
		View right_padding_view;
	}

}
