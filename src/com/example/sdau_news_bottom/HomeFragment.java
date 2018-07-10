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
	// ��ͼ
	private ListView mListView;
	private View mView;

	private Handler mHandler = new Handler() {
		// ���� massage����
		public void handleMessage(android.os.Message msg) {

			switch (msg.what) {
			case 1:
				// ȡ�����ݵ�UI����ʾ
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

				Toast.makeText(getActivity(), "���", Toast.LENGTH_SHORT).show();
				httpUrlConnectionGet("shehui");

			}
		});

		mbtn_cj = (Button) mView.findViewById(R.id.btn_cj);
		mbtn_cj.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Toast.makeText(getActivity(), "�ƾ�", Toast.LENGTH_SHORT).show();
				httpUrlConnectionGet("caijing");
			}
		});
		mbtn_zz = (Button) mView.findViewById(R.id.btn_zz);
		mbtn_zz.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Toast.makeText(getActivity(), "����", Toast.LENGTH_SHORT).show();
				httpUrlConnectionGet("junshi");
			}
		});
		mbtn_ty = (Button) mView.findViewById(R.id.btn_ty);
		mbtn_ty.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Toast.makeText(getActivity(), "����", Toast.LENGTH_SHORT).show();
				httpUrlConnectionGet("tiyu");
			}
		});
		mbtn_kj = (Button) mView.findViewById(R.id.btn_kj);
		mbtn_kj.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Toast.makeText(getActivity(), "�Ƽ�", Toast.LENGTH_SHORT).show();
				httpUrlConnectionGet("keji");
			}
		});
		mbtn_yl = (Button) mView.findViewById(R.id.btn_yl);
		mbtn_yl.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Toast.makeText(getActivity(), "����", Toast.LENGTH_SHORT).show();
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
		// listview��������ת
		mListView = (ListView) mView.findViewById(R.id.list_view);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// ��ת��ֵ�����¸�ҳ�����Ӧ����ҳ
				Intent intent = new Intent(getActivity(), WebViewActivity.class);
				intent.putExtra("url", news.get(position).getUrl());
				startActivity(intent);
			}

		});

		// �����ղ�
		mListView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int postion, long od) {
				/*
				 * // ���ݱ�������󷵻ص���ţ����Ծ�׼���ҵ���Ӧ������Դ��������ɾ�� �޸� �������
				 * mDataList.remove(position); // ʹ����������֪ͨUI���û����棩���ݵ�ǰ���ݸ���
				 * adapter.notifyDataSetChanged();
				 */

				// TODO ���ղص�������ӵ����ݿ�
				DAO d = new DAO(getActivity());
				d.insert(news.get(postion));
				Toast.makeText(getActivity(), "�ղسɹ���", Toast.LENGTH_SHORT)
						.show();
				// ��������͵���ĳ�ͻ
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
		// jsonarray����
		JSONArray array = result.getJSONArray("data");
		News news_item;
		// ��������
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
		// ֪ͨ���߳�ȥ���µ�ǰ����
		Message message = Message.obtain();
		message.what = 1;
		// ʹ��int���͵�what��������Ϊ��ͬ����Ϣʶ�𣨼����ˣ�
		// message����Я������()
		message.obj = mNews;
		// ʹ��handler����ʹ��������Ϣ
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
				// ���URL����
				URL url = new URL("http://v.juhe.cn/toutiao/index?type="
						+ category + "&key=5465c4c5d60f72c3d756a9f1a9b8437d");
				// ����Get����������
				HttpURLConnection connection = (HttpURLConnection) url
						.openConnection();
				// ����ΪGET����
				connection.setRequestMethod("GET");
				// ��������ʱ���Ͷ�ȡʱ��
				connection.setConnectTimeout(10000);
				connection.setReadTimeout(10000);

				// �ж�״̬��
				if (connection.getResponseCode() == 200) {
					// ��ȡ�ֽ�������
					InputStream is = connection.getInputStream();
					// ת��Ϊ�ַ���������
					InputStreamReader isr = new InputStreamReader(is);
					// �����ַ�������
					BufferedReader br = new BufferedReader(isr);

					// JSON����ƴ���õ��ַ�����
					StringBuffer sb = new StringBuffer();

					String buffer = new String();

					// ѭ����ȡ����
					while ((buffer = br.readLine()) != null) {
						// ׷��ƴ�ӵ��ַ���
						sb.append(buffer);
					}
					// ת�����ַ�������ӡ������Ƿ�����

					// �ر�������
					br.close();
					// ����json����
					parseJSON(sb.toString());
					Log.d(TAG, "testing ��ȡ " + category + " ������");
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
