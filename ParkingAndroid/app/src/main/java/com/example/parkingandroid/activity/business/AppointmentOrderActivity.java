package com.example.parkingandroid.activity.business;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.parkingandroid.activity.base.BaseListActivity;
import com.example.parkingandroid.models.business.ParkingLotModel;

public class AppointmentOrderActivity extends BaseListActivity<ParkingLotModel> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("我的预约订单");
    }
}
