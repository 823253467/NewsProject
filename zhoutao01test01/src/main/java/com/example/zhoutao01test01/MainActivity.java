package com.example.zhoutao01test01;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.RadioGroup;

import com.example.zhoutao01test01.fragment.Fragment01;
import com.example.zhoutao01test01.fragment.Fragment02;
import com.example.zhoutao01test01.fragment.Fragment03;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private RadioGroup group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        SharedPreferences preferences = MainActivity.this.getSharedPreferences("config", MODE_PRIVATE);
        boolean flag = preferences.getBoolean("flag", false);
        if (flag==true){
            Intent intent = new Intent(MainActivity.this, Second.class);
            startActivity(intent);
            finish();
        }
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        group = (RadioGroup) findViewById(R.id.group);
        final List<Fragment> list = new ArrayList<Fragment>();

        list.add(new Fragment01());
        list.add(new Fragment02());
        list.add(new Fragment03());
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {

                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                group.check(group.getChildAt(position).getId());
                if (position==list.size()-1){
                    SharedPreferences preferences = MainActivity.this.getSharedPreferences("config", MODE_PRIVATE);
                    preferences.edit().putBoolean("flag",true).commit();

                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}
