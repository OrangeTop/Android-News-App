package com.example.sdau_news_main;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

public class ImageTurn {
	 /**
     * �������ȡͼƬ��������,���ҽ�������ת����Bitmap
     */
	public static final String TAG = "news";
	private ImageView mImageView;
	private String  ImgUrl;
    
	private Handler mHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(mImageView.getTag().equals(ImgUrl)){
                mImageView.setImageBitmap((Bitmap) msg.obj);
            }
        }
    };
	
	
	public Bitmap getBitmapFromUrl(String urlString){
        Bitmap bitmap;
        InputStream is = null;
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            is = new BufferedInputStream(connection.getInputStream());
            bitmap = BitmapFactory.decodeStream(is);
            connection.disconnect();
            return bitmap;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            if(is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
	
	
    public void showImagebyThread(ImageView imageView, final String url){
        mImageView = imageView;
        ImgUrl = url;

        new Thread(){
            @Override
            public void run() {
                super.run();
                Bitmap bitmap = getBitmapFromUrl(url);
                Message message = Message.obtain();
                message.obj = bitmap;
                mHandler.sendMessage(message);
            }
        }.start();
    }
	
	/*
	private Handler mHandler = new Handler() {
		// ���� massage����
		public void handleMessage(android.os.Message msg) {
			if(mImageView.getTag().equals(equals(ImgUrl))){
				mImageView.setImageBitmap((Bitmap) msg.obj);
			}
			

		}
	};
	public void getInputStreamFromNet(ImageView mImageView, String imgUrl) {
		this.mImageView = mImageView;
		ImgUrl = imgUrl;
        new Thread() {

            public void run() {

                // ������������
                BasicHttpParams params = new BasicHttpParams();
                // ʹ�þ�̬����
                // ��������ʱ��
                // ��һ��������BasicHttpParams����
                // �ڶ�������������ʱ��
                HttpConnectionParams.setConnectionTimeout(params, 10000);
                HttpConnectionParams.setSoTimeout(params, 10000);

                // ���HttpClient����
                HttpClient client = new DefaultHttpClient(params);

                HttpGet get = new HttpGet(ImgUrl);
                try {
                    // ִ��get����,��÷��ص����ݷ�װ
                    HttpResponse response = client.execute(get);

                    // �ж�״̬��
                    if (response.getStatusLine().getStatusCode() == 200) {
                        // ��ȡ�ֽ�������
                        InputStream is = response.getEntity().getContent();
                        // TODO תBitmap ���� �ļ��洢
                        final Bitmap bitmap = BitmapFactory.decodeStream(is);
                        
                        Message message = Message.obtain();
                        message.obj = bitmap;
                        mHandler.sendMessage(message);
                        
                        is.close();

                    } else {
                        Log.d(TAG, "Error code:" + response.getStatusLine().getStatusCode());
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d(TAG, "" + e);
                }

            }

        }.start();
    }
    */

}
