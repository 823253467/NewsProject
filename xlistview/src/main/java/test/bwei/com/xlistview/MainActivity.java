package test.bwei.com.xlistview;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import test.bwei.com.xlistview.adapter.MyAdapter;
import test.bwei.com.xlistview.bean.JavaBean;
import test.bwei.com.xlistview.view.XListView;

public class MainActivity extends AppCompatActivity implements XListView.IXListViewListener {
    private int num=10;
    private XListView xListView;
    private List<JavaBean.ResultBean.ListBean> list = new ArrayList<>();
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xListView = (XListView) findViewById(R.id.xlistview);

        xListView.setPullRefreshEnable(true);
        xListView.setPullLoadEnable(true);


        xListView.setXListViewListener(this);
        //获取数据
        getshuju();


    }

    private void getshuju() {
        AsyncTask<Void, Void, String> asyncTask = new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String path ="http://v.juhe.cn/weixin/query";
                try {
                    URL url = new URL(path);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setReadTimeout(5000);
                    connection.setConnectTimeout(5000);
                    connection.setDoOutput(true);
                    connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
                    String pra ="key=19178372f8a7c069c42cd59df8d84a46&ps=10&pno="+num;
                    connection.getOutputStream().write(pra.getBytes());
                    int responseCode = connection.getResponseCode();
                    if (responseCode==200){
                        InputStream inputStream = connection.getInputStream();
                        String s = streamToString(inputStream, "utf-8");


                        return s;
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


                Log.i("-----",javaBean.getResult().getList().get(1).getTitle());
                list.addAll(javaBean.getResult().getList());
                Log.i("-----",list.toString());
                setAdapter();
                xListView.stopLoadMore();;



            }
        };
        asyncTask.execute();
    }

    private void setAdapter() {
        if (myAdapter==null){

            myAdapter = new MyAdapter(MainActivity.this, list);
            xListView.setAdapter(myAdapter);
        }else {
            myAdapter.notifyDataSetChanged();
        }
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

    @Override
    public void onRefresh() {

       /* num++;
        getshuju();*/
        num--;
        if (num>0){
            reshuju();
        }else {
            Toast.makeText(MainActivity.this,"没有最新数据了",Toast.LENGTH_SHORT).show();
            xListView.stopRefresh();//停止刷新
        }
    }

    private void reshuju() {
        AsyncTask<Void, Void, String> asyncTask = new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String path ="http://v.juhe.cn/weixin/query";
                try {
                    URL url = new URL(path);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setReadTimeout(5000);
                    connection.setConnectTimeout(5000);
                    connection.setDoOutput(true);
                    connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
                    String pra ="key=19178372f8a7c069c42cd59df8d84a46&ps=10&pno="+num;
                    connection.getOutputStream().write(pra.getBytes());
                    int responseCode = connection.getResponseCode();
                    if (responseCode==200){
                        InputStream inputStream = connection.getInputStream();
                        String s = streamToString(inputStream, "utf-8");


                        return s;
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


                Log.i("-----",javaBean.getResult().getList().get(1).getTitle());
                list.addAll(0,javaBean.getResult().getList());
                Log.i("-----",list.toString());
                setAdapter();
                xListView.stopRefresh();
                Date date = new Date(System.currentTimeMillis());
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
                xListView.setRefreshTime(dateFormat.format(date));


            }
        };
        asyncTask.execute();
    }
    //
    @Override
    public void onLoadMore() {

        num++;

        getshuju();
    }


}
