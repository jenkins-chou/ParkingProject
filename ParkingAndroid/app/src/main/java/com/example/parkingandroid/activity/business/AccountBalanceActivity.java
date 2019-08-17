package com.example.parkingandroid.activity.business;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.parkingandroid.R;
import com.example.parkingandroid.activity.base.BaseEmptyActivity;
import com.example.parkingandroid.api.RS;
import com.example.parkingandroid.dialog.CommonTipsDialog;
import com.example.parkingandroid.intent.IntentManager;
import com.example.parkingandroid.models.base.ResultModel;
import com.example.parkingandroid.models.business.account.BalanceAndCoupon;
import com.example.parkingandroid.presenter.AccountPresenter;
import com.example.parkingandroid.tools.AccountTool;
import com.example.parkingandroid.tools.Const;
import com.google.gson.Gson;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class AccountBalanceActivity extends BaseEmptyActivity implements View.OnClickListener {

    //@BindView(R.id.balance)
    TextView balance;
    Button goto_recharge;
    Button goto_bankcard;
    AccountPresenter accountPresenter;

    @Override
    protected int setContentRes() {
        return R.layout.activity_account_balance;
    }

    @Override
    public void initData() {
        super.initData();
        setTitle("");//去除标题
        initPresenter();

        balance = findViewById(R.id.balance);
        goto_recharge = findViewById(R.id.goto_recharge);
        goto_bankcard = findViewById(R.id.goto_bankcard);

        goto_recharge.setOnClickListener(this);
        goto_bankcard.setOnClickListener(this);
    }

    @Override
    public void initView() {
        super.initView();
        setToolbarRightButtonEnable(true);
        setToolbarRightButtonTitle("充值记录");
    }

    @Override
    public void onToolbarRightButtonClick() {
        super.onToolbarRightButtonClick();
        IntentManager.intentToRechargeRecord(context);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getBalanceAndCoupon();
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
        if (accountPresenter ==null)return;
        resetBalanceAndCoupon();
        Map<String,String> params = RS.getBaseParams(context);
        params.put("user_id",AccountTool.getLoginUser(context).getId());
        accountPresenter.getBalanceAndCoupon(params);
    }

    private void resetBalanceAndCoupon(){
        balance.setText("");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.goto_recharge:
                IntentManager.intentToRecharge(context);
                break;
            case R.id.goto_bankcard:
                IntentManager.intentToBindBankcard(context);
                break;
        }

    }
}
