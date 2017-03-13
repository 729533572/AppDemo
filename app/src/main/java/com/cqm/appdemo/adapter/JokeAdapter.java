package com.cqm.appdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cqm.appdemo.R;
import com.cqm.appdemo.bean.JokeBean;
import com.cqm.appdemo.listener.MyItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenqm on 2016/12/12.
 */

public class JokeAdapter extends RecyclerView.Adapter<ViewHolder> {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private List<JokeBean> mDatas = new ArrayList<JokeBean>();
    private Context mContext;
    private LayoutInflater inflater;
    private MyItemClickListener mItemClickListener;
    private boolean isLoadAll = false;

    public JokeAdapter(Context context, List<JokeBean> datas) {
        this.mContext = context;
        this.mDatas = datas;
        inflater = LayoutInflater.from(mContext);
    }

    public void setLoadAll(boolean loadall) {
        isLoadAll = loadall;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (isLoadAll) {
            return TYPE_ITEM;
        }
        if (position == getItemCount() - 1) {
            return TYPE_FOOTER;
        }
        return TYPE_ITEM;
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
        if (isLoadAll) {
            return mDatas.size();
        }
        return mDatas.size() + 1;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = null;
        if (viewType == TYPE_ITEM) {
            View view = inflater.inflate(R.layout.joke_item, parent,
                    false);
            holder = new ItemViewHolder(view);
        } else {
            View view = inflater.inflate(R.layout.footer_view, parent,
                    false);
            holder = new FooterViewHolder(view);
        }
        return holder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            JokeBean bean = mDatas.get(position);
            ((ItemViewHolder) holder).content_text.setText(bean.getContent());
            ((ItemViewHolder) holder).time_text.setText(bean.getUpdatetime());
        }
    }

    static class FooterViewHolder extends ViewHolder {
        public FooterViewHolder(View view) {
            super(view);
        }
    }

    static class ItemViewHolder extends ViewHolder {
        TextView content_text;
        TextView time_text;

        public ItemViewHolder(View view) {
            super(view);
            content_text = (TextView) view.findViewById(R.id.content_text);
            time_text = (TextView) view.findViewById(R.id.time_text);
        }
    }
}
