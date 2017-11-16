package com.example.zhanghaoqing20170902;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.zhanghaoqing20170902.bean.JavaBenan;
import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.text01);

    }

    public void btn(View view) {
        //异步任务块
        AsyncTask<Void, Void, String> asyncTask = new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {


                try {
                    //httpclent请求
                    HttpClient client = new DefaultHttpClient();
                    //url路径
                    String path = "http://v.juhe.cn/weather/index?format=2&cityname=%E8%8B%8F%E5%B7%9E&key=312d4e9966d1e554a1668a8190f6135b";
                    HttpGet httpGet = new HttpGet(path);
                    HttpResponse response = client.execute(httpGet);

                    int statusCode = response.getStatusLine().getStatusCode();
                    if (statusCode == 200) {
                        InputStream inputStream = response.getEntity().getContent();

                        String json = adad(inputStream, "utf-8");
                        return json;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }


                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                Gson gson = new Gson();
                JavaBenan javaBenan = gson.fromJson(s, JavaBenan.class);
                String time = javaBenan.getResult().getSk().getTime();
                textView.setText(time);

            }
        };
        asyncTask.execute();
    }

    private String adad(InputStream inputStream, String s) {

        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, s);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();            String string = null;
            while ((string=bufferedReader.readLine())!=null){
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

