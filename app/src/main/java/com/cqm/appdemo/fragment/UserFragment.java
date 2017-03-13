package com.cqm.appdemo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.cqm.appdemo.R;
import com.cqm.appdemo.activity.JokeActivity;
import com.cqm.appdemo.activity.WeatherActivity;
import com.cqm.appdemo.activity.WebViewActivity;
import com.cqm.appdemo.activity.XingZuoActivity;
import com.cqm.appdemo.base.BaseFragment;
import com.cqm.appdemo.util.GlideCircleTransform;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by chenqm on 2016/12/15.
 */

public class UserFragment extends BaseFragment {

    @BindView(R.id.headimg)
    ImageView headimg;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContentView = inflater.inflate(R.layout.fragment_user, container, false);
        ButterKnife.bind(this, mContentView);
        initView();
        return mContentView;
    }

    private void initView() {
        Glide.with(this).load(R.mipmap.user_img).transform(new GlideCircleTransform(mContext)).into(headimg);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.weather_view, R.id.movie_view, R.id.xingzuo_view, R.id.smile_view})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.weather_view:{
                Intent intent = new Intent(mContext, WeatherActivity.class);
                mContext.startActivity(intent);
            }
                break;
            case R.id.movie_view: {
                Intent intent = new Intent(mContext, WebViewActivity.class);
                intent.putExtra("webUrl", "https://m.douban.com/movie/");
                intent.putExtra("webTitle", "热播电影");
                intent.putExtra("desc", "豆瓣电影");
                mContext.startActivity(intent);
            }
            break;
            case R.id.xingzuo_view: {
                Intent intent = new Intent(mContext, XingZuoActivity.class);
                mContext.startActivity(intent);
            }
            break;
            case R.id.smile_view: {
                Intent intent = new Intent(mContext, JokeActivity.class);
                mContext.startActivity(intent);
            }
            break;
        }
    }
}
