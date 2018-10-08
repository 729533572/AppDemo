package com.cqm.appdemo.activity;

import android.os.Bundle;
import android.text.TextUtils;
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
    private String imgurl = "";


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
        imgurl = getIntent().getStringExtra("imgurl");
        if (TextUtils.isEmpty(imgurl)) {
            imgurl = "https://github.com/chenqunming/AppDemo/blob/master/app/src/main/res/mipmap-xxhdpi/logo.jpg";
        }
        setCentetTitle(webTitle);
        webView.loadUrl(webUrl);

//        setRightBtn(R.mipmap.share, new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showShare();
//            }
//        });
    }


    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        oks.disableSSOWhenAuthorize();
        oks.setTitle(webTitle);
        oks.setTitleUrl(webUrl);
        oks.setText(desc);
        oks.setImageUrl(imgurl);
//        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        oks.setUrl(webUrl);
//        oks.setComment("我是测试评论文本");
        oks.show(this);
    }

}
