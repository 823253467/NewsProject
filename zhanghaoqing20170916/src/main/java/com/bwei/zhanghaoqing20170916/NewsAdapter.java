package com.bwei.zhanghaoqing20170916;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * @author Dash
 * @date 2017/9/16
 * @description:
 */
public class NewsAdapter extends BaseAdapter {
    Context context;
    List<DataDataBean.NewslistBean> list;

    public NewsAdapter(Context context, List<DataDataBean.NewslistBean> list) {
        this.context = context;
        this.list = list;
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
        ViewHolder holder;
        if (view == null){
            view = View.inflate(context,R.layout.item_layout,null);
            holder = new ViewHolder();

            holder.imageView = (ImageView) view.findViewById(R.id.image_view);
            holder.textView = (TextView) view.findViewById(R.id.text_01);

            view.setTag(holder);

        }else {
            holder = (ViewHolder) view.getTag();
        }

        holder.textView.setText(list.get(i).getTitle());

        //图片显示

        ImageLoader.getInstance().displayImage(list.get(i).getPicUrl(),holder.imageView,ImageLoaderUtil.getoption());

        return view;
    }

    private class ViewHolder{
        ImageView imageView;
        TextView textView;
    }
}