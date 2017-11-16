package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    private ImageView imageview;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                Bitmap b = (Bitmap) msg.obj;
                imageview.setImageBitmap(b);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        imageview = (ImageView) findViewById(R.id.ima);
    }
    public void getpic(View view){
        new Thread(){
            @Override
            public void run() {
                super.run();
                String path = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1503854327078&di=08bdf32f7a117deafd580ca006b80a67&imgtype=0&src=http%3A%2F%2Fnews.k618.cn%2Fpic%2Fdmyx%2F201505%2FW020150501335817970176.jpg";

                try {
                    URL url = new URL(path);

                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("get");
                    urlConnection.setConnectTimeout(5000);//连接超时
                    urlConnection.setReadTimeout(5000);//读取超时
                    int responseCode = urlConnection.getResponseCode();
                    if (responseCode==200){
                        InputStream inputStream = urlConnection.getInputStream();
                        //八返回字节流转换图片资源
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        //设置....使用handler发送到主线程
//                 imageview.setImageBitmap(bitmap);
                        Message message = Message.obtain();
                        message.what=0;
                        message.obj=bitmap;
                        handler.sendMessage(message);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }
}
