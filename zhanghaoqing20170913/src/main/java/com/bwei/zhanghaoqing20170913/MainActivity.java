package com.bwei.zhanghaoqing20170913;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bwei.zhanghaoqing20170913.adapter.Myadapter;
import com.bwei.zhanghaoqing20170913.bean.JavaBean;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {


            if (msg.what==0){
                int currentItem = viewPager.getCurrentItem();
                viewPager.setCurrentItem(currentItem+1);
                handler.sendEmptyMessageDelayed(0,1000);

            }
        }
    };
    private List<ImageView> image;
    private LinearLayout linearLayout;
    private List<JavaBean.NewslistBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.viewpager);

        linearLayout = (LinearLayout) findViewById(R.id.linear01);

        AsyncTask<Void, Void, String> asyncTask = new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                Log.i("--=====sasa-","as");
                try {
                    URL url = new URL("https://api.tianapi.com/wxnew/?key=8d6e3228d25298f13af4fc40ce6c9679&num=6&page=1");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    connection.setRequestMethod("GET");
                    connection.setReadTimeout(5000);
                    connection.setConnectTimeout(5000);
                    int responseCode = connection.getResponseCode();
                    if (responseCode==200){
                        InputStream inputStream = connection.getInputStream();
                        String string = StringStream(inputStream,"utf-8");

                        Log.i("--=====sasa-",string);
                        return string;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                Gson gson = new Gson();
                JavaBean javaBean = gson.fromJson(s, JavaBean.class);

                list = javaBean.getNewslist();

                getinitDoc();
                Log.i("--aaaaaasasaasa-", list.get(1).getTitle());
                Myadapter myadapter = new Myadapter(MainActivity.this, list,handler);
                viewPager.setAdapter(myadapter);
                viewPager.setCurrentItem(list.size()*10000);
               handler.sendEmptyMessageDelayed(0,1000);

                viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {

                        for (int i=0;i<image.size();i++){
                            if (i==position%image.size()){
                                image.get(i).setImageResource(R.drawable.yes);
                            }else {
                                image.get(i).setImageResource(R.drawable.no);

                            }
                        }
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });



            }
        };
        asyncTask.execute();

    }

    private void getinitDoc() {

        image = new ArrayList<>();

        linearLayout.removeAllViews();
        for (int i =0 ;i<list.size();i++){
            ImageView imageView = new ImageView(MainActivity.this);
            if (i==0){
                imageView.setImageResource(R.drawable.yes);
            }else {
                imageView.setImageResource(R.drawable.no);
            }
            image.add(imageView);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(5,0,5,0);
            linearLayout.addView(imageView,params);
        }

    }


    private String StringStream(InputStream inputStream, String s) {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream,s);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuilder stringBuilder = new StringBuilder();
            String string = null;

            while((string=bufferedReader.readLine())!=null){
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
