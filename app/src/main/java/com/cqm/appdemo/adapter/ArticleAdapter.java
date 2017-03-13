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
import com.cqm.appdemo.bean.GankBean;
import com.cqm.appdemo.listener.MyItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenqm on 2016/12/12.
 */

public class ArticleAdapter extends RecyclerView.Adapter<ViewHolder> {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private List<GankBean> mDatas = new ArrayList<GankBean>();
    private Context mContext;
    private LayoutInflater inflater;
    private MyItemClickListener mItemClickListener;
    private boolean isLoadAll = false;

    public ArticleAdapter(Context context, List<GankBean> datas) {
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
            View view = inflater.inflate(R.layout.article_item, parent,
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
            GankBean bean = mDatas.get(position);
            ((ItemViewHolder) holder).type_text.setText(bean.getType());
            ((ItemViewHolder) holder).author_text.setText("作者：" + bean.getWho());
            String time = bean.getPublishedAt().substring(0, 10);
            ((ItemViewHolder) holder).time_text.setText(time);
            if ((bean.getImages() != null && bean.getImages().size() > 0) || bean.getType().equals("福利")) {
                ((ItemViewHolder) holder).art_img.setVisibility(View.VISIBLE);
                String imgUrl = "";
                if (bean.getType().equals("福利")) {
                    imgUrl = bean.getUrl();
                } else {
                    imgUrl = bean.getImages().get(0);
                }
                Glide.with(mContext).load(imgUrl).into(((ItemViewHolder) holder).art_img);
            } else {
                ((ItemViewHolder) holder).art_img.setVisibility(View.GONE);
            }
            String desc = bean.getDesc();
            if (bean.getType().equals("福利")) {
                desc = "福利来了，休息一下，瞧瞧美眉再继续撸！";
            }
            ((ItemViewHolder) holder).art_desc.setText(desc);
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
    }

    static class FooterViewHolder extends ViewHolder {
        public FooterViewHolder(View view) {
            super(view);
        }
    }

    static class ItemViewHolder extends ViewHolder {
        TextView type_text;
        TextView author_text;
        TextView time_text;
        ImageView art_img;
        TextView art_desc;

        public ItemViewHolder(View view) {
            super(view);
            type_text = (TextView) view.findViewById(R.id.type_text);
            author_text = (TextView) view.findViewById(R.id.author_text);
            time_text = (TextView) view.findViewById(R.id.time_text);

            art_img = (ImageView) view.findViewById(R.id.art_img);
            art_desc = (TextView) view.findViewById(R.id.art_desc);
        }
    }
}
