package com.cqm.appdemo.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cqm.appdemo.R;
import com.cqm.appdemo.base.BaseActivity;
import com.cqm.appdemo.bean.ConsBean;
import com.cqm.appdemo.http.ApiService;
import com.cqm.appdemo.http.RetrofitUtil;

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

public class FateActivity extends BaseActivity {

    @BindView(R.id.consimg)
    ImageView consimg;
    @BindView(R.id.consname)
    TextView consname;
    @BindView(R.id.consdate)
    TextView consdate;
    @BindView(R.id.fate_friend)
    TextView fateFriend;
    @BindView(R.id.fate_all)
    TextView fateAll;
    @BindView(R.id.fate_color)
    TextView fateColor;
    @BindView(R.id.fate_health)
    TextView fateHealth;
    @BindView(R.id.fate_number)
    TextView fateNumber;
    @BindView(R.id.fate_money)
    TextView fateMoney;
    @BindView(R.id.fate_love)
    TextView fateLove;
    @BindView(R.id.fate_work)
    TextView fateWork;
    @BindView(R.id.fate_summary)
    TextView fateSummary;

    private String consName = "";
    private String type = "today";
    private static final String key = "bab12e25d0f2e0777e9a0d5870438013";
    private static final String url = "http://web.juhe.cn:8080/constellation/getAll";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fate);
        ButterKnife.bind(this);
        setCentetTitle("今日运势");
        consName = getIntent().getStringExtra("consName");
        String consDate = getIntent().getStringExtra("consDate");
        int consIconId = getIntent().getIntExtra("consIconId", 0);
        consimg.setImageResource(consIconId);
        consname.setText(consName);
        consdate.setText(consDate);
        setBack();
//        setRightBtn(R.mipmap.share, new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            }
//        });

        loadData();


    }

    private void loadData() {
        Map<String, String> map = new HashMap<>();
        map.put("key", key);
        map.put("consName", consName);
        map.put("type", type);
        ApiService service = RetrofitUtil.create(ApiService.class);
        Call<ConsBean> call = service.getConsData(url, map);
        call.enqueue(new Callback<ConsBean>() {
            @Override
            public void onResponse(Call<ConsBean> call, Response<ConsBean> response) {
                ConsBean bean = response.body();
                String code = bean.getError_code();
                if (code.equals("0")) {
                    fateAll.setText(bean.getAll());
                    fateFriend.setText(bean.getQFriend());
                    fateWork.setText(bean.getWork());
                    fateMoney.setText(bean.getMoney());
                    fateLove.setText(bean.getLove());
                    fateSummary.setText(bean.getSummary());
                    fateColor.setText(bean.getColor());
                    fateNumber.setText(bean.getNumber());
                    fateHealth.setText(bean.getHealth());
                } else {
                    Toast.makeText(mContext, bean.getReason(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ConsBean> call, Throwable t) {
                Toast.makeText(mContext, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

}
