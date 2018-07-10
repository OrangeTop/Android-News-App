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

	// �вι����������ݲ���
	// �����ġ�����Դ
	public MyAdapter(Context context, ArrayList<News> news) {
		mContext = context;
		mNews = news;
		// this.layoutInflater = layoutInflater.from(context);
		imgturn = new ImageTurn();
	}

	public int getCount() {
		// �������ݸ���
		return mNews.size();
	}

	@Override
	public Object getItem(int position) {
		// ����Item����
		return mNews.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	// ����ÿ��item����������
	// 1����ǰ���Ƶ�item���
	// 2:
	// ����item���ڵ������ؼ�
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		ViewHolder holder;
		ImageView image;
		TextView title;
		TextView date;
		TextView author_name;
		// Ϊ�ձ�ʾ��������
		if (view == null) {
			LayoutInflater inflater = LayoutInflater.from(mContext);
			view = inflater.inflate(R.layout.item_layout, null);
			// ���ص���Item�Ĳ��ֶ���

			// �����ҳ�item�ڲ��ؼ�
			image = (ImageView) view.findViewById(R.id.item_thumbnail_pic_s);
			title = (TextView) view.findViewById(R.id.item_title);
			date = (TextView) view.findViewById(R.id.item_date);
			author_name = (TextView) view.findViewById(R.id.item_author_name);
			holder = new ViewHolder(image, title, date, author_name);
			view.setTag(holder);
		} else {
			// ����
			// ȡ��holder����
			holder = (ViewHolder) view.getTag();
			image = holder.mImage;
			title = holder.mTitle;
			date = holder.mDate;
			author_name = holder.mAuthor_name;
		}
		// ���ؼ�������Ӧ����
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
