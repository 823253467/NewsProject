package com.example.zhanghaoqing20170901.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhanghaoqing20170901.MainActivity;
import com.example.zhanghaoqing20170901.R;
import com.example.zhanghaoqing20170901.bean.JavaBean;
import com.example.zhanghaoqing20170901.util.ImageUtil;

import java.util.List;

/**
 * Created by MK on 2017/8/31.
 */
public class MyAdapter extends BaseAdapter{
    Context context;
    List<JavaBean.ResultBean.DataBean> list;
    public MyAdapter(Context context, List<JavaBean.ResultBean.DataBean> list) {
        this.context=context;
        this.list=list;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            convertView = View.inflate(context, R.layout.item_list,null);
            holder = new ViewHolder();
            holder.text01 = (TextView) convertView.findViewById(R.id.text01);
            holder.text02 = (TextView) convertView.findViewById(R.id.text02);
            holder.text03 = (TextView) convertView.findViewById(R.id.text03);
            holder.text04 = (TextView) convertView.findViewById(R.id.text04);
            holder.image01 = (ImageView) convertView.findViewById(R.id.image01);
            holder.image02 = (ImageView) convertView.findViewById(R.id.image02);
            holder.image03 = (ImageView) convertView.findViewById(R.id.image03);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.text01.setText(list.get(position).getTitle());
        holder.text02.setText(list.get(position).getCategory());
        holder.text03.setText(list.get(position).getAuthor_name());
        holder.text04.setText(list.get(position).getDate());

        new ImageUtil().getImage(list.get(position).getThumbnail_pic_s(),holder.image01);
        new ImageUtil().getImage(list.get(position).getThumbnail_pic_s02(),holder.image02);
        new ImageUtil().getImage(list.get(position).getThumbnail_pic_s03(),holder.image03);
        return convertView;
    }
    class ViewHolder{
        TextView text01;
        TextView text02;
        TextView text03;
        TextView text04;
        ImageView image01;
        ImageView image02;
        ImageView image03;

    }
}
