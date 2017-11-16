package test.bwei.com.zhoukaotest02.bean.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

import java.util.List;

import test.bwei.com.zhoukaotest02.R;
import test.bwei.com.zhoukaotest02.bean.JavaBean;

/**
 * Created by MK on 2017/9/9.
 */
public class Myadapter extends BaseAdapter{
    Context context;
    private int left = 0;
    private int right = 1;
    List<JavaBean.ResultBean.ListBean> list;
    public Myadapter(Context context, List<JavaBean.ResultBean.ListBean> list) {
        this.list=list;
        this.context=context;
    }
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
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position%2==0){
            return left;
        }
        return right;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (getItemViewType(position)==left){
            ViewHolder01 holder;
            if (convertView==null){
                convertView = View.inflate(context, R.layout.item01,null);
                holder = new ViewHolder01();
                holder.text = (TextView) convertView.findViewById(R.id.text01);
                holder.image = (ImageView) convertView.findViewById(R.id.image01);
                convertView.setTag(holder);
            }else {
                holder = (ViewHolder01) convertView.getTag();
            }
            holder.text.setText(list.get(position).getTitle());

           //ImageLoader.getInstance().displayImage(list.get(position).getFirstImg(),holder.image);
            ImageLoader.getInstance().displayImage(list.get(position).getFirstImg(),holder.image,getOption());
        }else if (getItemViewType(position)==right){
            ViewHolder02 holder;
            if (convertView==null){
                convertView = View.inflate(context, R.layout.item02,null);
                holder = new ViewHolder02();
                holder.text = (TextView) convertView.findViewById(R.id.text01);
                holder.image = (ImageView) convertView.findViewById(R.id.image01);
                convertView.setTag(holder);
            }else {
                holder = (ViewHolder02) convertView.getTag();
            }
            holder.text.setText(list.get(position).getTitle());
            //ImageLoader.getInstance().displayImage(list.get(position).getFirstImg(),holder.image);
            ImageLoader.getInstance().displayImage(list.get(position).getFirstImg(),holder.image,getOption());
        }

        return convertView;
    }

    private DisplayImageOptions getOption() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher) // 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.mipmap.ic_launcher) // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.mipmap.ic_launcher) // 设置图片加载或解码过程中发生错误显示的图片
                .resetViewBeforeLoading(false)  // default 设置图片在加载前是否重置、复位
                .delayBeforeLoading(1000)  // 下载前的延迟时间
                .cacheInMemory(false) // default  设置下载的图片是否缓存在内存中
                .cacheOnDisk(false) // default  设置下载的图片是否缓存在SD卡中

                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) // default 设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.ARGB_8888) // default 设置图片的解码类型

            .displayer(new SimpleBitmapDisplayer()) // default  还可以设置圆角图片new RoundedBitmapDisplayer(20)

                .build();
        return options;
       /* DisplayImageOptions imageOptions = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .showImageOnLoading(R.mipmap.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
                .resetViewBeforeLoading(true)//在加载之前复位一下显示
                .bitmapConfig(Bitmap.Config.RGB_565)//图片的质量
                .considerExifParams(true)///是否考虑JPEG图像EXIF参数（旋转，翻转）
                .build();*/
        //return imageOptions;
    }



    class ViewHolder01{
        TextView text;
        ImageView image;
    }
    class ViewHolder02{
        TextView text;
        ImageView image;
    }

}
