package com.example.parkingandroid.fragment.homepage;

import com.example.parkingandroid.R;
import com.example.parkingandroid.fragment.business.BaseFragment;

import butterknife.OnClick;

public class MainFragment1 extends BaseFragment {

    @OnClick(R.id.get_parking)
    void get_parking(){

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_main_one;
    }
}
