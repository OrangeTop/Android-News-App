package com.example.sdau_news_main;

import com.example.sdau_news.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class StartActivity extends Activity{
	
	private static final int PAUSE=3000;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
        new Handler().postDelayed(new Runnable(){

			@Override
			public void run() {
				Intent mainIntent = new Intent(StartActivity.this,MainActivity.class);
				StartActivity.this.startActivity(mainIntent);
				StartActivity.this.finish();
			}
        	
        },PAUSE);
	}
	
	
}
