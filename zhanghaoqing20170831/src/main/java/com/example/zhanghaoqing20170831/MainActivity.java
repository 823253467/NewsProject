package com.example.zhanghaoqing20170831;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    private EditText editTedxt;

private Handler hander=new Handler(){};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTedxt = (EditText) findViewById(R.id.edittext);
        Button button = (Button) findViewById(R.id.btn);

        /*button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               agfjh();

            }
        });*/
    }

    public void btn(View view){
        new Thread(){
           @Override
          public void run() {

                try {
                String path = "http://wthrcdn.etouch.cn/weather_mini?city="+URLEncoder.encode(editTedxt.getText().toString(),"utf-8");
                    URL url = new URL(path);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setReadTimeout(5000);
                    connection.setConnectTimeout(5000);
                    int responseCode = connection.getResponseCode();
                    if (responseCode==200){
                        InputStream inputStream = connection.getInputStream();
                        String json = dedao(inputStream,"utf-8");
                        Log.i("asas",json);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
           }
       }.start();

    }

    private String dedao(InputStream inputStream, String s) {

        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, s);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String string = null;
            while ((string=bufferedReader.readLine())!=null){
                stringBuilder.append(string);
            }
            bufferedReader.close();
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    };
}
