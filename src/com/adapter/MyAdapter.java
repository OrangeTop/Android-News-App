package com.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.adapter.MyAdapter.ViewHolder;
import com.example.sdau_news.R;
import com.example.sdau_news_main.ImageTurn;
import com.modle.News;

public class MyAdapter extends BaseAdapter {

	private Context mContext;
	// private LayoutInflater layoutInflater;
	private ArrayList<News> mNews;
	ImageTurn imgturn;

	// 有参构造器，传递参数
	// 上下文、数据源
	public MyAdapter(Context context, ArrayList<News> news) {
		mContext = context;
		mNews = news;
		// this.layoutInflater = layoutInflater.from(context);
		imgturn = new ImageTurn();
	}

	public int getCount() {
		// 返回数据个数
		return mNews.size();
	}

	@Override
	public Object getItem(int position) {
		// 返回Item数据
		return mNews.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	// 负责每个item的数据设置
	// 1：当前绘制的item序号
	// 2:
	// 三：item所在的容器控件
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		ViewHolder holder;
		ImageView image;
		TextView title;
		TextView date;
		TextView author_name;
		// 为空表示不可重用
		if (view == null) {
			LayoutInflater inflater = LayoutInflater.from(mContext);
			view = inflater.inflate(R.layout.item_layout, null);
			// 返回单个Item的布局对象

			// 单独找出item内部控件
			image = (ImageView) view.findViewById(R.id.item_thumbnail_pic_s);
			title = (TextView) view.findViewById(R.id.item_title);
			date = (TextView) view.findViewById(R.id.item_date);
			author_name = (TextView) view.findViewById(R.id.item_author_name);
			holder = new ViewHolder(image, title, date, author_name);
			view.setTag(holder);
		} else {
			// 重用
			// 取出holder对象
			holder = (ViewHolder) view.getTag();
			image = holder.mImage;
			title = holder.mTitle;
			date = holder.mDate;
			author_name = holder.mAuthor_name;
		}
		// 给控件设置相应参数
		String url = mNews.get(position).getThumbnail_pic_s();
		image.setTag(url);
		imgturn.showImagebyThread(image, url);
		title.setText(mNews.get(position).getTitle());
		date.setText(mNews.get(position).getDate());
		author_name.setText(mNews.get(position).getAuthor_name());
		return view;
	}

	public void updateItemsData(ArrayList<News> list) {
		this.mNews = list;
		notifyDataSetChanged();
	}

	class ViewHolder {
		private ImageView mImage;
		private TextView mTitle;
		private TextView mDate;
		private TextView mAuthor_name;

		private ViewHolder(ImageView image, TextView title, TextView date,
				TextView author_name) {
			super();
			mImage = image;
			mTitle = title;
			mDate = date;
			mAuthor_name = author_name;
		}

	}

}
