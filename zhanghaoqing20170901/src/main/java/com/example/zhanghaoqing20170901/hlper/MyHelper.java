package com.example.zhanghaoqing20170901.hlper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by MK on 2017/9/2.
 */
public class MyHelper extends SQLiteOpenHelper{
    public MyHelper(Context context) {
        super(context, "bawei.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user (uid integer primary key autoincrement,title varchar(20),url varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
