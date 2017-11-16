package com.bwei.lunbotu;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bwei.lunbotu.fragment.FaXianFragment;
import com.bwei.lunbotu.fragment.ShouYeFragment;
import com.bwei.lunbotu.fragment.WoFragment;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private List<String> list;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==0){
                int currentItem = viewPager.getCurrentItem();

                viewPager.setCurrentItem(currentItem+1);

                handler.sendEmptyMessageDelayed(0,1000);


            }
        }
    };
    private LinearLayout linearLayout;
    private List<ImageView> image;
    private FrameLayout frameLayout;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        viewPager = (ViewPager) findViewById(R.id.viewpager01);
        linearLayout = (LinearLayout) findViewById(R.id.linear);
        frameLayout = (FrameLayout) findViewById(R.id.frame01);
        radioGroup = (RadioGroup) findViewById(R.id.group01);

        FragmentTransaction transaction =  getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame01,new ShouYeFragment()).commit();

        list = new ArrayList<>();
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505151721118&di=649c9a43aed72fbc4d99ec1a031510c6&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F015c7d574b9f8f6ac72525aee98351.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505151956771&di=0eb6f306991d24b67a13ceb336f80102&imgtype=0&src=http%3A%2F%2Fi0.hdslb.com%2Fbfs%2Farchive%2F00613def3f1beb7a35ae136341be2b589bc46a2d.jpg_320x200.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505151847685&di=c7a4b5d08ec43fa629bcb690039a7629&imgtype=0&src=http%3A%2F%2Fattimg.dospy.com%2Fimg%2Fday_080625%2F20080625_2e91a10c444877e88827vri2ZKdGMvQo.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505151825129&di=70bf74b87d8a15cb91a2d79f15ed0eaf&imgtype=0&src=http%3A%2F%2Fattimg.dospy.com%2Fimg%2Fday_081016%2F20081016_fee215664d5740e56c13E2YB8giERFEX.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505746504&di=930c4d677a02328a142d6fa85ed14580&imgtype=jpg&er=1&src=http%3A%2F%2Fattimg.dospy.com%2Fimg%2Fday_090113%2F20090113_6ac58b42bea94f0b318e1B6BZb5lPZl5.jpg");

        initDoc();
        //适配器
        viewPager.setAdapter(new PagerAdapter() {
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
                ImageView imageView = new ImageView(MainActivity.this);

                imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                ImageLoader.getInstance().displayImage(list.get(position%list.size()),imageView);

                imageView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        boolean istiao = true;

                        switch (motionEvent.getAction()){
                            case MotionEvent.ACTION_DOWN://按下
                                handler.removeCallbacksAndMessages(null);
                                break;
                            case MotionEvent.ACTION_MOVE://移动
                                istiao=false;
                                handler.removeCallbacksAndMessages(null);
                                break;
                            case MotionEvent.ACTION_CANCEL://取消
                                istiao=false;
                                handler.sendEmptyMessageDelayed(0,1000);
                                break;
                            case MotionEvent.ACTION_UP://抬起
                                if (istiao){
                                    Toast.makeText(MainActivity.this,"sasas",Toast.LENGTH_SHORT).show();
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
        });


        viewPager.setCurrentItem(list.size()*10000);
        handler.sendEmptyMessageDelayed(0,1000);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for (int i=0;i<image.size();i++){
                    if (i==position%image.size()){
                        image.get(i).setImageResource(R.drawable.doc_yew);
                    }else {
                        image.get(i).setImageResource(R.drawable.doc_no);

                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
                switch (i){
                    case R.id.radio01:

                        transaction1.replace(R.id.frame01,new ShouYeFragment()).commit();

                        break;
                    case R.id.radio02:
                        transaction1.replace(R.id.frame01,new FaXianFragment()).commit();

                        break;
                    case R.id.radio03:
                        transaction1.replace(R.id.frame01,new WoFragment()).commit();

                        break;

                }
            }
        });
    }

    private void initDoc() {
        image = new ArrayList<>();

        linearLayout.removeAllViews();

        for (int i=0;i<list.size();i++){
            ImageView imageView = new ImageView(MainActivity.this);

            if (i==0){

                imageView.setImageResource(R.drawable.doc_yew);
            }else {
                imageView.setImageResource(R.drawable.doc_no);
            }

            image.add(imageView);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(5,0,5,0);
            linearLayout.addView(imageView,params);
        }
    }
}
