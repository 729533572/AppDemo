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
import com.cqm.appdemo.listener.MyItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenqm on 2016/12/12.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {


    private List<NewsBean> mDatas = new ArrayList<NewsBean>();
    private Context mContext;
    private LayoutInflater inflater;
    private MyItemClickListener mItemClickListener;

    public NewsAdapter(Context context) {
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
    }

    public void setDatas(List<NewsBean> datas) {
        if (this.mDatas != null) {
            this.mDatas.clear();
        }
        this.mDatas = datas;
    }

    /**
     * 设置Item点击监听
     *
     * @param listener
     */
    public void setOnItemClickListener(MyItemClickListener listener) {
        this.mItemClickListener = listener;
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
        holder.date.setText(bean.getAuthor_name() + "   " + bean.getDate());
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (Integer) v.getTag();
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(v, position);
                }
            }
        });

    }

    static class MyViewHolder extends ViewHolder {
        TextView title;
        ImageView img;
        TextView date;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.news_title);
            img = (ImageView) view.findViewById(R.id.news_img);
            date = (TextView) view.findViewById(R.id.news_date);
        }
    }
}
