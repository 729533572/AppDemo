package com.cqm.appdemo.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cqm.appdemo.R;
import com.cqm.appdemo.activity.WebViewActivity;
import com.cqm.appdemo.adapter.ArticleAdapter;
import com.cqm.appdemo.base.BaseFragment;
import com.cqm.appdemo.bean.GankBean;
import com.cqm.appdemo.bean.GankResult;
import com.cqm.appdemo.http.ApiService;
import com.cqm.appdemo.http.RetrofitUtil;
import com.cqm.appdemo.listener.MyItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by chenqm on 2016/12/15.
 */

public class ArticleFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.refreshlayout)
    SwipeRefreshLayout refreshlayout;
    private ArticleAdapter adapter;
    private List<GankBean> listDatas = new ArrayList<>();
    private String url = "http://gank.io/api/data/all/20/";
    private int page = 1;
    private boolean isLoadingMore = false;
    private boolean isRefreshing = false;
    private boolean isloadAll = false;
    private LinearLayoutManager mLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContentView = inflater.inflate(R.layout.fragment_article, container, false);
        ButterKnife.bind(this, mContentView);
        initView();
        return mContentView;
    }

    private void initView() {
        setCentetTitle("微闻资讯");
        mLayoutManager = new LinearLayoutManager(mContext);
        recyclerview.setLayoutManager(mLayoutManager);
        refreshlayout.setColorSchemeResources(android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        refreshlayout.setOnRefreshListener(this);
        adapter = new ArticleAdapter(mContext, listDatas);
        recyclerview.setAdapter(adapter);
        adapter.setOnItemClickListener(new MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                GankBean bean = listDatas.get(position);
                if (bean.getType().equals("休息视频")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);    //为Intent设置Action属性
                    intent.setData(Uri.parse(bean.getUrl())); //为Intent设置DATA属性
                    startActivity(intent);
                } else if (!bean.getType().equals("福利")) {
                    Intent intent = new Intent(mContext, WebViewActivity.class);
                    intent.putExtra("webUrl", bean.getUrl());
                    intent.putExtra("webTitle", bean.getType());
                    intent.putExtra("desc", bean.getDesc());
                    startActivity(intent);
                }
            }
        });
        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView,
                                             int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
                int totalItemCount = mLayoutManager.getItemCount();
                if (lastVisibleItem >= totalItemCount - 4 && dy > 0) {
                    if (!isLoadingMore) {
                        if (isloadAll) {
                            Toast.makeText(mContext, "已加载全部", Toast.LENGTH_LONG).show();
                        } else {
                            page++;
                            isLoadingMore = true;
                            loadData();
                        }
                    }
                }
            }
        });
        loadData();
    }

    @Override
    public void onRefresh() {
        if (!isRefreshing) {
            isRefreshing = true;
            isloadAll = false;
            adapter.setLoadAll(false);
            listDatas.clear();
            page = 1;
            loadData();
        }
    }

    private void loadData() {
        String mUrl = url + page;
        ApiService service = RetrofitUtil.create(ApiService.class);
        Call<GankResult> call = service.getGankData(mUrl);
        call.enqueue(new Callback<GankResult>() {
            @Override
            public void onResponse(Call<GankResult> call, Response<GankResult> response) {
                isRefreshing = false;
                isLoadingMore = false;
                refreshlayout.setRefreshing(false);
                GankResult result = response.body();
                List<GankBean> datas = result.getResults();
                if (datas == null || datas.size() == 0) {
                    isloadAll = true;
                    adapter.setLoadAll(true);
                } else {
                    listDatas.addAll(datas);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<GankResult> call, Throwable t) {
                refreshlayout.setRefreshing(false);
                isRefreshing = false;
                isLoadingMore = false;
                Toast.makeText(mContext, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
