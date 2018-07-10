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
import com.example.sdau_news_main.MainActivity;
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
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class HomeFragment extends Fragment {

	private static final String TAG = "HomeFragment";
	private Button mbtn_rd, mbtn_cj, mbtn_zz, mbtn_yl, mbtn_ty, mbtn_kj;
	private ArrayList<News> news = new ArrayList<News>();

	private MyAdapter mAdapter;
	// 视图
	private ListView mListView;
	private View mView;

	private Handler mHandler = new Handler() {
		// 参数 massage对象
		public void handleMessage(android.os.Message msg) {

			switch (msg.what) {
			case 1:
				// 取出数据到UI上显示
				news = (ArrayList<News>) msg.obj;
				mAdapter.updateItemsData(news);
				break;
			}
		}
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		mView = inflater.inflate(R.layout.fragment_home, null);
		httpUrlConnectionGet("shehui");
		mbtn_rd = (Button) mView.findViewById(R.id.btn_rd);
		mbtn_rd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Toast.makeText(getActivity(), "社会", Toast.LENGTH_SHORT).show();
				httpUrlConnectionGet("shehui");

			}
		});

		mbtn_cj = (Button) mView.findViewById(R.id.btn_cj);
		mbtn_cj.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Toast.makeText(getActivity(), "财经", Toast.LENGTH_SHORT).show();
				httpUrlConnectionGet("caijing");
			}
		});
		mbtn_zz = (Button) mView.findViewById(R.id.btn_zz);
		mbtn_zz.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Toast.makeText(getActivity(), "军事", Toast.LENGTH_SHORT).show();
				httpUrlConnectionGet("junshi");
			}
		});
		mbtn_ty = (Button) mView.findViewById(R.id.btn_ty);
		mbtn_ty.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Toast.makeText(getActivity(), "体育", Toast.LENGTH_SHORT).show();
				httpUrlConnectionGet("tiyu");
			}
		});
		mbtn_kj = (Button) mView.findViewById(R.id.btn_kj);
		mbtn_kj.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Toast.makeText(getActivity(), "科技", Toast.LENGTH_SHORT).show();
				httpUrlConnectionGet("keji");
			}
		});
		mbtn_yl = (Button) mView.findViewById(R.id.btn_yl);
		mbtn_yl.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Toast.makeText(getActivity(), "娱乐", Toast.LENGTH_SHORT).show();
				httpUrlConnectionGet("yule");
			}
		});

		initView();
		initAdapter();

		return mView;
	}

	private void initAdapter() {
		// mAdapter = new MyAdapter(getActivity(), news);
		news = new ArrayList<News>();
		mAdapter = new MyAdapter(getActivity(), news);
		mListView.setAdapter(mAdapter);
	}

	private void initView() {
		// listview、单击跳转
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

		// 长按收藏
		mListView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int postion, long od) {
				/*
				 * // 根据被点击对象返回的序号，可以精准的找到对应的数据源，来进行删除 修改 插入操作
				 * mDataList.remove(position); // 使用适配器来通知UI（用户界面）根据当前数据更新
				 * adapter.notifyDataSetChanged();
				 */

				// TODO 将收藏的内容添加到数据库
				DAO d = new DAO(getActivity());
				d.insert(news.get(postion));
				Toast.makeText(getActivity(), "收藏成功！", Toast.LENGTH_SHORT)
						.show();
				// 解决长按和点击的冲突
				return true;
			}

		});

		mListView.setAdapter(mAdapter);

	}

	private void httpUrlConnectionGet(String category) {

		new Thread(new MyThread(category)).start();

		// new Thread(new Runnable() {

		// @Override
		// public void run() {
		//
		//
		//
		// }).start();

	}

	private void parseJSON(String data) throws JSONException {

		JSONObject object = new JSONObject(data);
		JSONObject result = object.getJSONObject("result");
		// jsonarray数组
		JSONArray array = result.getJSONArray("data");
		News news_item;
		// 遍历数组
		ArrayList<News> mNews = new ArrayList<News>();
		for (int i = 0; i < array.length(); i++) {

			JSONObject item = array.getJSONObject(i);
			String uniquekey = item.getString("uniquekey");
			String title = item.getString("title");
			String date = item.getString("date");
			String category = item.getString("category");
			String author_name = item.getString("author_name");
			String url = item.getString("url");
			String thumbnail_pic_s = item.getString("thumbnail_pic_s");
			news_item = new News(uniquekey, title, date, category, author_name,
					url, thumbnail_pic_s);
			mNews.add(news_item);
			Log.d(TAG, title);

		}
		// 通知主线程去更新当前数据
		Message message = Message.obtain();
		message.what = 1;
		// 使用int类型的what属性来作为不同的信息识别（寄信人）
		// message可以携带内容()
		message.obj = mNews;
		// 使用handler（信使）发送信息
		mHandler.sendMessage(message);

	}

	class MyThread implements Runnable {

		private String category;

		public MyThread(String category) {
			super();
			this.category = category;
		}

		public MyThread() {
			super();
			// TODO Auto-generated constructor stub
		}

		@Override
		public void run() {
			try {
				// TODO Auto-generated method stub
				// 获得URL对象
				URL url = new URL("http://v.juhe.cn/toutiao/index?type="
						+ category + "&key=5465c4c5d60f72c3d756a9f1a9b8437d");
				// 网络Get请求的主类对
				HttpURLConnection connection = (HttpURLConnection) url
						.openConnection();
				// 设置为GET请求
				connection.setRequestMethod("GET");
				// 设置连接时长和读取时长
				connection.setConnectTimeout(10000);
				connection.setReadTimeout(10000);

				// 判断状态码
				if (connection.getResponseCode() == 200) {
					// 获取字节输入流
					InputStream is = connection.getInputStream();
					// 转换为字符流输入流
					InputStreamReader isr = new InputStreamReader(is);
					// 缓冲字符输入流
					BufferedReader br = new BufferedReader(isr);

					// JSON数据拼接用的字符串类
					StringBuffer sb = new StringBuffer();

					String buffer = new String();

					// 循环读取数据
					while ((buffer = br.readLine()) != null) {
						// 追加拼接的字符串
						sb.append(buffer);
					}
					// 转换成字符串，打印检测结果是否正常

					// 关闭输入流
					br.close();
					// 解析json数据
					parseJSON(sb.toString());
					Log.d(TAG, "testing 获取 " + category + " 的数据");
				} else {
					Log.d(TAG, "Error code:" + connection.getResponseCode());
				}

			} catch (MalformedURLException e) {
				e.printStackTrace();
				Log.d(TAG, "" + e);
			} catch (IOException e) {
				e.printStackTrace();
				Log.d(TAG, "" + e);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.d(TAG, "" + e);
			}

		}
	}

}
