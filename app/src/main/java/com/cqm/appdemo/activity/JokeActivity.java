package com.cqm.appdemo.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.cqm.appdemo.R;
import com.cqm.appdemo.adapter.JokeAdapter;
import com.cqm.appdemo.base.BaseActivity;
import com.cqm.appdemo.bean.JokeBean;
import com.cqm.appdemo.bean.JokeData;
import com.cqm.appdemo.bean.JokeResult;
import com.cqm.appdemo.http.ApiService;
import com.cqm.appdemo.http.RetrofitUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by chenqm on 2017/1/4.
 */

public class JokeActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.refreshlayout)
    SwipeRefreshLayout refreshlayout;
    private int page = 1;
    private static final String key = "49b6ab010a3d585a4aa780bad7d3bde3";
    private static final String sort = "asc";
    private static final String time = "1418816972";
    private static final String url = "http://japi.juhe.cn/joke/content/list.from";

    private JokeAdapter adapter;
    private LinearLayoutManager mLayoutManager;
    private List<JokeBean> listDatas = new ArrayList<>();
    private boolean isLoadingMore = false;
    private boolean isRefreshing = false;
    private boolean isloadAll = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        ButterKnife.bind(this);
        setCentetTitle("笑话锦集");
        setBack();
        mLayoutManager = new LinearLayoutManager(mContext);
        recyclerview.setLayoutManager(mLayoutManager);
        refreshlayout.setColorSchemeResources(android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        refreshlayout.setOnRefreshListener(this);
        adapter = new JokeAdapter(mContext, listDatas);
        recyclerview.setAdapter(adapter);
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
        Map<String, String> map = new HashMap<>();
        map.put("key", key);
        map.put("page", String.valueOf(page));
        map.put("pagesize", "20");
        map.put("sort", sort);
        map.put("time", time);
        ApiService service = RetrofitUtil.create(ApiService.class);

        Call<JokeResult> call = service.getJokeData(url, map);
        call.enqueue(new Callback<JokeResult>() {
            @Override
            public void onResponse(Call<JokeResult> call, Response<JokeResult> response) {
                isRefreshing = false;
                isLoadingMore = false;
                refreshlayout.setRefreshing(false);
                JokeResult result = response.body();
                String code = result.getError_code();
                if (code.equals("0")) {
                    JokeData data = result.getResult();
                    List<JokeBean> list = data.getData();
                    if (list != null && list.size() > 0) {
                        listDatas.addAll(list);
                        adapter.notifyDataSetChanged();
                    } else {
                        isloadAll = true;
                        adapter.setLoadAll(true);
                    }

                } else {
                    Toast.makeText(mContext, result.getReason(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<JokeResult> call, Throwable t) {
                isRefreshing = false;
                isLoadingMore = false;
                refreshlayout.setRefreshing(false);
                Toast.makeText(mContext, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

}
