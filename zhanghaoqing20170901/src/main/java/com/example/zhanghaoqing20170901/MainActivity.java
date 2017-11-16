package com.example.zhanghaoqing20170901;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.zhanghaoqing20170901.adapter.MyAdapter;
import com.example.zhanghaoqing20170901.bean.JavaBean;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listview;
    private List<JavaBean.ResultBean.DataBean> list;
    private Handler handler = new Handler(){



        @Override
        public void handleMessage(Message msg) {
            if (msg.what==0){

                JavaBean javabean = (JavaBean) msg.obj;
                list = javabean.getResult().getData();

                MyAdapter myAdapter = new MyAdapter(MainActivity.this, list);
                listview.setAdapter(myAdapter);

            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = (ListView) findViewById(R.id.listview);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (list!=null){
                    Intent intent = new Intent(MainActivity.this,WebActivity.class);
                    intent.putExtra("url",list.get(position).getUrl());
                    startActivity(intent);
                }
            }
        });


    }

    public void btn(View view) {
        if (isnetconnectwd()){

            getNet();
        }else {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("警告");
            builder.setMessage("网络不可用，是否设置");
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                    startActivity(intent);
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.show();
        }
    }
    private boolean isnetconnectwd(){

        ConnectivityManager manager = (ConnectivityManager) MainActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = manager.getActiveNetworkInfo();//获取网络连接的信息

        if (networkInfo!=null){
            return networkInfo.isAvailable();
        }
        return false;
    }
    private void getNet() {
        new Thread() {
            @Override
            public void run() {
                String path = "http://v.juhe.cn/toutiao/index";


                try {
                    URL url = new URL(path);

                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setReadTimeout(5000);
                    connection.setConnectTimeout(5000);

                    connection.setDoOutput(true);
                    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    String pra = "type=top&key=84614b3b4312c924141d0ec348cc8243";
                    connection.getOutputStream().write(pra.getBytes());

                    int responseCode = connection.getResponseCode();
                    if (responseCode == 200) {

                        InputStream inputStream = connection.getInputStream();

                        String json = zifu(inputStream, "utf-8");
                        Gson gson = new Gson();
                        JavaBean javaBean = gson.fromJson(json, JavaBean.class);
                        //List<JavaBean.ResultBean.DataBean> list = javaBean.getResult().getData();
                      //  Log.i("---", list+"");
                        Message message = Message.obtain();

                        message.what=0;
                        message.obj=javaBean;
                        handler.sendMessage(message);



                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }

    private String zifu(InputStream inputStream, String s) {

        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, s);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuilder stringBuilder = new StringBuilder();
            String string = null;

            while ((string = bufferedReader.readLine()) != null) {
                stringBuilder.append(string);
            }

            bufferedReader.close();
            return stringBuilder.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }


}
