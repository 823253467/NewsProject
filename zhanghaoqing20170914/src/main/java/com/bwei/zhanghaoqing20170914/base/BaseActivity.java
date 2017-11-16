package com.bwei.zhanghaoqing20170914.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by MK on 2017/9/14.
 *封装activity
 */
public abstract class BaseActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        getfindviewID();
        getData();
    }

    protected abstract void getData();

    protected abstract void getfindviewID();

    protected abstract int getLayoutID();
    


}
