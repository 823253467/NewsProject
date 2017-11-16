package com.bwei.zhanghaoqing20170912.fragment.adpter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bwei.zhanghaoqing20170912.R;
import com.bwei.zhanghaoqing20170912.bean.DataDataBean;

import java.util.List;

/**
 * Created by MK on 2017/9/12.
 */
public class MyAdapter extends BaseAdapter{
    Context context;
    List<DataDataBean.ResultBean.DataBean> list;
    public MyAdapter(Context context, List<DataDataBean.ResultBean.DataBean> list) {
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

            view = View.inflate(context, R.layout.text_item,null);

            holder = new ViewHolder();
            holder.text = (TextView) view.findViewById(R.id.text01);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        holder.text.setText(list.get(i).getTitle());
        return view;
    }
    class ViewHolder{
        TextView text;
    }
}
