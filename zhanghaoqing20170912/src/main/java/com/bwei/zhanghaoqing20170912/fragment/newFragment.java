package com.bwei.zhanghaoqing20170912.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.bwei.zhanghaoqing20170912.R;
import com.bwei.zhanghaoqing20170912.bean.DataDataBean;
import com.bwei.zhanghaoqing20170912.fragment.adpter.MyAdapter;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by MK on 2017/9/12.
 */
public class newFragment extends Fragment{

    private ListView listview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_view, container, false);
        listview = (ListView) view.findViewById(R.id.listview01);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle bundle = getArguments();
        String string = bundle.getString("name");

        getDataFromNet(string);
    }
    private void getDataFromNet(final String string) {
        AsyncTask<Void, Void, String> asyncTask = new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {

                String path = "http://v.juhe.cn/toutiao/index?type="+string+"&key=597b4f9dcb50e051fd725a9ec54d6653";
                try {
                    URL url = new URL(path);

                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    //设置
                    connection.setRequestMethod("GET");
                    connection.setReadTimeout(5000);
                    connection.setConnectTimeout(5000);

                    //获取
                    int responseCode = connection.getResponseCode();
                    if (responseCode == 200){
                        InputStream inputStream = connection.getInputStream();

                        String json = streamToString(inputStream,"utf-8");

                        return json;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                return "";
            }

            @Override
            protected void onPostExecute(String json) {
                Gson gson = new Gson();

                DataDataBean dataDataBean = gson.fromJson(json, DataDataBean.class);

                List<DataDataBean.ResultBean.DataBean> list = dataDataBean.getResult().getData();

                MyAdapter myAdapter = new MyAdapter(getActivity(), list);

                listview.setAdapter(myAdapter);

            }
        };

        asyncTask.execute();

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