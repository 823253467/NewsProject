package com.bwei.zhanghaoqing20170913;

import android.app.Application;

import com.bwei.zhanghaoqing20170913.util.ImageUtils;

/**
 * Created by MK on 2017/9/13.
 */
public class appliaction extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        ImageUtils.initconfig(this);
    }
}
