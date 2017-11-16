package test.bwei.com.zhoukaotest02.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import test.bwei.com.zhoukaotest02.R;
import test.bwei.com.zhoukaotest02.bean.JavaBean;
import test.bwei.com.zhoukaotest02.bean.adapter.Myadapter;
import test.bwei.com.zhoukaotest02.view.XListView;

/**
 * Created by MK on 2017/9/8.
 */
public class WeixinFragment01 extends Fragment implements XListView.IXListViewListener {

    private int num;
    private XListView xListView;
    private List<JavaBean.ResultBean.ListBean> list = new ArrayList<>();
    private Myadapter myadapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weixin_item, container, false);
        xListView = (XListView) view.findViewById(R.id.x_list_view);
        xListView.setPullRefreshEnable(true);
        xListView.setPullLoadEnable(true);
        xListView.setXListViewListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        String key = bundle.getString("key", "1");
        num = Integer.parseInt(key);

        getData();


    }

    private void getData() {
        AsyncTask<Void, Void, String> asyncTask = new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                try {
                    URL url = new URL("http://v.juhe.cn/weixin/query");
                    HttpURLConnection  connection = (HttpURLConnection) url.openConnection();

                    connection.setRequestMethod("POST");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);

                    //设置向服务器输出
                    connection.setDoOutput(true);
                    connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");

                    String param ="key=19178372f8a7c069c42cd59df8d84a46&ps=10&pno="+num;

                    connection.getOutputStream().write(param.getBytes());

                    int responseCode = connection.getResponseCode();

                    if (responseCode==200){
                        InputStream inputStream = connection.getInputStream();

                        String string = streamToString(inputStream, "utf-8");

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

//                Log.i("---",s);
                Gson gson = new Gson();
                JavaBean javaBean = gson.fromJson(s, JavaBean.class);


                list.addAll(0,javaBean.getResult().getList());

                //设置适配器
                setAdapter();
                xListView.stopRefresh();
                Date date = new Date(System.currentTimeMillis());
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
                xListView.setRefreshTime(dateFormat.format(date));
            }
        };
        asyncTask.execute();

    }

    private void setAdapter() {
        if (myadapter==null){
            myadapter = new Myadapter(getActivity(),list);
            xListView.setAdapter(myadapter);
        }else {
            myadapter.notifyDataSetChanged();;
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

        num++;
        getData();//加载后停止

    }

    @Override
    public void onLoadMore() {
        num++;
        getData();

    }
}
