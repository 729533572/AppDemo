package com.cqm.appdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cqm.appdemo.R;
import com.cqm.appdemo.bean.NewsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenqm on 2016/12/12.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {


    private List<NewsBean> mDatas = new ArrayList<NewsBean>();
    private Context mContext;
    private LayoutInflater inflater;

    public NewsAdapter(Context context) {
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
    }

    public void setDatas(List<NewsBean> datas) {
        this.mDatas = datas;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(inflater.inflate(R.layout.news_item, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NewsBean bean = mDatas.get(position);
        holder.title.setText(bean.getTitle());
        Glide.with(mContext).load(bean.getThumbnail_pic_s()).into(holder.img);
        holder.date.setText(bean.getAuthor_name()+"   "+bean.getDate());

    }

    class MyViewHolder extends ViewHolder {
        TextView title;
        ImageView img;
        TextView date;
        public MyViewHolder(View view) {
            super(view);
            title = (TextView)view.findViewById(R.id.news_title);
            img = (ImageView)view.findViewById(R.id.news_img);
            date = (TextView)view.findViewById(R.id.news_date);
        }
    }
}
