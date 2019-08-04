package com.example.parkingandroid.activity.business;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.parkingandroid.R;
import com.example.parkingandroid.activity.base.BaseListActivity;
import com.github.library.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class TestListActivity extends BaseListActivity<String> {

    private List<String> datas = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("测试");


        datas.add("1212121");
        datas.add("1212121");
        datas.add("1212121");
        setData(datas);
    }

    @Override
    protected int getItemLayoutRes() {
        return R.layout.activity_base_list_test_item;
    }

    @Override
    protected void convertData(BaseViewHolder helper, Object item) {
        super.convertData(helper,item);
    }
}
