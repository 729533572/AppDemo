package com.cqm.appdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cqm.appdemo.R;
import com.cqm.appdemo.adapter.BeautyAdapter;
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

public class BeautyFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.refreshlayout)
    SwipeRefreshLayout refreshlayout;
    private BeautyAdapter adapter;
    private List<GankBean> listDatas = new ArrayList<>();
    private String url = "http://gank.io/api/data/福利/20/";
    private int page = 1;
    private boolean isLoadingMore = false;
    private boolean isRefreshing = false;
    private boolean isloadAll = false;
    private StaggeredGridLayoutManager mLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContentView = inflater.inflate(R.layout.fragment_beauty, container, false);
        ButterKnife.bind(this, mContentView);
        initView();
        return mContentView;
    }

    private void initView() {
        setCentetTitle("倩美眉");
        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(mLayoutManager);
        refreshlayout.setColorSchemeResources(android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        refreshlayout.setOnRefreshListener(this);
        adapter = new BeautyAdapter(mContext, listDatas);
        recyclerview.setAdapter(adapter);
        adapter.setOnItemClickListener(new MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

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
                int[] visibleItems = mLayoutManager.findLastVisibleItemPositions(null);
                int lastVisibleItem = Math.max(visibleItems[0], visibleItems[1]);
                if (dy > 0 && lastVisibleItem > adapter.getItemCount() - 5 && !isLoadingMore) {
                    if (isloadAll) {
                        Toast.makeText(mContext, "已加载全部", Toast.LENGTH_LONG).show();
                    } else {
                        refreshlayout.setRefreshing(true);
                        page++;
                        isLoadingMore = true;
                        loadData();
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
