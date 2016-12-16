package com.cqm.appdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cqm.appdemo.R;
import com.cqm.appdemo.base.BaseFragment;

/**
 * Created by chenqm on 2016/12/15.
 */

public class UserFragment extends BaseFragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContentView = inflater.inflate(R.layout.fragment_user, container, false);
        return mContentView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
