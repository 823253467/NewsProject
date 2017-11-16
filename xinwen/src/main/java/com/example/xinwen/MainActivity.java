package com.example.xinwen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btn(View view){
        new Thread(){
            @Override
            public void run() {
                String path = "http://v.juhe.cn/toutiao/index";


                try {
                    URL url = new URL(path);

                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setConnectTimeout(5000);
                    urlConnection.setReadTimeout(5000);

                    urlConnection.setDoOutput(true);
                    urlConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
                    String params = "type=top&key=597b4f9dcb50e051fd725a9ec54d6653";
                    urlConnection.getOutputStream().write(params.getBytes());
                    int responseCode = urlConnection.getResponseCode();
                    if (responseCode==200){
                        InputStream inputStream = urlConnection.getInputStream();

                        String json = streamToString(inputStream,"utf-8");

                        Log.i("---",json);
                    }



                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }

    private String streamToString(InputStream inputStream,String charset) {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream,charset);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String s = null;
            StringBuilder builder = new StringBuilder();
            while ((s = bufferedReader.readLine()) != null){
                builder.append(s);
            }

            bufferedReader.close();
            return builder.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return  null;
    }

}
