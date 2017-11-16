package com.bwei.zhanghaoqing20170921;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

/**
 * Created by MK on 2017/9/21.
 */
public class MyAdapter extends BaseAdapter{
    Context context;
    List<JavaBean.NewslistBean> list;

    public MyAdapter(Context context, List<JavaBean.NewslistBean> list) {
        this.context=context;
        this.list=list;
        ImageLoaderConfiguration aDefault = ImageLoaderConfiguration.createDefault(context);
        ImageLoader.getInstance().init(aDefault);

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHodler holder;
        if (view==null){
            view = View.inflate(context,R.layout.item,null);

            holder = new ViewHodler();

            holder.image01 = (ImageView) view.findViewById(R.id.image01);
            holder.text01 = (TextView) view.findViewById(R.id.text01);

            view.setTag(holder);

        }else {
            holder = (ViewHodler) view.getTag();
        }

        holder.text01.setText(list.get(i).getTitle());
        ImageLoader.getInstance().displayImage(list.get(i).getPicUrl(),holder.image01);
        return view;
    }
    class ViewHodler{
        TextView text01;
        ImageView image01;
    }
}
