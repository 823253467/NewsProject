package com.example.zhoutao01test01.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.zhoutao01test01.MyHelper.MyHelper;
import com.example.zhoutao01test01.Second;

/**
 * Created by MK on 2017/9/2.
 */
public class Dao {

    private final MyHelper myHelper;

    public Dao(Context context) {
        myHelper = new MyHelper(context);
    }

    public void add(String news_title, String pic_url) {
        SQLiteDatabase database = myHelper.getWritableDatabase();
       // database.execSQL("insert into user where title = ? and url = ?",new String[]{news_title,pic_url});
        ContentValues value = new ContentValues();
        value.put("title",news_title);
        value.put("url",pic_url);
        database.insert("user",null,value);
    }

    public void delete(String news_title) {
        SQLiteDatabase database = myHelper.getWritableDatabase();
        database.execSQL("delete from user where title = ?",new String[]{news_title});
    }
}
