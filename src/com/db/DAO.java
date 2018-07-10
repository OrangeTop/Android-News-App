package com.db;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.modle.News;

public class DAO {

	private Context mContext;

	public DAO(Context context) {
		super();
		mContext = context;
		createDB();
	}

	// �������ݿ�
	private void createDB() {
		// �������ݿ⣺
		// ʵ�������ݿ�������
		MyHelper helper = new MyHelper(mContext, "news.db", 1);
		// ������ݿ����
		helper.getWritableDatabase().close();
	}

	/**
	 * 
	 * �����ݿ��в�������
	 */
	public void insert(News news) {

		MyHelper helper = new MyHelper(mContext, "news.db", 1);
		// ��ÿ�д�����ݿ����
		SQLiteDatabase db = helper.getWritableDatabase();
		// һ�����ݵķ�װ
		ContentValues cv = new ContentValues();
		// ��װ����
		cv.put("uniquekey", news.getUniquekey());
		cv.put("title", news.getTitle());
		cv.put("date", news.getDate());
		cv.put("category", news.getCategory());
		cv.put("author_name", news.getAuthor_name());
		cv.put("url", news.getUrl());
		cv.put("thumbnail_pic_s", news.getThumbnail_pic_s());
		// ��������
		// ��һ������������
		// �ڶ�����������ֵ����
		// ���������������������
		long result = db.insert(MyHelper.DB_TABLE_CACHE, null, cv);
		db.close();
		if (result != -1)
			// TODO ��ʾ��Ϣ�޸�һ��
			/* Toast.makeText(mContext, "�������ݳɹ���", Toast.LENGTH_SHORT).show(); */
			Log.d("DB", "���ݲ���ʧ�ܣ�");
	}

	/**
	 * ɾ�����ݿ� �е�����
	 */
	public void delete() {

		MyHelper helper = new MyHelper(mContext, "news.db", 1);
		// ��ÿ�д�����ݿ����
		SQLiteDatabase db = helper.getWritableDatabase();
		// ɾ������
		// ��һ������������
		// �ڶ���������where�Ӿ� where category = 'ͷ��' and author_name = '��Ѷ��'
		// ������������where�Ӿ���?����ֵ
		db.execSQL("delete  from " + MyHelper.DB_TABLE_CACHE);

		// �ر����ݿ�
		db.close();
	}

	/**
	 * ��ѯ
	 */
	public ArrayList<News> query() {

		ArrayList<News> list = new ArrayList<News>();

		MyHelper helper = new MyHelper(mContext, "news.db", 1);
		// ��ÿ�д�����ݿ����
		SQLiteDatabase db = helper.getReadableDatabase();
		// ���ݲ�ѯ
		// ��һ������������
		Cursor cursor = db.query(MyHelper.DB_TABLE_CACHE, null, null, null,
				null, null, null);
		// ѭ��ȡ��
		while (cursor.moveToNext()) {
			// ȡֵ
			News mynew = new News();
			mynew.setUniquekey(cursor.getString(cursor
					.getColumnIndex("uniquekey")));
			mynew.setTitle(cursor.getString(cursor.getColumnIndex("title")));
			mynew.setDate(cursor.getString(cursor.getColumnIndex("date")));
			mynew.setCategory(cursor.getString(cursor
					.getColumnIndex("category")));
			mynew.setAuthor_name(cursor.getString(cursor
					.getColumnIndex("author_name")));
			mynew.setUrl(cursor.getString(cursor.getColumnIndex("url")));
			mynew.setThumbnail_pic_s(cursor.getString(cursor
					.getColumnIndex("thumbnail_pic_s")));
			list.add(mynew);
			Log.d("Jason", mynew.getTitle() + " ------------------------");
		}
		// �ر��α�����ݿ�
		cursor.close();
		db.close();

		return list;
	}

}
