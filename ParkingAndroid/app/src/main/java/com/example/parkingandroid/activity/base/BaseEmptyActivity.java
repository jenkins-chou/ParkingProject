package com.example.parkingandroid.activity.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.parkingandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public abstract class BaseEmptyActivity extends BaseActivity {

    @OnClick(R.id.back)
    void back(){
        backAction();
    }
    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.container)
    LinearLayout container;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_empty);
    }

    @Override
    public void initData() {
        super.initData();
        View view = getLayoutInflater().inflate(setContentRes(),null);
        if (view != null){
            container.addView(view);
        }
    }

    protected abstract int setContentRes();

    protected void backAction(){
        finish();
    }
    public void setTitle(String t){
        title.setText(t);
    }

    public void setTitle(int res){
        title.setText(res);
    }
}
