package com.bwei.zhanghaoqing20170921;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bwei.zhanghaoqing20170921.view.XListView;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private XListView xListView;
    List<JavaBean.NewslistBean> list = new ArrayList();

    private int num = 1;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xListView = (XListView) findViewById(R.id.x_listview);

        getData();
        xListView.setPullRefreshEnable(true);
        xListView.setPullLoadEnable(true);
        xListView.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {

                getOneData();

            }

            @Override
            public void onLoadMore() {

                num++;
                getData();
            }
        });



    }

    private void getOneData() {
        AsyncTask<Void, Void, String> asyncTask = new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                try {
                    String  path="https://api.tianapi.com/wxnew/?key=8d6e3228d25298f13af4fc40ce6c9679&num="+1;
                    URL url = new URL(path);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    connection.setRequestMethod("GET");
                    connection.setReadTimeout(5000);
                    connection.setConnectTimeout(5000);
                    int responseCode = connection.getResponseCode();
                    if (responseCode==200){
                        InputStream inputStream = connection.getInputStream();

                        String string = streamToString(inputStream, "utf-8");

                        return string;
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                if (s!=null&&!s.isEmpty()){

                    Gson gson = new Gson();
                    JavaBean javaBean = gson.fromJson(s, JavaBean.class);

                    if (javaBean!=null&&javaBean.getNewslist()!=null){

                        list.addAll(javaBean.getNewslist());

                        setAdapter();
                        xListView.stopRefresh();
                    }






                }

            }
        };
        asyncTask.execute();
    }

    private void getData() {

        AsyncTask<Void, Void, String> asyncTask = new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                try {
                    String  path="https://api.tianapi.com/wxnew/?key=8d6e3228d25298f13af4fc40ce6c9679&num="+num;
                    URL url = new URL(path);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    
                    connection.setRequestMethod("GET");
                    connection.setReadTimeout(5000);
                    connection.setConnectTimeout(5000);
                    int responseCode = connection.getResponseCode();
                    if (responseCode==200){
                        InputStream inputStream = connection.getInputStream();

                        String string = streamToString(inputStream, "utf-8");

                        return string;
                    }
                    

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                if (s!=null&&!s.isEmpty()){

                    Gson gson = new Gson();
                    JavaBean javaBean = gson.fromJson(s, JavaBean.class);

                    if (javaBean!=null&&javaBean.getNewslist()!=null){

                        list.addAll(0,javaBean.getNewslist());

                        setAdapter();
                        xListView.stopLoadMore();
                    }






                }

            }
        };
        asyncTask.execute();

    }

    private void setAdapter() {
        if (adapter==null){

            adapter = new MyAdapter(MainActivity.this, list);
            xListView.setAdapter(adapter);
        }else {

            adapter.notifyDataSetChanged();
        }
    }

    private String streamToString(InputStream inputStream, String gbk) {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String s = null;
            while ((s=bufferedReader.readLine())!=null){
                stringBuilder.append(s);
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    };


}
