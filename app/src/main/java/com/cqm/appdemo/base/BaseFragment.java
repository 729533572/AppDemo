package com.cqm.appdemo.base;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

/**
 * Created by chenqm on 2016/12/15.
 */

public class BaseFragment extends Fragment {

    protected Context mContext = null;
    protected View mContentView= null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
