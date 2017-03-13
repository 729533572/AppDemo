package com.cqm.appdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.cqm.appdemo.R;
import com.cqm.appdemo.adapter.GirdViewAdapter;
import com.cqm.appdemo.base.BaseActivity;
import com.cqm.appdemo.data.ConsData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chenqm on 2017/1/4.
 */

public class XingZuoActivity extends BaseActivity {

    @BindView(R.id.girdView)
    GridView girdView;

    private List<ConsData> listDatas = new ArrayList<>();

    //图片数组
    private Integer[] iconId = {
            R.mipmap.cat01, R.mipmap.cat02, R.mipmap.cat03,
            R.mipmap.cat04, R.mipmap.cat05, R.mipmap.cat06,
            R.mipmap.cat07, R.mipmap.cat08, R.mipmap.cat09,
            R.mipmap.cat10, R.mipmap.cat11, R.mipmap.cat12
    };
    private String[] iconName = {"白羊座", "金牛座", "双子座", "巨蟹座", "狮子座",
            "处女座", "天枰座", "天蝎座", "射手座", "摩羯座", "水瓶座", "双鱼座"};
    private String[] iconDate = {"03.21-04.19", "04.20-05.20", "05.21-06.21", "06.22-07-22",
            "07.23-08.22", "08.23-9.22", "09.23-10.23", "10.24-11.22", "11.23-12.21",
            "12.22-01.19", "01.20-02.18", "02.19-3.20"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xingzuo);
        ButterKnife.bind(this);
        setCentetTitle("星座运势");
        setBack();
        listDatas.clear();
        for (int i = 0; i < iconId.length; i++) {
            ConsData item = new ConsData();
            item.setIconId(iconId[i]);
            item.setName(iconName[i]);
            item.setDate(iconDate[i]);
            listDatas.add(item);
        }

        girdView.setAdapter(new GirdViewAdapter(this, listDatas));
        //注册监听事件
        girdView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext, FateActivity.class);
                ConsData item = listDatas.get(position);
                intent.putExtra("consName", item.getName());
                intent.putExtra("consDate", item.getDate());
                intent.putExtra("consIconId", item.getIconId());
                mContext.startActivity(intent);
            }
        });

    }

}
