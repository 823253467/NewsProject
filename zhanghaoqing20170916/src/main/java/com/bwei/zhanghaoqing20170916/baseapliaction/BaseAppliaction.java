package com.bwei.zhanghaoqing20170916.baseapliaction;

import android.app.Application;

import com.bwei.zhanghaoqing20170916.ImageLoaderUtil;

/**
 * Created by MK on 2017/9/16.
 */
public class BaseAppliaction extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        ImageLoaderUtil.getInit(this);

    }
}
