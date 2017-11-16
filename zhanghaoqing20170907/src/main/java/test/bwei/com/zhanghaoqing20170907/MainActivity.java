package test.bwei.com.zhanghaoqing20170907;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private List<JavaBean.NewslistBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listview01);
        AsyncTask<Void, Void, String> asyncTask = new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                try {
                    URL url = new URL("https://api.tianapi.com/wxnew/?key=8d6e3228d25298f13af4fc40ce6c9679&num=10");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setReadTimeout(5000);
                    connection.setConnectTimeout(5000);
                    int responseCode = connection.getResponseCode();
                    if (responseCode==200){
                        InputStream inputStream = connection.getInputStream();

                        String json = as(inputStream,"utf-8");

                        return json;

                    }

                } catch (IOException e) {
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

//                Log.i("----",list.get(1).getTitle());
                listView.setAdapter(new BaseAdapter() {
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
                        ViewHolder viewHolder;
                        if (convertView==null){

                            convertView = View.inflate(MainActivity.this,R.layout.item,null);

                            viewHolder = new ViewHolder();

                            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.image01);
                            viewHolder.textView01 = (TextView) convertView.findViewById(R.id.text01);
                            viewHolder.textView02 = (TextView) convertView.findViewById(R.id.text02);
                            convertView.setTag(viewHolder);
                        }else {
                            viewHolder = (ViewHolder) convertView.getTag();
                        }
                        viewHolder.textView01.setText(list.get(position).getTitle());
                        viewHolder.textView02.setText(list.get(position).getCtime());
                        ImageLoader.getInstance().displayImage(list.get(position).getPicUrl(),viewHolder.imageView);
                        return convertView;
                    }
                    class ViewHolder{
                        ImageView imageView;
                        TextView textView01;
                        TextView textView02;
                    }
                });


            }
        };
        asyncTask.execute();

    }
    private String as(InputStream inputStream, String s) {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream,s);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String string = null;

            while ((string=bufferedReader.readLine())!=null){
                stringBuilder.append(string);
            }

            bufferedReader.close();
            return stringBuilder.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return s;
    }

}
