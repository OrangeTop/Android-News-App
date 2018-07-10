package com.db;

import com.example.sdau_news.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class HuanCunActivity extends Activity {
	private Button mBtn2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        setContentView(R.layout.shezhi);
        
        mBtn2=(Button) findViewById(R.id.btn2);
        
        mBtn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(HuanCunActivity.this,"Çå¿Õ»º´æ£¡£¡", Toast.LENGTH_SHORT).show();

			}
		});
        
        
	}
	
}
