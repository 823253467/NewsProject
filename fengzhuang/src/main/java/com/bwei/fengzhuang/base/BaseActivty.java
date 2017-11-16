package com.bwei.fengzhuang.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by MK on 2017/9/13.
 */
public abstract class BaseActivty extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayouId());
        initData();
        getData();
    }

    protected abstract void getData();

    protected abstract void initData();

    protected abstract int getLayouId();


    
}
