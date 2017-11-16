package test.bwei.com.multi08;

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

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private int video = 0;
    private int two = 1;
    private int three = 2;
    private int normal = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listview01);

        AsyncTask<Void, Void, String> asyncTask = new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                try {
                    URL url = new URL("http://ic.snssdk.com/2/article/v25/stream/?count=20&min_behot_time=1504621638&bd_latitude=4.9E-324&bd_longitude=4.9E-324&bd_loc_time=1504622133&loc_mode=5&loc_time=1504564532&latitude=35.00125&longitude=113.56358166666665&city=%E7%84%A6%E4%BD%9C&lac=34197&cid=23201&iid=14534335953&device_id=38818211465&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=SM-E7000&os_api=19&os_version=4.4.2&uuid=357698010742401&openudid=74f06d2f9d8c9664");

                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
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

                Log.i("-----",s);
                Gson gson = new Gson();
                JavaBean json = gson.fromJson(s, JavaBean.class);
                final List<JavaBean.DataBean> list = json.getData();

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
                    public int getViewTypeCount() {
                        return 4;
                    }

                    @Override
                    public int getItemViewType(int position) {
                        if (list.get(position).isHas_video()){

                            return video;
                        }else if (list.get(position).isHas_image()){
                            if (list.get(position).getImage_list().size()>=3){

                                return three;
                            }else {
                                return two;
                            }
                        }
                        return normal;
                    }

                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        if (getItemViewType(position)==video){
                            VideoHolder holder;
                            if (convertView==null){

                                convertView = View.inflate(MainActivity.this,R.layout.item_video,null);

                                holder = new VideoHolder();

                                holder.text_title = (TextView) convertView.findViewById(R.id.text_title);

                                convertView.setTag(holder);
                            }else {
                                holder = (VideoHolder) convertView.getTag();
                            }
                            holder.text_title.setText(list.get(position).getTitle());


                        }else if (getItemViewType(position)==three){

                            ImageThreeHolder holder;
                            if (convertView==null){

                                convertView = View.inflate(MainActivity.this,R.layout.item_three_image,null);

                                holder = new ImageThreeHolder();

                                holder.text_title = (TextView) convertView.findViewById(R.id.text_title);

                                convertView.setTag(holder);
                            }else {
                                holder = (ImageThreeHolder) convertView.getTag();
                            }
                            holder.text_title.setText(list.get(position).getTitle());
                        }else if (getItemViewType(position)==two){

                            ImageOneHolder holder;
                            if (convertView==null){

                                convertView = View.inflate(MainActivity.this,R.layout.item_image_one,null);

                                holder = new ImageOneHolder();

                                holder.text_title = (TextView) convertView.findViewById(R.id.text_title);

                                convertView.setTag(holder);
                            }else {
                                holder = (ImageOneHolder) convertView.getTag();
                            }
                            holder.text_title.setText(list.get(position).getTitle());
                        }else {
                            NormalHolder holder;
                            if (convertView==null){

                                convertView = View.inflate(MainActivity.this,R.layout.item_normal,null);

                                holder = new NormalHolder();

                                holder.text_title = (TextView) convertView.findViewById(R.id.text_title);

                                convertView.setTag(holder);
                            }else {
                                holder = (NormalHolder) convertView.getTag();
                            }
                            holder.text_title.setText(list.get(position).getTitle());
                        }
                        return convertView;
                    }
                    class VideoHolder{
                        TextView text_title;
                        ImageView imageView;
                    }

                    class ImageThreeHolder{
                        TextView text_title;
                        ImageView image_01;
                        ImageView image_02;
                        ImageView image_03;
                    }

                    class ImageOneHolder{
                        TextView text_title;
                        ImageView imageView;
                    }

                    class NormalHolder{
                        TextView text_title;
                    }
                });


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
