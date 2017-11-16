package com.bwei.lunbotu.appaction;

import android.app.Application;

import com.bwei.lunbotu.util.ImagelaoderUtil;

/**
 * Created by MK on 2017/9/12.
 */
public class imageappaction extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        ImagelaoderUtil.initconfig(this);
    }
}
