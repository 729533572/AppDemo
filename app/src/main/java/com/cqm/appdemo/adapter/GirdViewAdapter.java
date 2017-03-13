package com.cqm.appdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cqm.appdemo.R;
import com.cqm.appdemo.data.ConsData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenqm on 2017/1/6.
 */

public class GirdViewAdapter extends BaseAdapter {

    //上下文对象
    private Context context;
    private List<ConsData> datas = new ArrayList<>();

    public GirdViewAdapter(Context context, List<ConsData> datas) {
        this.context = context;
        this.datas = datas;
    }

    public int getCount() {
        return datas.size();
    }

    public Object getItem(int item) {
        return item;
    }

    public long getItemId(int id) {
        return id;
    }

    //创建View方法
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.girdview_item, parent, false);
            holder = new ViewHolder();
            holder.consImg = (ImageView) convertView.findViewById(R.id.cons_img);
            holder.consName = (TextView) convertView.findViewById(R.id.cons_name);
            holder.consDate = (TextView) convertView.findViewById(R.id.cons_date);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ConsData item = datas.get(position);
        holder.consImg.setImageResource(item.getIconId());
        holder.consName.setText(item.getName());
        holder.consDate.setText(item.getDate());
        return convertView;
    }

    static class ViewHolder {
        public ImageView consImg;
        public TextView consName;
        public TextView consDate;

    }
}
