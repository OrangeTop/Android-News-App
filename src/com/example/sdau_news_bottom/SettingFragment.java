package com.example.sdau_news_bottom;


import com.collect.FavoActivity;
import com.collect.GuanYuActivity;
import com.db.DAO;
import com.example.sdau_news.R;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class SettingFragment extends Fragment implements OnClickListener {

	private Button mBtn1, mBtn2, mBtn4;
	private View mView;

	// 两点不同：一点是放回 view对象 2是content对象 是getActivity
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.shezhi, null);
		mBtn1 = (Button) mView.findViewById(R.id.btn1);
		mBtn2 = (Button) mView.findViewById(R.id.btn2);
		mBtn4 = (Button) mView.findViewById(R.id.btn4);

		mBtn1.setOnClickListener(this);
		mBtn2.setOnClickListener(this);
		mBtn4.setOnClickListener(this);

		return mView;

	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = null;
		switch (v.getId()) {
		case R.id.btn1:
			intent = new Intent(getActivity(), FavoActivity.class);
			startActivity(intent);
			break;
		case R.id.btn2: {
			DAO d = new DAO(getActivity());
			d.delete();
			Toast.makeText(getActivity(), "缓存清除成功！！", Toast.LENGTH_SHORT)
					.show();
			break;
		}
		case R.id.btn4:
			intent = new Intent(getActivity(), GuanYuActivity.class);
			startActivity(intent);
			break;

		}
		

	}
}
