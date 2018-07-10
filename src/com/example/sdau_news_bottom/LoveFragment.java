package com.example.sdau_news_bottom;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.adapter.MyAdapter;
import com.db.DAO;
import com.example.sdau_news.R;
import com.example.sdau_news_bottom.HomeFragment.MyThread;
import com.example.sdau_news_main.WebViewActivity;
import com.modle.News;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class LoveFragment extends Fragment {
	
	private Button mbtn_rd, mbtn_cj, mbtn_zz, mbtn_yl, mbtn_ty, mbtn_kj;
	
	//数据源
	private ArrayList<News> news = new ArrayList<News>();
	//适配器
	MyAdapter mAdapter;
	// 视图
	private ListView mListView;
	private View mView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		mView = inflater.inflate(R.layout.fragment_home, null);
		initDate();
		initAdapter();
		initView();

		return mView;
	}

	private void initDate() {
		//newslist

		DAO d=new DAO(getActivity());
		news=d.query();
	}
	
	private void initAdapter() {
		mAdapter = new MyAdapter(getActivity(), news);

	}

	private void initView() {
		// 底部

		// listview
		mListView = (ListView) mView.findViewById(R.id.list_view);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// 跳转传值，在下个页面打开相应的网页
				Intent intent = new Intent(getActivity(), WebViewActivity.class);
				intent.putExtra("url", news.get(position).getUrl());
				startActivity(intent);
			}
		});
		mListView.setAdapter(mAdapter);
	}

}