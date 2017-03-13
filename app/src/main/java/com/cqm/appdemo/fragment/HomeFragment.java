package com.cqm.appdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cqm.appdemo.R;
import com.cqm.appdemo.adapter.HomePagerAdapter;
import com.cqm.appdemo.base.BaseFragment;
import com.cqm.appdemo.data.NewsModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chenqm on 2016/12/15.
 */

public class HomeFragment extends BaseFragment {


    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    //类型,shehui(社会),guonei(国内),guoji(国际),yule(娱乐),
    // tiyu(体育)junshi(军事),keji(科技),caijing(财经),shishang(时尚)
    private HomePagerAdapter mPagerAdapter;
    private List<NewsModel> newsModelList = new ArrayList<>();
    private List<NewsFragment> fragmentList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContentView = inflater.inflate(R.layout.fragment_home, container, false);

        ButterKnife.bind(this, mContentView);
        initData();
        return mContentView;
    }

    private void initData() {
        newsModelList.clear();
        newsModelList.add(new NewsModel("shehui", "社会"));
        newsModelList.add(new NewsModel("guonei", "国内"));
        newsModelList.add(new NewsModel("guoji", "国际"));
        newsModelList.add(new NewsModel("yule", "娱乐"));
        newsModelList.add(new NewsModel("tiyu", "体育"));
        newsModelList.add(new NewsModel("keji", "军事"));
//        newsModelList.add(new NewsModel("caijing", "财经"));
        newsModelList.add(new NewsModel("shishang", "时尚"));
        fragmentList.clear();
        for (NewsModel newsModel : newsModelList) {
            NewsFragment newsFragment = new NewsFragment();
            Bundle bundle = new Bundle();
            bundle.putString("KeyType", newsModel.getKeyType());
            bundle.putString("title", newsModel.getTitle());
            newsFragment.setArguments(bundle);
            fragmentList.add(newsFragment);
        }
        mPagerAdapter = new HomePagerAdapter(getChildFragmentManager(), fragmentList, newsModelList);
        viewPager.setAdapter(mPagerAdapter);
        tabs.setupWithViewPager(viewPager);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
