package com.example.parkingandroid.activity.business;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.parkingandroid.R;
import com.example.parkingandroid.activity.base.BaseEmptyActivity;
import com.example.parkingandroid.tools.AccountTool;

import butterknife.BindView;

public class SettingActivity extends BaseEmptyActivity implements View.OnClickListener{

    TextView logout;

    @Override
    protected int setContentRes() {
        return R.layout.activity_setting;
    }

    @Override
    public void initData() {
        super.initData();
        setTitle("设置");
        logout = findViewById(R.id.logout);
        logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.logout:
                if (AccountTool.isLogin(context)){
                    AccountTool.logout(context);
                    Toast.makeText(context,"操作成功",Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(context,"未登录",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
