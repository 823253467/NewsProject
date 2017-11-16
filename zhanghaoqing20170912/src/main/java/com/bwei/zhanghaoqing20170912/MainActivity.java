package com.bwei.zhanghaoqing20170912;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;

import com.bwei.zhanghaoqing20170912.fragment.newFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        final List<String> list = new ArrayList<String>();

        list.add("头条");
        list.add("社会");
        list.add("国内");
        list.add("国际");
        list.add("娱乐");
        list.add("体育");
        list.add("军事");
        list.add("科技");
        list.add("财经");
        list.add("时尚");


        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public CharSequence getPageTitle(int position) {
                return list.get(position);
            }

            @Override
            public Fragment getItem(int position) {
                newFragment newFragment = new newFragment();

                Bundle bundle = new Bundle();
                if (list.get(position).equals("头条")){
                    bundle.putString("name","top");
                }else if (list.get(position).equals("社会")){
                    bundle.putString("name","shehui");
                }else if (list.get(position).equals("国内")){
                    bundle.putString("name","guonei");
                }else if (list.get(position).equals("国际")){
                    bundle.putString("name","guoji");
                }else if (list.get(position).equals("娱乐")){
                    bundle.putString("name","yule");
                }else if (list.get(position).equals("体育")){
                    bundle.putString("name","tiyu");
                }else if (list.get(position).equals("军事")){
                    bundle.putString("name","junshi");
                }else if (list.get(position).equals("科技")){
                    bundle.putString("name","keji");
                }else if (list.get(position).equals("财经")){
                    bundle.putString("name","caijing");
                }else if (list.get(position).equals("时尚")){
                    bundle.putString("name","shishang");
                }

                newFragment.setArguments(bundle);
                return newFragment;
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });


        tabLayout.setupWithViewPager(viewPager);


    }
}
