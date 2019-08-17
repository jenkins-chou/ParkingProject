package com.example.parkingandroid.activity.business;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.parkingandroid.activity.base.BaseListActivity;

public class ConsumeRecordActivity extends BaseListActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("消费记录");
    }
}
