package com.example.parkingandroid.activity.business;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.parkingandroid.R;
import com.example.parkingandroid.activity.base.BaseListActivity;
import com.example.parkingandroid.api.RS;
import com.example.parkingandroid.dialog.CommonTipsDialog;
import com.example.parkingandroid.models.base.ResultModel;
import com.example.parkingandroid.models.business.CouponModel;
import com.example.parkingandroid.presenter.CouponPresenter;
import com.example.parkingandroid.tools.Const;
import com.github.library.BaseViewHolder;

import java.util.List;
import java.util.Map;

public class CouponStoreActivity extends BaseListActivity {

    CouponPresenter couponPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("优惠券商店");
    }

    @Override
    protected int getItemLayoutRes() {
        return R.layout.activity_coupon_store_item;
    }

    @Override
    protected void convertData(BaseViewHolder helper, Object item) {
        //super.convertData(helper, item);
        CouponModel couponModel = (CouponModel)item;
        helper.setText(R.id.coupon_name,couponModel.getCoupon_name());
        helper.setText(R.id.coupon_decution,"￥"+couponModel.getDeduction());
        helper.setText(R.id.coupon_money,"￥"+couponModel.getMoney());

        TextView buyBtn = helper.getView(R.id.buy_btn);
        buyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buy(couponModel);
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
        couponPresenter = new CouponPresenter(context);
        couponPresenter.setOnCallBack(new CouponPresenter.OnCallBack() {
            @Override
            public void callback(boolean isSuccess, Object object) {

            }

            @Override
            public void getCouponListByUserId(boolean isSuccess, Object object) {

            }

            @Override
            public void getAllCouponStoreData(boolean isSuccess, Object object) {
                finishRefresh();
                if (isSuccess && object != null) {
                    ResultModel resultModel = (ResultModel) object;
                    if (resultModel != null && TextUtils.equals(resultModel.getCode(), Const.KEY_RES_CODE_200)) {
                        List<CouponModel> couponModels = resultModel.getData();
                        if (couponModels != null){
                            setData(couponModels);
                        }
                    }
                }
            }

            @Override
            public void buyCoupon(boolean isSuccess, Object object) {
                if (isSuccess && object != null) {
                    ResultModel resultModel = (ResultModel) object;
                    if (resultModel != null && TextUtils.equals(resultModel.getCode(), Const.KEY_RES_CODE_200)) {
                        Toast.makeText(context, "购买成功", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context, resultModel.getMessage()+"", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void failed(Object object) {

            }
        });

        couponPresenter.getAllCouponStoreData(RS.getBaseParams(this));
    }

    void getDataFromNet(){
        if (couponPresenter != null){
            couponPresenter.getAllCouponStoreData(RS.getBaseParams(this));
        }
    }

    @Override
    protected void handleRefreshResult() {
        super.handleRefreshResult();
        getDataFromNet();
    }

    void buy(CouponModel couponModel){
        if (couponModel != null){
            CommonTipsDialog.create(context,"温馨提示","确定购买"+couponModel.getCoupon_name()+"吗？",false)
                    .setOnClickListener(new CommonTipsDialog.OnClickListener() {
                        @Override
                        public void cancel() {

                        }

                        @Override
                        public void confirm() {
                            Map<String,String> params = RS.getBaseParams(CouponStoreActivity.this);
                            params.put("coupon_id",couponModel.getId());
                            params.put("coupon_money",couponModel.getMoney());
                            couponPresenter.buyCoupon(params);
                        }
                    }).show();
        }
    }
}
