package com.example.zhoutao01test01;

import android.net.http.SslCertificate;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.zhoutao01test01.bean.JavaBean;
import com.example.zhoutao01test01.dao.Dao;
import com.example.zhoutao01test01.util.ImageUtil;
import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by MK on 2017/9/2.
 */
public class Second extends AppCompatActivity {

    private ListView listview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.second);
        listview = (ListView) findViewById(R.id.listview01);

        AsyncTask<Void, Void, String> asyncTask = new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {


                try {

                    SSLSocketFactory.getSocketFactory().setHostnameVerifier(new AllowAllHostnameVerifier());


                    HttpClient client = new DefaultHttpClient();
                    String path = "http://api.expoon.com/AppNews/getNewsList/type/1/p/1";
                    HttpGet httpGet = new HttpGet(path);
                    HttpResponse response = client.execute(httpGet);
                    int statusCode = response.getStatusLine().getStatusCode();
                    if (statusCode == 200) {
                        InputStream inputStream = response.getEntity().getContent();

                        String json = streamToString(inputStream, "utf-8");
                        return json;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }


                return null;

            }

            @Override
            protected void onPostExecute(String s) {
//                Log.i("---",s);
                Gson gson = new Gson();
                JavaBean javaBean = gson.fromJson(s, JavaBean.class);
                /*List<JavaBean.DataBean> data = javaBean.getData();
                for (JavaBean.DataBean ad: data) {
                    new Dao(Second.this).add(ad.getNews_title(),ad.getPic_url());
                }*/

                final List<JavaBean.DataBean> list = javaBean.getData();
                final BaseAdapter baseAdapter = new BaseAdapter() {
                    @Override
                    public int getCount() {
                        return list.size();
                    }

                    @Override
                    public Object getItem(int position) {
                        return list.get(position);
                    }

                    @Override
                    public long getItemId(int position) {
                        return position;
                    }

                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        ViewHolder holder;
                        if (convertView == null) {
                            convertView = View.inflate(Second.this, R.layout.item_list, null);
                            holder = new ViewHolder();
                            holder.textView = (TextView) convertView.findViewById(R.id.text01);
                            holder.imageView = (ImageView) convertView.findViewById(R.id.image01);
                            convertView.setTag(holder);
                        } else {
                            holder = (ViewHolder) convertView.getTag();
                        }
                        holder.textView.setText(list.get(position).getNews_title());
                        new ImageUtil(list.get(position).getPic_url(), holder.imageView);

                        return convertView;
                    }

                    class ViewHolder {
                        ImageView imageView;
                        TextView textView;
                    }
                };
                listview.setAdapter(baseAdapter);

                /*listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        new Dao(Second.this).delete(list.get(position).getNews_title());
                        list.clear();
                        baseAdapter.notifyDataSetChanged();
                        return false;
                    }
                });*/
            }
        };

        asyncTask.execute();
    }

    private String streamToString(InputStream inputStream, String gbk) {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String s = null;
            while ((s = bufferedReader.readLine()) != null) {
                stringBuilder.append(s);
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    ;
}
