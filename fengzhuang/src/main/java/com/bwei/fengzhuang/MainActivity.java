package com.bwei.fengzhuang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.bwei.fengzhuang.base.BaseActivty;

public class MainActivity extends BaseActivty {


    private TextView textView;

    //得到数据
    @Override
    protected void getData() {

        textView.setText("这是继承了baseActivity");
    }

    //找到控件
    @Override
    protected void initData() {

        textView = (TextView) findViewById(R.id.text01);
    }

    @Override
    protected int getLayouId() {
        return R.layout.activity_main;
    }
}
