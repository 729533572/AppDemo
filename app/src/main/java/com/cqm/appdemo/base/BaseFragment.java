package com.cqm.appdemo.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cqm.appdemo.R;

import butterknife.BindView;

/**
 * Created by chenqm on 2016/12/15.
 */

public class BaseFragment extends Fragment {

    @BindView(R.id.left_btn)
    ImageView leftBtn;
    @BindView(R.id.right_btn)
    ImageView rightBtn;
    @BindView(R.id.centet_title)
    TextView centetTitle;
    protected Context mContext = null;
    protected View mContentView = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
//        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
//            mContext = getContext();
//        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    protected void setRightBtn(View.OnClickListener listener) {
        rightBtn.setVisibility(View.VISIBLE);
        rightBtn.setOnClickListener(listener);
    }
    protected void setRightBtn(int resId, View.OnClickListener listener) {
        rightBtn.setVisibility(View.VISIBLE);
        rightBtn.setImageResource(resId);
        rightBtn.setOnClickListener(listener);
    }

    protected void setBack() {
        leftBtn.setVisibility(View.VISIBLE);
    }

    protected void setCentetTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            centetTitle.setText(title);
        }
    }
}
