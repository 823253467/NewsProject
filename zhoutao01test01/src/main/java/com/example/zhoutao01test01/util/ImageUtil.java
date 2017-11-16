package com.example.zhoutao01test01.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by MK on 2017/9/2.
 */
public class ImageUtil {
    ImageView imageView;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==0){
                Bitmap bitmap = (Bitmap) msg.obj;
                imageView.setImageBitmap(bitmap);
            }
        }
    };
    public ImageUtil(final String pic_url, ImageView imageView) {
        this.imageView=imageView;
        new Thread(){
            @Override
            public void run() {

                try {
                    URL url = new URL(pic_url);
                   HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setReadTimeout(5000);
                    connection.setConnectTimeout(5000);

                    int responseCode = connection.getResponseCode();
                    if (responseCode==200){
                        InputStream inputStream = connection.getInputStream();

                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

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
