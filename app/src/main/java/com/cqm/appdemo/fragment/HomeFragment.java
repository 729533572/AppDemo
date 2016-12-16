package com.cqm.appdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cqm.appdemo.R;
import com.cqm.appdemo.adapter.NewsAdapter;
import com.cqm.appdemo.base.BaseFragment;
import com.cqm.appdemo.bean.NewsBean;
import com.cqm.appdemo.bean.NewsResult;
import com.cqm.appdemo.http.ApiService;
import com.cqm.appdemo.http.RetrofitUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by chenqm on 2016/12/15.
 */

public class HomeFragment extends BaseFragment {


    @BindView(R.id.left_btn)
    ImageView leftBtn;
    @BindView(R.id.centet_title)
    TextView centetTitle;
    @BindView(R.id.right_btn)
    ImageView rightBtn;
    @BindView(R.id.home_recyclerview)
    RecyclerView homeRecyclerview;

    private NewsAdapter mAdapter;
    private List<NewsBean> mDatas;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContentView = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, mContentView);
        initView();
        return mContentView;
    }

    private void initView(){
        //        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        homeRecyclerview.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,
//                DividerItemDecoration.VERTICAL));
        mAdapter = new NewsAdapter(mContext);
        homeRecyclerview.setAdapter(mAdapter);

        loadData();
    }

    private void loadData() {
        ApiService mService = RetrofitUtil.create(ApiService.class);
        Call<NewsResult> call = mService.getNewsData("115daac717fe4295d16734595c2a86e3", "top");
        call.enqueue(new Callback<NewsResult>() {
                         @Override
                         public void onResponse(Call<NewsResult> call, Response<NewsResult> response) {
                             mDatas = response.body().getResult().getData();
                             mAdapter.setDatas(mDatas);
                             mAdapter.notifyDataSetChanged();
                         }

                         @Override
                         public void onFailure(Call<NewsResult> call, Throwable t) {
                             System.out.println(t.toString());
                             Toast.makeText(mContext, t.toString(), Toast.LENGTH_LONG).show();
                         }
                     }
        );
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
