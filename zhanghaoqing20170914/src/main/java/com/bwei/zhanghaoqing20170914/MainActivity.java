package com.bwei.zhanghaoqing20170914;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bwei.zhanghaoqing20170914.base.BaseActivity;

public class MainActivity extends BaseActivity {




    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }
    //得到控件
    @Override
    protected void getfindviewID() {

    }

    @Override
    protected void getData() {

        if (isConnection(this)) {

            //下面这个方法是自定义的网络请求数据方法,就不上传了
            //getDataByWeb();
            Intent intent = new Intent(MainActivity.this, SceondActivity.class);

            startActivity(intent);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("网络链接中断,是否设置链接网络");
            builder.setNegativeButton("取消", null);
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
//                    两种跳转都可以跳转到到设置网络
                    //Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                    Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
//                    intent.setAction("android.settings.WIRELESS_SETTINGS");
                    startActivity(intent);
                }
            });
            builder.create().show();
        }



    }



    public boolean isConnection(MainActivity context) {
        ConnectivityManager manager = (ConnectivityManager)

                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return (info != null && info.isAvailable());

    }




}
