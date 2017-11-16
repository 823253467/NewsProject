package com.bwei.zhanghaoqing20170913.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bwei.zhanghaoqing20170913.MainActivity;
import com.bwei.zhanghaoqing20170913.bean.JavaBean;
import com.bwei.zhanghaoqing20170913.util.ImageUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by MK on 2017/9/13.
 */
public class Myadapter extends PagerAdapter {
    Context context;
    List<JavaBean.NewslistBean> list;
    Handler handler;
    public Myadapter(Context context, List<JavaBean.NewslistBean> list, Handler handler) {
        this.context=context;
        this.list=list;
        this.handler=handler;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        ImageLoader.getInstance().displayImage(list.get(position%list.size()).getPicUrl(),imageView, ImageUtils.getoption());


        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                boolean is =true;
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        handler.removeCallbacksAndMessages(null);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        is = false;
                        handler.removeCallbacksAndMessages(null);


                        break;
                    case MotionEvent.ACTION_CANCEL:
                        is = false;
                        handler.sendEmptyMessageDelayed(0,1000);
                        break;
                    case MotionEvent.ACTION_UP:

                        if (is){
                            Toast.makeText(context,"跳转页面",Toast.LENGTH_SHORT).show();

                            /*Intent intent = new Intent();

                            context.startActivity();*/
                        }
                        handler.sendEmptyMessageDelayed(0,1000);
                        break;

                }
                return true;
            }
        });

        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((View) object);
    }

}
