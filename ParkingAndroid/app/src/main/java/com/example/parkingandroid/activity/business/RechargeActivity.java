package com.example.parkingandroid.activity.business;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.parkingandroid.R;
import com.example.parkingandroid.activity.base.BaseEmptyActivity;
import com.example.parkingandroid.api.RS;
import com.example.parkingandroid.dialog.CommonTipsDialog;
import com.example.parkingandroid.intent.IntentManager;
import com.example.parkingandroid.models.base.ResultModel;
import com.example.parkingandroid.models.business.account.BalanceAndCoupon;
import com.example.parkingandroid.models.business.account.BankCardModel;
import com.example.parkingandroid.presenter.AccountPresenter;
import com.example.parkingandroid.tools.AccountTool;
import com.example.parkingandroid.tools.Const;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

import static com.example.parkingandroid.intent.IntentManager.intentToBindBankcard;

public class RechargeActivity extends BaseEmptyActivity implements View.OnClickListener{

    boolean noBankCard = true;
    AccountPresenter accountPresenter;
    RelativeLayout tips_bar;

    TextView btn_bind_bankcard;
    Button btn_recharge;

    @Override
    protected int setContentRes() {
        return R.layout.activity_recharge;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("余额充值");
        initPresenter();
        getBankcard();
    }

    @Override
    public void initView() {
        super.initView();
        tips_bar = findViewById(R.id.tips_bar);
        btn_bind_bankcard = findViewById(R.id.btn_bind_bankcard);
        btn_recharge = findViewById(R.id.btn_recharge);

        btn_bind_bankcard.setOnClickListener(this);
        btn_recharge.setOnClickListener(this);
    }

    private void initPresenter(){
        accountPresenter = new AccountPresenter(context);
        accountPresenter.setOnCallBack(new AccountPresenter.OnCallBack() {
            @Override
            public void callback(boolean isSuccess, Object object) {

            }

            @Override
            public void getBalanceAndCoupon(boolean isSuccess, Object object) {

            }

            @Override
            public void getBankCard(boolean isSuccess, Object object) {
                if (isSuccess && object != null){
                    ResultModel resultModel = (ResultModel)object;
                    if (resultModel != null && TextUtils.equals(resultModel.getCode(),Const.KEY_RES_CODE_200)){
                        List<BankCardModel> bankCardModels = resultModel.getData();
                        if (bankCardModels != null && bankCardModels.size()>=1){
                            tips_bar.setVisibility(View.GONE);
                            noBankCard = false;
                        }else{
                            noBankCard = true;
                            tips_bar.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }

            @Override
            public void failed(Object object) {

            }
        });
    }

    private void getBankcard(){
        Map<String,String> params = RS.getBaseParams(context);
        params.put("user_id",AccountTool.getLoginUser(context).getId());
        accountPresenter.getBankCard(params);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_bind_bankcard:
                if (noBankCard){
                    IntentManager.intentToBindBankcard(context);
                }
                break;
            case R.id.btn_recharge:
                if (noBankCard){
                    CommonTipsDialog.show(context,"提示","未绑定银行卡，请按照页面提示进行操作",false);
                }
                break;
        }
    }
}
