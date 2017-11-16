package com.bwei.zhanghaoqing20170915;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.bwei.zhanghaoqing20170915.adpter.ListAdapter;
import com.bwei.zhanghaoqing20170915.bean.JavaBean;
import com.bwei.zhanghaoqing20170915.util.stringSastreamutil;
import com.google.gson.Gson;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==0){

                s = (String) msg.obj;

//                Log.i("-----",s);
                Gson gson = new Gson();
                JavaBean javaBean = gson.fromJson(s, JavaBean.class);
                List<JavaBean.NewslistBean> list = javaBean.getNewslist();
                //Log.i("=====",list.get(1).getTitle());

                ListAdapter adapter = new ListAdapter(MainActivity.this, list);
                listView.setAdapter(adapter);


            }

        }
    };
    private String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listview);

        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    String path = "https://api.tianapi.com/wxnew/?key=8d6e3228d25298f13af4fc40ce6c9679&num=10";
                    URL url = new URL(path);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);
                    int responseCode = connection.getResponseCode();
                    if (responseCode==200){
                        InputStream inputStream = connection.getInputStream();

                        String s = stringSastreamutil.getstringread(inputStream, "utf-8");

                        Message message = Message.obtain();
                        message.what=0;
                        message.obj=s;
                        handler.sendMessage(message);

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }


        }.start();


    }


}
