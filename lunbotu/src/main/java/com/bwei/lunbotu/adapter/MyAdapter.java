package com.bwei.lunbotu.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bwei.lunbotu.R;
import com.bwei.lunbotu.bean.DataDataBean;

import java.util.List;

/**
 * Created by MK on 2017/9/13.
 */
public class MyAdapter extends BaseAdapter{
    private int shang=0;
    private int xia=1;
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
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position%2==0){
            return shang;
        }
        return xia;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (getItemViewType(i)==shang){

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
        }else if (getItemViewType(i)==xia){

            ViewHolder1 holder;
            if (view==null){

                view = View.inflate(context, R.layout.text2_item,null);

                holder = new ViewHolder1();
                holder.text = (TextView) view.findViewById(R.id.text01);
                holder.text1 = (TextView) view.findViewById(R.id.text02);
                view.setTag(holder);
            }else {
                holder = (ViewHolder1) view.getTag();
            }
            holder.text.setText(list.get(i).getTitle());
            holder.text1.setText(list.get(i).getTitle());
        }



        return view;
    }
    class ViewHolder{
        TextView text;
    }
    class ViewHolder1{
        TextView text;
        TextView text1;
    }
}
