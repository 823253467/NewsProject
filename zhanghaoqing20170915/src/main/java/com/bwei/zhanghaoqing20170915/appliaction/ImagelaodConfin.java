package com.bwei.zhanghaoqing20170915.appliaction;

import android.app.Application;

import com.bwei.zhanghaoqing20170915.util.ImageLaoderUtil;

/**
 * Created by MK on 2017/9/15.
 */
public class ImagelaodConfin extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        ImageLaoderUtil.getinitconfig(this);
    }
}
