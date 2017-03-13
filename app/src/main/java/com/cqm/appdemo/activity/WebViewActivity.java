package com.cqm.appdemo.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.cqm.appdemo.R;
import com.cqm.appdemo.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by chenqm on 2017/1/4.
 */

public class WebViewActivity extends BaseActivity {


    @BindView(R.id.webView)
    WebView webView;

    private String webUrl = "";
    private String webTitle = "";
    private String desc = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        ShareSDK.initSDK(this);
        setBack();

        webUrl = getIntent().getStringExtra("webUrl");
        webTitle = getIntent().getStringExtra("webTitle");
        desc = getIntent().getStringExtra("desc");
        setCentetTitle(webTitle);
        webView.loadUrl(webUrl);

        setRightBtn(R.mipmap.share, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare();
            }
        });
    }


    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        oks.disableSSOWhenAuthorize();
        oks.setTitle(webTitle);
        oks.setTitleUrl(webUrl);
        oks.setText(desc);
//        oks.setImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489123925794&di=05ee2720e5333b3e9006e2a4412e0269&imgtype=0&src=http%3A%2F%2Fimg01.taopic.com%2F160117%2F318752-16011F9334648.jpg");
//        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        oks.setUrl(webUrl);
        oks.setComment("我是测试评论文本");
        oks.show(this);
    }

}
