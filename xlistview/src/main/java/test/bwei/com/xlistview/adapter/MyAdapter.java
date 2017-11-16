package test.bwei.com.xlistview.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import test.bwei.com.xlistview.MainActivity;
import test.bwei.com.xlistview.R;
import test.bwei.com.xlistview.bean.JavaBean;

/**
 * Created by MK on 2017/9/10.
 */
public class MyAdapter extends BaseAdapter{
    Context context;
    private int left=0;
    private int right=1;
    List<JavaBean.ResultBean.ListBean> list;
    public MyAdapter(Context context, List<JavaBean.ResultBean.ListBean> list) {
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
                convertView = View.inflate(context, R.layout.tem01,null);
                holder = new ViewHolder01();

                holder.text = (TextView) convertView.findViewById(R.id.text01);
                holder.image = (ImageView) convertView.findViewById(R.id.image01);
                convertView.setTag(holder);
            }else {
                holder = (ViewHolder01) convertView.getTag();
            }
            holder.text.setText(list.get(position).getTitle());

            ImageLoader.getInstance().displayImage(list.get(position).getFirstImg(),holder.image);
        }else if (getItemViewType(position)==right){

            ViewHolder02 holder;
            if (convertView==null){
                convertView = View.inflate(context, R.layout.tem02,null);
                holder = new ViewHolder02();

                holder.text = (TextView) convertView.findViewById(R.id.text01);
                holder.image = (ImageView) convertView.findViewById(R.id.image01);
                convertView.setTag(holder);
            }else {
                holder = (ViewHolder02) convertView.getTag();
            }
            holder.text.setText(list.get(position).getTitle());
            ImageLoader.getInstance().displayImage(list.get(position).getFirstImg(),holder.image);
        }
        return convertView;
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
