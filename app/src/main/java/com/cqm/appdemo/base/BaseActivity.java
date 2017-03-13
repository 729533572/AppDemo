package com.cqm.appdemo.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cqm.appdemo.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by chenqm on 2016/12/15.
 */

public class BaseActivity extends AppCompatActivity {

    @BindView(R.id.left_btn)
    ImageView leftBtn;
    @BindView(R.id.right_btn)
    ImageView rightBtn;
    @BindView(R.id.centet_title)
    TextView centetTitle;
    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        mContext = this;
    }

    @OnClick(R.id.left_btn)
    public void onClick() {
        finish();
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


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    protected void showLongToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show();
    }

    protected void showShortToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }


}
