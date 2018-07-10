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

	// 创建数据库
	private void createDB() {
		// 创建数据库：
		// 实例化数据库助手类
		MyHelper helper = new MyHelper(mContext, "news.db", 1);
		// 获得数据库对象
		helper.getWritableDatabase().close();
	}

	/**
	 * 
	 * 向数据库中插入数据
	 */
	public void insert(News news) {

		MyHelper helper = new MyHelper(mContext, "news.db", 1);
		// 获得可写的数据库对象
		SQLiteDatabase db = helper.getWritableDatabase();
		// 一条数据的封装
		ContentValues cv = new ContentValues();
		// 封装数据
		cv.put("uniquekey", news.getUniquekey());
		cv.put("title", news.getTitle());
		cv.put("date", news.getDate());
		cv.put("category", news.getCategory());
		cv.put("author_name", news.getAuthor_name());
		cv.put("url", news.getUrl());
		cv.put("thumbnail_pic_s", news.getThumbnail_pic_s());
		// 插入数据
		// 第一个参数：表名
		// 第二个参数：空值保护
		// 第三个参数：插入的数据
		long result = db.insert(MyHelper.DB_TABLE_CACHE, null, cv);
		db.close();
		if (result != -1)
			// TODO 提示信息修改一下
			/* Toast.makeText(mContext, "插入数据成功！", Toast.LENGTH_SHORT).show(); */
			Log.d("DB", "数据插入失败！");
	}

	/**
	 * 删除数据库 中的数据
	 */
	public void delete() {

		MyHelper helper = new MyHelper(mContext, "news.db", 1);
		// 获得可写的数据库对象
		SQLiteDatabase db = helper.getWritableDatabase();
		// 删除数据
		// 第一个参数：表名
		// 第二个参数：where子句 where category = '头条' and author_name = '腾讯网'
		// 第三个参数：where子句中?的数值
		db.execSQL("delete  from " + MyHelper.DB_TABLE_CACHE);

		// 关闭数据库
		db.close();
	}

	/**
	 * 查询
	 */
	public ArrayList<News> query() {

		ArrayList<News> list = new ArrayList<News>();

		MyHelper helper = new MyHelper(mContext, "news.db", 1);
		// 获得可写的数据库对象
		SQLiteDatabase db = helper.getReadableDatabase();
		// 数据查询
		// 第一个参数：表名
		Cursor cursor = db.query(MyHelper.DB_TABLE_CACHE, null, null, null,
				null, null, null);
		// 循环取出
		while (cursor.moveToNext()) {
			// 取值
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
		// 关闭游标和数据库
		cursor.close();
		db.close();

		return list;
	}

}
