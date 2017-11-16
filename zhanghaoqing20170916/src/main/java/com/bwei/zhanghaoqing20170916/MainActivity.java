package com.bwei.zhanghaoqing20170916;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.bwei.zhanghaoqing20170916.inter.NetInfoCallBack;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private List<DataDataBean.NewslistBean> list = new ArrayList<>();
    private PullToRefreshListView refreshListView;


   private int num=1;
    private NewsAdapter newsAdapter;
    private ILoadingLayout startLabels;
    private ILoadingLayout endLabels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        refreshListView = (PullToRefreshListView) findViewById(R.id.refresh_list_view);


        //获取数据
        getData();



        refreshListView.setMode(PullToRefreshBase.Mode.BOTH);


        startLabels = refreshListView.getLoadingLayoutProxy(true, false);
        startLabels.setPullLabel("下拉刷新");
        startLabels.setRefreshingLabel("正在拉");
        startLabels.setReleaseLabel("放开刷新");
        endLabels = refreshListView.getLoadingLayoutProxy(false, true);
        endLabels.setPullLabel("上拉刷新");
        endLabels.setRefreshingLabel("正在载入...");
        endLabels.setReleaseLabel("放开刷新...");
        refreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(final PullToRefreshBase<ListView> refreshView) {


                num++;
                getData();



            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

                getOneData();


            }
        });
    }

    private void getOneData() {

        GetNetData.getData(MainActivity.this, new NetInfoCallBack() {
            @Override
            public void getString(String s) {
                Gson gson = new Gson();
                DataDataBean dataDataBean = gson.fromJson(s, DataDataBean.class);

                list.addAll(dataDataBean.getNewslist());

                setAdapter();

                refreshListView.onRefreshComplete();
            }
        });
    }

    private void getData() {
        GetNetData.FuoQuStringData(num, new NetInfoCallBack() {
            @Override
            public void getString(String s) {
                Gson gson = new Gson();
                DataDataBean dataDataBean = gson.fromJson(s, DataDataBean.class);

                list.addAll(0,dataDataBean.getNewslist());

                setAdapter();

                refreshListView.onRefreshComplete();

                startLabels.setLastUpdatedLabel("上次刷新"+new SimpleDateFormat("HH:mm").format(new Date(System.currentTimeMillis())));


            }
        },MainActivity.this);


    }


    private void setAdapter() {

        if (newsAdapter==null){
            newsAdapter = new NewsAdapter(MainActivity.this,list);

            refreshListView.setAdapter(newsAdapter);
        }else {
            newsAdapter.notifyDataSetChanged();
        }

    }

}
