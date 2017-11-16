package com.bwei.zhanghaoqing20170916;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.provider.Settings;



import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by MK on 2017/9/14.
 */
public class WangLuo {
    public static boolean isWL(Context context) {

        ConnectivityManager manager = (ConnectivityManager)

                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return (info != null && info.isAvailable());
    }

    public static void TanKuang(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("网络链接中断,是否设置链接网络");
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                    两种跳转都可以跳转到到设置网络
//                Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
//                    intent.setAction("android.settings.WIRELESS_SETTINGS");
                context.startActivity(intent);
            }
        });
        builder.create().show();
    }


}
