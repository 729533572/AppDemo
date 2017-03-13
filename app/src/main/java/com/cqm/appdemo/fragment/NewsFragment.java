package com.cqm.appdemo.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cqm.appdemo.R;
import com.cqm.appdemo.activity.WebViewActivity;
import com.cqm.appdemo.adapter.NewsAdapter;
import com.cqm.appdemo.bean.NewsBean;
import com.cqm.appdemo.bean.NewsResult;
import com.cqm.appdemo.data.NewsData;
import com.cqm.appdemo.http.ApiService;
import com.cqm.appdemo.http.RetrofitUtil;
import com.cqm.appdemo.listener.MyItemClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by chenqunming on 2017/3/6.
 */

public class NewsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    @BindView(R.id.refreshlayout)
    SwipeRefreshLayout mRefreshlayout;
    private String keyType = "shehui";
    private Context mContext = null;
    private View mContentView = null;
    private NewsAdapter newsAdapter;

    private List<NewsBean> listDatas = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        if (mContentView == null) {
            mContentView = inflater.from(mContext).inflate(R.layout.fragment_news, container, false);
        }
        ButterKnife.bind(this, mContentView);
        initView();
        return mContentView;
    }

    public void initView() {
        if (listDatas != null) {
            listDatas.clear();
        }
        //        mRecyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerview.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        if (getArguments() != null) {
            keyType = getArguments().getString("KeyType");
        }
        mRefreshlayout.setColorSchemeResources(android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        mRefreshlayout.setOnRefreshListener(this);
        newsAdapter = new NewsAdapter(mContext);
        newsAdapter.setOnItemClickListener(new MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                NewsBean bean = listDatas.get(position);
                Intent intent = new Intent(mContext, WebViewActivity.class);
                intent.putExtra("webUrl", bean.getUrl());
                intent.putExtra("webTitle", bean.getAuthor_name());
                intent.putExtra("desc", bean.getTitle());
                mContext.startActivity(intent);
            }
        });
        mRecyclerview.setAdapter(newsAdapter);
        if (newsAdapter.getItemCount() <= 0) {
            initData();
        }
    }

    private void initData() {
        ApiService service = RetrofitUtil.create(ApiService.class);
        Call<NewsResult> call = service.getNewsData("115daac717fe4295d16734595c2a86e3", keyType);
        call.enqueue(new Callback<NewsResult>() {
            @Override
            public void onResponse(Call<NewsResult> call, Response<NewsResult> response) {
                mRefreshlayout.setRefreshing(false);

                NewsResult result = response.body();
//                Log.e("11111111", "keyType==" + keyType + "," + result.toString());
                String code = result.getError_code();
                if (code.equals("0")) {
                    NewsData datas = result.getResult();
                    listDatas = datas.getData();
                    newsAdapter.setDatas(listDatas);
                    newsAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(mContext, result.getReason(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<NewsResult> call, Throwable t) {
                mRefreshlayout.setRefreshing(false);
                Toast.makeText(mContext, t.toString(), Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public void onRefresh() {
        initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}
