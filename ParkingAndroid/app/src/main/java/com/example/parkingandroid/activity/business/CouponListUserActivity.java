package com.example.parkingandroid.activity.business;

import android.text.TextUtils;

import com.example.parkingandroid.R;
import com.example.parkingandroid.activity.base.BaseListActivity;
import com.example.parkingandroid.api.RS;
import com.example.parkingandroid.models.base.ResultModel;
import com.example.parkingandroid.models.business.CouponModel;
import com.example.parkingandroid.presenter.AccountPresenter;
import com.example.parkingandroid.presenter.CouponPresenter;
import com.example.parkingandroid.tools.AccountTool;
import com.example.parkingandroid.tools.Const;
import com.github.library.BaseViewHolder;

import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class CouponListUserActivity extends BaseListActivity<CouponModel> {

    CouponPresenter couponPresenter;
    @Override
    protected int getItemLayoutRes() {
        return R.layout.item_coupon;
    }


    @Override
    protected void convertData(BaseViewHolder helper, Object item) {
        //super.convertData(helper, item);
        CouponModel couponModel = (CouponModel)item;
        helper.setText(R.id.coupon_name,couponModel.getCoupon_name());
        helper.setText(R.id.coupon_money,"售价:"+couponModel.getMoney()+"元");
        helper.setText(R.id.coupon_decution,"价值:"+couponModel.getDeduction()+"元");
        helper.setText(R.id.coupon_deadline,"截止:"+couponModel.getDeadline());
    }

    @Override
    public void initData() {
        super.initData();
        setTitle("我的优惠券");
        couponPresenter = new CouponPresenter(context);
        couponPresenter.setOnCallBack(new CouponPresenter.OnCallBack() {
            @Override
            public void callback(boolean isSuccess, Object object) {

            }

            @Override
            public void getCouponListByUserId(boolean isSuccess, Object object) {
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
            public void getAllCouponStoreData(boolean isSuccess, Object object) {

            }

            @Override
            public void buyCoupon(boolean isSuccess, Object object) {

            }

            @Override
            public void failed(Object object) {

            }
        });

        getDataFromNet();
    }

    void getDataFromNet(){
        if (couponPresenter != null){
            couponPresenter.getCouponListByUserId(RS.getBaseParams(this));
        }
    }

    @Override
    protected void handleRefreshResult() {
        super.handleRefreshResult();
        getDataFromNet();
    }
}
