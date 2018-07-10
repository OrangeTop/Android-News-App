package com.example.sdau_news_main;

import com.example.sdau_news.R;
import com.example.sdau_news_bottom.HomeFragment;
import com.example.sdau_news_bottom.LoveFragment;
import com.example.sdau_news_bottom.SettingFragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class MainActivity extends Activity implements Button.OnClickListener {
	// 传选中的新闻类型
	private static final String TAG = "HttpClient";

	// 底部导航
	private FragmentManager manager;
	private FragmentTransaction transaction;
	private RadioButton rb_home, rb_love, rb_setting;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initView();
		manager = getFragmentManager();
		transaction = manager.beginTransaction();
		transaction.add(R.id.cotent_layout, new HomeFragment());
		transaction.commit();

	}

	private void initView() {
		// 底部
		rb_home = (RadioButton) findViewById(R.id.rb_home);
		rb_love = (RadioButton) findViewById(R.id.rb_love);
		rb_setting = (RadioButton) findViewById(R.id.rb_setting);

		rb_home.setOnClickListener(this);
		rb_love.setOnClickListener(this);
		rb_setting.setOnClickListener(this);

	}

	/**
	 * 底部 点击RadioButton时触发的事件
	 */
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		// 执行事务
		transaction = manager.beginTransaction();
		switch (view.getId()) {

		case R.id.rb_home:
			transaction.replace(R.id.cotent_layout, new HomeFragment());

			// Log.d(TAG1, "i am home");
			break;

		case R.id.rb_love:
			transaction.replace(R.id.cotent_layout, new LoveFragment());
			// Log.d(TAG2, "i am love");
			break;

		case R.id.rb_setting:
			transaction.replace(R.id.cotent_layout, new SettingFragment());
			// Log.d(TAG3, "i am setting");
			break;

		}
		transaction.commit();
	}

}
