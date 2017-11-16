package com.bwei.zhanghaoqing20170915.adpter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.zhanghaoqing20170915.MainActivity;
import com.bwei.zhanghaoqing20170915.R;
import com.bwei.zhanghaoqing20170915.bean.JavaBean;
import com.bwei.zhanghaoqing20170915.util.ImageLaoderUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by MK on 2017/9/15.
 */
public class ListAdapter extends BaseAdapter{
    Context context;
    List<JavaBean.NewslistBean> list;
    public ListAdapter(Context context, List<JavaBean.NewslistBean> list) {
        this.context=context;
        this.list=list;
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
        if (view==null){

            view = View.inflate(context, R.layout.item,null);
            holder = new ViewHolder();
            holder.image01 = (ImageView) view.findViewById(R.id.image01);
            holder.text01 = (TextView) view.findViewById(R.id.text01);

            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        holder.text01.setText(list.get(i).getTitle());
        ImageLoader.getInstance().displayImage(list.get(i).getPicUrl(),holder.image01, ImageLaoderUtil.getoption());
        return view;
    }
    class ViewHolder{
        ImageView image01;
        TextView text01;
    }

}
