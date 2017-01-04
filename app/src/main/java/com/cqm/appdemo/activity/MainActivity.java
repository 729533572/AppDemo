package com.cqm.appdemo.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cqm.appdemo.R;
import com.cqm.appdemo.base.BaseFragment;
import com.cqm.appdemo.fragment.HomeFragment;
import com.cqm.appdemo.fragment.InterestFragment;
import com.cqm.appdemo.fragment.UserFragment;
import com.cqm.appdemo.fragment.VedioFragment;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.tab_img1)
    ImageView tabImg1;
    @BindView(R.id.tab_text1)
    TextView tabText1;
    @BindView(R.id.tabbar1)
    RelativeLayout tabbar1;
    @BindView(R.id.tab_img2)
    ImageView tabImg2;
    @BindView(R.id.tab_text2)
    TextView tabText2;
    @BindView(R.id.tabbar2)
    RelativeLayout tabbar2;
    @BindView(R.id.tab_img3)
    ImageView tabImg3;
    @BindView(R.id.tab_text3)
    TextView tabText3;
    @BindView(R.id.tabbar3)
    RelativeLayout tabbar3;
    @BindView(R.id.tab_img4)
    ImageView tabImg4;
    @BindView(R.id.tab_text4)
    TextView tabText4;
    @BindView(R.id.tabbar4)
    RelativeLayout tabbar4;

    private int fragmentTag = 0;
    private ArrayList<String> fragmentNames = new ArrayList<>(Arrays.asList("Fragment1", "Fragment2", "Fragment3", "Fragment4"));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        selectFragement();
        showFragment();
    }

    private Fragment instantFragment(){
        BaseFragment baseFragment = null;
        switch (fragmentTag){
            case 0:
                baseFragment =  new HomeFragment();
                break;
            case 1:
                baseFragment =  new VedioFragment();
                break;
            case 2:
                baseFragment =  new InterestFragment();
                break;
            case 3:
                baseFragment =  new UserFragment();
                break;
            default:
                break;
        }
        return baseFragment;
    }

    private void showFragment(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        String fragmenTagName = fragmentNames.get(fragmentTag);
        Fragment fragment = fragmentManager.findFragmentByTag(fragmenTagName);
        if (fragment==null){
            fragment = instantFragment();
        }
        for (int i = 0; i < fragmentNames.size(); i++) {
            Fragment f = fragmentManager.findFragmentByTag(fragmentNames.get(i));
            if (f != null && f.isAdded()) {
                fragmentTransaction.hide(f);
            }
        }
        if (fragment.isAdded()){
            fragmentTransaction.show(fragment);
        }else{
            fragmentTransaction.add(R.id.container_fragment,fragment,fragmenTagName);
        }
        fragmentTransaction.commitAllowingStateLoss();
        fragmentManager.executePendingTransactions();

    }

    @OnClick({R.id.tabbar1, R.id.tabbar2, R.id.tabbar3, R.id.tabbar4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tabbar1:
                fragmentTag = 0;
                break;
            case R.id.tabbar2:
                fragmentTag = 1;
                break;
            case R.id.tabbar3:
                fragmentTag = 2;
                break;
            case R.id.tabbar4:
                fragmentTag = 3;
                break;
            default:
                break;
        }
        selectFragement();
        showFragment();
    }

    private void selectFragement(){
        switch (fragmentTag){
            case 0:
                tabImg1.setEnabled(true);
                tabImg2.setEnabled(false);
                tabImg3.setEnabled(false);
                tabImg4.setEnabled(false);
                tabText1.setTextColor(getResources().getColor(R.color.tabPressed));
                tabText2.setTextColor(getResources().getColor(R.color.tabNormal));
                tabText3.setTextColor(getResources().getColor(R.color.tabNormal));
                tabText4.setTextColor(getResources().getColor(R.color.tabNormal));

                break;
            case 1:
                tabImg1.setEnabled(false);
                tabImg2.setEnabled(true);
                tabImg3.setEnabled(false);
                tabImg4.setEnabled(false);
                tabText1.setTextColor(getResources().getColor(R.color.tabNormal));
                tabText2.setTextColor(getResources().getColor(R.color.tabPressed));
                tabText3.setTextColor(getResources().getColor(R.color.tabNormal));
                tabText4.setTextColor(getResources().getColor(R.color.tabNormal));
                break;
            case 2:
                tabImg1.setEnabled(false);
                tabImg2.setEnabled(false);
                tabImg3.setEnabled(true);
                tabImg4.setEnabled(false);
                tabText1.setTextColor(getResources().getColor(R.color.tabNormal));
                tabText2.setTextColor(getResources().getColor(R.color.tabNormal));
                tabText3.setTextColor(getResources().getColor(R.color.tabPressed));
                tabText4.setTextColor(getResources().getColor(R.color.tabNormal));
                break;
            case 3:
                tabImg1.setEnabled(false);
                tabImg2.setEnabled(false);
                tabImg3.setEnabled(false);
                tabImg4.setEnabled(true);
                tabText1.setTextColor(getResources().getColor(R.color.tabNormal));
                tabText2.setTextColor(getResources().getColor(R.color.tabNormal));
                tabText3.setTextColor(getResources().getColor(R.color.tabNormal));
                tabText4.setTextColor(getResources().getColor(R.color.tabPressed));
                break;
        }
    }
}
