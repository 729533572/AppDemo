package com.cqm.appdemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;

import com.cqm.appdemo.data.NewsModel;
import com.cqm.appdemo.fragment.NewsFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenqunming on 2017/3/6.
 */

public class HomePagerAdapter extends FragmentPagerAdapter {

    private List<NewsFragment> fragmentList = new ArrayList<>();
    private List<NewsModel> newsModelList;


    public HomePagerAdapter(FragmentManager fm, List<NewsFragment> fragmentList, List<NewsModel> newsModelList) {
        super(fm);
        setFragments(fm);
        this.fragmentList = fragmentList;
        this.newsModelList = newsModelList;

    }

    //刷新fragment
    public void setFragments(FragmentManager fm) {
        if (this.fragmentList != null) {
            FragmentTransaction ft = fm.beginTransaction();
            for (Fragment f : this.fragmentList) {
                ft.remove(f);
            }
            ft.commitAllowingStateLoss();
            ft = null;
            fm.executePendingTransactions();
        }
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return newsModelList.get(position).getTitle();
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

}
