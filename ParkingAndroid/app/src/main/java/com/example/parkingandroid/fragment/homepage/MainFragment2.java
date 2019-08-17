package com.example.parkingandroid.fragment.homepage;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.parkingandroid.R;
import com.example.parkingandroid.api.RS;
import com.example.parkingandroid.fragment.business.BaseFragment;
import com.example.parkingandroid.intent.IntentManager;
import com.example.parkingandroid.models.base.ResultModel;
import com.example.parkingandroid.models.business.UserModel;
import com.example.parkingandroid.models.business.account.BalanceAndCoupon;
import com.example.parkingandroid.presenter.AccountPresenter;
import com.example.parkingandroid.tools.AccountTool;
import com.example.parkingandroid.tools.Const;
import com.google.gson.Gson;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class MainFragment2 extends BaseFragment {

    @BindView(R.id.balance)
    TextView balance;
    @BindView(R.id.coupon)
    TextView coupon;

    @BindView(R.id.login_tips_content)
    RelativeLayout loginTipsContent;



    AccountPresenter accountPresenter;

    @OnClick(R.id.balance_bar)
    void balance_bar(){
        if (AccountTool.isLogin(context)){
            IntentManager.intentToBalance(context);
        }
    }

    @OnClick(R.id.coupon_bar)
    void coupon_bar(){
        if (AccountTool.isLogin(context)){
            IntentManager.intentToCouponListUser(context);
        }
    }

    @OnClick(R.id.consume_record)
    void consume_record(){
        if (AccountTool.isLogin(context)){
            IntentManager.intentToConsumeRecord(context);
        }
    }

    @OnClick(R.id.coupon_store)
    void coupon_store(){
        if (AccountTool.isLogin(context)){
            IntentManager.intentToCouponStore(context);
        }
    }

    @OnClick(R.id.login_btn)
    void goLogin(){
        IntentManager.intentToLogin(context);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_main_two;
    }

    @Override
    public void onResume() {
        super.onResume();
        initAccountView();
    }

    private void initAccountView(){
        if (AccountTool.isLogin(context)){
            UserModel userModel = AccountTool.getLoginUser(context);
            if (userModel!=null){
                loginTipsContent.setVisibility(View.GONE);
                getBalanceAndCoupon();
            }
        }else{
            loginTipsContent.setVisibility(View.VISIBLE);
        }
    }



    @Override
    protected void initData() {
        super.initData();
        initPresenter();
    }

    private void initPresenter(){
        accountPresenter = new AccountPresenter(context);
        accountPresenter.setOnCallBack(new AccountPresenter.OnCallBack() {
            @Override
            public void callback(boolean isSuccess, Object object) {

            }

            @Override
            public void getBalanceAndCoupon(boolean isSuccess, Object object) {
                if (isSuccess && object != null){
                    ResultModel resultModel = (ResultModel)object;
                    if (resultModel != null && TextUtils.equals(resultModel.getCode(),Const.KEY_RES_CODE_200)){
                        BalanceAndCoupon balanceAndCoupon = new Gson().fromJson(resultModel.getModelJson(),BalanceAndCoupon.class);
                        if (balanceAndCoupon != null){
                            balance.setText(balanceAndCoupon.getBalance());
                            coupon.setText(balanceAndCoupon.getCoupon());
                        }
                    }
                }
            }

            @Override
            public void recharge(boolean isSuccess, Object object) {

            }

            @Override
            public void getRechargeRecord(boolean isSuccess, Object object) {

            }

            @Override
            public void failed(Object object) {

            }
        });
    }

    private void getBalanceAndCoupon(){
        resetBalanceAndCoupon();
        Map<String,String> params = RS.getBaseParams(context);
        params.put("user_id",AccountTool.getLoginUser(context).getId());
        accountPresenter.getBalanceAndCoupon(params);
    }

    private void resetBalanceAndCoupon(){
        balance.setText("");
        coupon.setText("");
    }
}
