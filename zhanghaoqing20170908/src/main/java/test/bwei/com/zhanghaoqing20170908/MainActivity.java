package test.bwei.com.zhanghaoqing20170908;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

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

import test.bwei.com.zhanghaoqing20170908.bean.imageUtil;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private int one = 0;
    private int two = 1;
    private int three = 2;
    private int frour = 3;
    private List<JavaBean.ResultBean.DataBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listview01);

        ImageLoaderConfiguration aDefault = ImageLoaderConfiguration.createDefault(MainActivity.this);

        ImageLoader.getInstance().init(aDefault);


        AsyncTask<Void, Void, String> asyncTask = new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                try {
                    URL url = new URL("http://v.juhe.cn/toutiao/index?type=top&key=2f092bd9ce76c0257052d6d3c93c11b4");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setReadTimeout(5000);
                    connection.setConnectTimeout(5000);
                    int responseCode = connection.getResponseCode();
                    if (responseCode==200){

                        InputStream inputStream = connection.getInputStream();
                        String json = stringStream(inputStream,"utf-8");

                        return json;
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
                list = javaBean.getResult().getData();
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


                       if (list.get(position).getThumbnail_pic_s02().startsWith("http")&&list.get(position).getThumbnail_pic_s().startsWith("http")&&list.get(position).getThumbnail_pic_s02().startsWith("http")) {
                            return frour;
                        } else if (list.get(position).getThumbnail_pic_s02().startsWith("http")&&list.get(position).getThumbnail_pic_s().startsWith("http")) {
                            return three;
                        }else if (list.get(position).getThumbnail_pic_s().startsWith("http")){
                            return two;
                        }


                        return one;
                    }

                    @Override
                    public View getView(final int position, View convertView, ViewGroup parent) {
                        if (one==getItemViewType(position)){

                            ViewHolder01 viewHolder01;
                            if (convertView==null){
                                convertView =View.inflate(MainActivity.this,R.layout.item01,null);
                                viewHolder01 = new ViewHolder01();
                                viewHolder01.text01 = (TextView) convertView.findViewById(R.id.text01);
                                convertView.setTag(viewHolder01);
                            }else {
                                viewHolder01 = (ViewHolder01) convertView.getTag();


                            }
                            viewHolder01.text01.setText(list.get(position).getTitle());
                        }else if (two==getItemViewType(position)){

                            ViewHolder02 viewHolder01;
                            if (convertView==null){
                                convertView =View.inflate(MainActivity.this,R.layout.item02,null);
                                viewHolder01 = new ViewHolder02();
                                viewHolder01.text01 = (TextView) convertView.findViewById(R.id.text01);
                                viewHolder01.image01= (ImageView) convertView.findViewById(R.id.image01);

                                convertView.setTag(viewHolder01);
                            }else {
                                viewHolder01 = (ViewHolder02) convertView.getTag();
                            }

                            viewHolder01.text01.setText(list.get(position).getTitle());

                            ImageLoader.getInstance().displayImage(list.get(position).getThumbnail_pic_s(),viewHolder01.image01,getPost());
                            //图片错位
                            /*viewHolder01.image01.setImageResource(R.mipmap.ic_launcher);
                            viewHolder01.text01.setText(list.get(position).getTitle());
                            if (list.get(position).getThumbnail_pic_s()!=null){

                                viewHolder01.image01.setTag(list.get(position).getThumbnail_pic_s());


                                new imageUtil(list.get(position).getThumbnail_pic_s(),viewHolder01.image01, new ImageCuWei() {
                                    @Override
                                    public void Imageback(ImageView imageView, Bitmap bitmap) {

                                        if (imageView.getTag().equals(list.get(position).getThumbnail_pic_s())){

                                            imageView.setImageBitmap(bitmap);
                                        }
                                    }
                                });

                            }*/
                        }else if (three==getItemViewType(position)){

                            ViewHolder03 viewHolder01;
                            if (convertView==null){
                                convertView =View.inflate(MainActivity.this,R.layout.item03,null);
                                viewHolder01 = new ViewHolder03();
                                viewHolder01.text01 = (TextView) convertView.findViewById(R.id.text01);
                                viewHolder01.image01= (ImageView) convertView.findViewById(R.id.image01);
                                viewHolder01.image02= (ImageView) convertView.findViewById(R.id.image02);
                                convertView.setTag(viewHolder01);
                            }else {
                                viewHolder01 = (ViewHolder03) convertView.getTag();
                            }
                            viewHolder01.text01.setText(list.get(position).getTitle());
                            ImageLoader.getInstance().displayImage(list.get(position).getThumbnail_pic_s(),viewHolder01.image01,getPost());
                            ImageLoader.getInstance().displayImage(list.get(position).getThumbnail_pic_s02(),viewHolder01.image02,getPost());
                        }else if (frour==getItemViewType(position)){

                            ViewHolder04 viewHolder01;
                            if (convertView==null){
                                convertView =View.inflate(MainActivity.this,R.layout.item04,null);
                                viewHolder01 = new ViewHolder04();
                                viewHolder01.text01 = (TextView) convertView.findViewById(R.id.text01);

                                viewHolder01.image01= (ImageView) convertView.findViewById(R.id.image01);
                                viewHolder01.image02= (ImageView) convertView.findViewById(R.id.image02);
                                viewHolder01.image03= (ImageView) convertView.findViewById(R.id.image03);
                                convertView.setTag(viewHolder01);
                            }else {
                                viewHolder01 = (ViewHolder04) convertView.getTag();
                            }
                            viewHolder01.text01.setText(list.get(position).getTitle());
                            ImageLoader.getInstance().displayImage(list.get(position).getThumbnail_pic_s(),viewHolder01.image01,getPost());
                            ImageLoader.getInstance().displayImage(list.get(position).getThumbnail_pic_s02(),viewHolder01.image02,getPost());
                            ImageLoader.getInstance().displayImage(list.get(position).getThumbnail_pic_s03(),viewHolder01.image03,getPost());
                        }



                        return convertView;
                    }
                    class ViewHolder01{
                        TextView text01;
                    }
                    class ViewHolder02{
                        TextView text01;
                        ImageView image01;
                    }
                    class ViewHolder03{
                        TextView text01;
                        ImageView image01;
                        ImageView image02;
                    }
                    class ViewHolder04{
                        TextView text01;
                        ImageView image01;
                        ImageView image02;
                        ImageView image03;
                    }

                });
            }


        };
        asyncTask.execute();

    }

    private String stringStream(InputStream inputStream, String s) {

        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream,s);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            //容器
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
        return null;
    }
    private DisplayImageOptions getPost() {


        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher) // 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.mipmap.ic_launcher) // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.mipmap.ic_launcher) // 设置图片加载或解码过程中发生错误显示的图片
                .resetViewBeforeLoading(false)  // default 设置图片在加载前是否重置、复位
                .delayBeforeLoading(1000)  // 下载前的延迟时间
                .cacheInMemory(false) // default  设置下载的图片是否缓存在内存中
                .cacheOnDisk(false) // default  设置下载的图片是否缓存在SD卡中

        .considerExifParams(false) // default
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) // default 设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.ARGB_8888) // default 设置图片的解码类型

        .displayer(new SimpleBitmapDisplayer()) // default  还可以设置圆角图片new RoundedBitmapDisplayer(20)

                .build();
        return options;
    }

}