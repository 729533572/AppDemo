package com.cqm.appdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.view.ViewGroup.LayoutParams;

import com.bumptech.glide.Glide;
import com.cqm.appdemo.R;
import com.cqm.appdemo.bean.GankBean;
import com.cqm.appdemo.listener.MyItemClickListener;
import com.cqm.appdemo.util.AndroidUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenqm on 2016/12/12.
 */

public class BeautyAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<GankBean> mDatas = new ArrayList<GankBean>();
    private Context mContext;
    private LayoutInflater inflater;
    private MyItemClickListener mItemClickListener;

    public BeautyAdapter(Context context, List<GankBean> datas) {
        this.mContext = context;
        this.mDatas = datas;
        inflater = LayoutInflater.from(mContext);
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
        return position;
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ItemViewHolder(inflater.inflate(R.layout.beauty_item, parent,
                false));
        return holder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GankBean bean = mDatas.get(position);
        Glide.with(mContext).load(bean.getUrl()).into(((ItemViewHolder) holder).img);
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = (Integer) v.getTag();
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(v, pos);
                }
            }
        });
    }


    static class ItemViewHolder extends ViewHolder {
        ImageView img;

        public ItemViewHolder(View view) {
            super(view);
            img = (ImageView) view.findViewById(R.id.beauty_img);
            LayoutParams params = img.getLayoutParams();
            int width = AndroidUtil.getScreenWidth(view.getContext());
            params.height = width / 2;
            params.width = width / 2;
            img.setLayoutParams(params);
        }
    }
}
