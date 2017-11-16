package com.example.a03_asynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.text_view);


    }
    public void excute(View view){
    MyTask myTask = new MyTask();

        myTask.execute("https://tcc.taobao.com/cc/json/mobile_tel_segment.htm?tel=15850781443");


    }
    class MyTask extends AsyncTask<String,Integer,String>{

        @Override
        protected String doInBackground(String... params) {
            Log.i("---","doInBackground");



            try {
                HttpClient client = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet(params[0]);

                HttpResponse response = client.execute(httpGet);

                int statusCode = response.getStatusLine().getStatusCode();

                if (statusCode==200){
                    HttpEntity entity = response.getEntity();
                    InputStream inputStream = entity.getContent();

                    //转换成字符串
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"utf-8");
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                    String string = null;
                    StringBuilder builder = new StringBuilder();
                    while ((string = bufferedReader.readLine()) != null){
                        builder.append(string);
                    }

                    bufferedReader.close();

                    return builder.toString();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;

        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected void onPostExecute(String s) {

            Log.i("---","onPostExecute"+s);

            textView.setText(s);
        }
    }
}
