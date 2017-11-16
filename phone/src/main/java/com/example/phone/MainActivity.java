package com.example.phone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.edit_text);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String string = s.toString();
                if (string.length()==11){
                    searPhone(string);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    private void searPhone(final String string) {
        new Thread(){
            @Override
            public void run() {
                String path = "https://tcc.taobao.com/cc/json/mobile_tel_segment.htm?tel="+string;

                try {
                    URL url = new URL(path);
                   HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("get");
                    connection.setReadTimeout(5000);
                    connection.setConnectTimeout(5000);

                    int responseCode = connection.getResponseCode();
                    if (responseCode == 200){
                        
                        InputStream inputStream = connection.getInputStream();
                        
                        String string = streamToString(inputStream,"gbk");

                        String json = string.substring(string.indexOf("{"));
                        Log.i("string",json);

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }.start();
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
