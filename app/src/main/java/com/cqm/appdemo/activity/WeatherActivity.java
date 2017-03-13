package com.cqm.appdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cqm.appdemo.R;
import com.cqm.appdemo.base.BaseActivity;
import com.cqm.appdemo.http.ApiService;
import com.cqm.appdemo.http.RetrofitUtil;
import com.cqm.appdemo.util.SPUtils;
import com.zaaach.citypicker.CityPickerActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by chenqm on 2017/1/4.
 */

public class WeatherActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    private static final int REQUEST_CODE_PICK_CITY = 0;
    @BindView(R.id.temperature)
    TextView temperature;
    @BindView(R.id.city_name)
    TextView cityName;
    @BindView(R.id.city_info)
    TextView cityInfo;
    @BindView(R.id.city_wind)
    TextView cityWind;
    @BindView(R.id.weather_ll)
    LinearLayout weatherLl;
    @BindView(R.id.chuanyi)
    TextView chuanyi;
    @BindView(R.id.ganmao)
    TextView ganmao;
    @BindView(R.id.yundong)
    TextView yundong;

    @BindView(R.id.refreshlayout)
    SwipeRefreshLayout refreshlayout;
    private boolean isRefreshing = false;

    private static final String key = "01148736b3a79578c191e86cf7102da1";
    private static final String url = "http://op.juhe.cn/onebox/weather/query";


    private String cityname = "厦门";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);
        setCentetTitle("天气预报");
        setBack();
        refreshlayout.setColorSchemeResources(android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        refreshlayout.setOnRefreshListener(this);
        cityname = (String) SPUtils.get(mContext, "cityname", cityname);
        cityName.setText(cityname);
        weatherLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(mContext, CityPickerActivity.class),
                        REQUEST_CODE_PICK_CITY);
            }
        });

        loadData();


    }

    @Override
    public void onRefresh() {
        if (!isRefreshing) {
            isRefreshing = true;
            loadData();
        }
    }

    private void loadData() {
        isRefreshing = true;
        Map<String, String> map = new HashMap<>();
        map.put("key", key);
        map.put("cityname", cityname);
        ApiService service = RetrofitUtil.create(ApiService.class);
        Call<String> call = service.getWeatherData(url, key, cityname);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                refreshlayout.setRefreshing(false);
                isRefreshing = false;
                String result = response.body();
                if (!TextUtils.isEmpty(result)) {
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        String error_code = jsonObject.optString("error_code");
                        if (error_code.equals("0")) {
                            String temp = jsonObject.getJSONObject("result") .getJSONObject("data")
                                    .getJSONObject("realtime").getJSONObject("weather").optString("temperature");
                            temperature.setText(temp+"°");
                            String info = jsonObject.getJSONObject("result").getJSONObject("data")
                                    .getJSONObject("realtime").getJSONObject("weather").optString("info");
                            cityInfo.setText(info);
                            String direct = jsonObject.getJSONObject("result").getJSONObject("data")
                                    .getJSONObject("realtime").getJSONObject("wind").optString("direct");
                            String power = jsonObject.getJSONObject("result").getJSONObject("data")
                                    .getJSONObject("realtime").getJSONObject("wind").optString("power");
                            cityWind.setText(direct + "/" + power );

                            JSONObject lifeJson = jsonObject.getJSONObject("result").getJSONObject("data")
                                    .getJSONObject("life").getJSONObject("info");
                            JSONArray array = lifeJson.getJSONArray("chuanyi");
                            String tempStr = "穿衣：";
                            if (array.length() > 1) {
                                tempStr += array.get(0) + "，" + array.get(1);
                            }
                            chuanyi.setText(tempStr);

                            array = lifeJson.getJSONArray("ganmao");
                            tempStr = "感冒：";
                            if (array.length() > 1) {
                                tempStr += array.get(0) + "，" + array.get(1);
                            }
                            ganmao.setText(tempStr);

                            array = lifeJson.getJSONArray("yundong");
                            tempStr = "运动：";
                            if (array.length() > 1) {
                                tempStr += array.get(0) + "，" + array.get(1);
                            }
                            yundong.setText(tempStr);

                        } else {
                            showLongToast(jsonObject.optString("reason"));
                        }

                    } catch (Exception e) {
                        showLongToast(e.toString());
                    }

                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                refreshlayout.setRefreshing(false);
                isRefreshing = false;
                showLongToast(t.toString());
            }
        });
    }


    //重写onActivityResult方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PICK_CITY && resultCode == RESULT_OK) {
            if (data != null) {
                cityname = data.getStringExtra(CityPickerActivity.KEY_PICKED_CITY);
                cityName.setText(cityname);
                SPUtils.put(mContext, "cityname", cityname);
                loadData();
            }
        }
    }

}
