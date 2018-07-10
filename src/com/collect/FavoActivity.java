package com.collect;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.sdau_news.R;

public class FavoActivity extends Activity {
	private Button b1, b2, b3, b4, b5, b6, b7, b8, b9;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.favorite);
		b1 = (Button) findViewById(R.id.b11);
		b2 = (Button) findViewById(R.id.b12);
		b3 = (Button) findViewById(R.id.b13);
		b4 = (Button) findViewById(R.id.b14);
		b5 = (Button) findViewById(R.id.b15);
		b6 = (Button) findViewById(R.id.b16);
		b7 = (Button) findViewById(R.id.b17);
		b8 = (Button) findViewById(R.id.b18);
		b9 = (Button) findViewById(R.id.b19);
		b1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Toast.makeText(FavoActivity.this, "设置成功！！！", Toast.LENGTH_SHORT)
						.show();
			}
		});
		b2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Toast.makeText(FavoActivity.this, "设置成功！！！", Toast.LENGTH_SHORT)
						.show();
			}
		});
		b3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Toast.makeText(FavoActivity.this, "设置成功！！！", Toast.LENGTH_SHORT)
						.show();
			}
		});
		b4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Toast.makeText(FavoActivity.this, "设置成功！！！", Toast.LENGTH_SHORT)
						.show();
			}
		});
		b5.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Toast.makeText(FavoActivity.this, "设置成功！！！", Toast.LENGTH_SHORT)
						.show();
			}
		});
		b6.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Toast.makeText(FavoActivity.this, "设置成功！！！", Toast.LENGTH_SHORT)
						.show();
			}
		});
		b7.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Toast.makeText(FavoActivity.this, "设置成功！！！", Toast.LENGTH_SHORT)
						.show();
			}
		});
		b8.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Toast.makeText(FavoActivity.this, "设置成功！！！", Toast.LENGTH_SHORT)
						.show();
			}
		});
		b9.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Toast.makeText(FavoActivity.this, "设置成功！！！", Toast.LENGTH_SHORT)
						.show();
			}
		});

	}

}
