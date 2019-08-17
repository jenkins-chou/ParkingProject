package com.example.parkingandroid.activity.business;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.parkingandroid.R;
import com.example.parkingandroid.activity.base.BaseEmptyActivity;
import com.example.parkingandroid.api.RS;
import com.example.parkingandroid.dialog.CommonTipsDialog;
import com.example.parkingandroid.models.base.ResultModel;
import com.example.parkingandroid.models.business.account.BalanceAndCoupon;
import com.example.parkingandroid.models.business.account.BankCardModel;
import com.example.parkingandroid.presenter.BankCardPresenter;
import com.example.parkingandroid.tools.Const;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

public class BindBankCardActivity extends BaseEmptyActivity implements View.OnClickListener{
    EditText bank_name;
    EditText bankcard_number;
    EditText bankcard_username;
    EditText bankcard_phone;
    Button bindBtn;
    BankCardPresenter bankCardPresenter;
    BankCardModel bankCardModel;
    @Override
    protected int setContentRes() {
        return R.layout.activity_bind_bankcard;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("绑定银行卡");
        setToolbarRightButtonEnable(true);
        setToolbarRightButtonTitle("重置");
    }

    @Override
    public void initData() {
        super.initData();
        bankCardPresenter = new BankCardPresenter(context);
        bankCardPresenter.setOnCallBack(new BankCardPresenter.OnCallBack() {
            @Override
            public void callback(boolean isSuccess, Object object) {

            }

            @Override
            public void bindBankCard(boolean isSuccess, Object object) {
                if (isSuccess && object != null) {
                    ResultModel resultModel = (ResultModel) object;
                    if (resultModel != null && TextUtils.equals(resultModel.getCode(), Const.KEY_RES_CODE_200)) {
                        Toast.makeText(context, "绑定成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }

            @Override
            public void getBankCard(boolean isSuccess, Object object) {
                if (isSuccess && object != null){
                    ResultModel resultModel = (ResultModel)object;
                    if (resultModel != null && TextUtils.equals(resultModel.getCode(),Const.KEY_RES_CODE_200)){
                        List<BankCardModel> bankCardModels = resultModel.getData();
                        if (bankCardModels != null && bankCardModels.size()>=1){
                            BankCardModel bankCardModel = bankCardModels.get(0);
                            setInfo(bankCardModel);
                        }else{
//                            noBankCard = true;
//                            tips_bar.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }

            @Override
            public void deleteBankCard(boolean isSuccess, Object object) {
                if (isSuccess && object != null) {
                    ResultModel resultModel = (ResultModel) object;
                    if (resultModel != null && TextUtils.equals(resultModel.getCode(), Const.KEY_RES_CODE_200)) {
                        Toast.makeText(context, "重置成功", Toast.LENGTH_SHORT).show();
                        setBindBtnEnable(true);
                        resetInfo();
                    }
                }
            }

            @Override
            public void failed(Object object) {

            }
        });

        bankCardPresenter.getBankCard(RS.getBaseParams(this));
    }

    @Override
    public void initView() {
        super.initView();
        bank_name = findViewById(R.id.bank_name);
        bankcard_number = findViewById(R.id.bankcard_number);
        bankcard_username = findViewById(R.id.bankcard_username);
        bankcard_phone = findViewById(R.id.bankcard_phone);
        bindBtn = findViewById(R.id.bind);
        bindBtn.setOnClickListener(this);
    }

    @Override
    public void onToolbarRightButtonClick() {
        super.onToolbarRightButtonClick();
        if (bankCardModel ==null){
            Toast.makeText(context, "银行卡获取中，请稍后重试", Toast.LENGTH_SHORT).show();
        }
        CommonTipsDialog.create(context,"温馨提示","确定要重置吗",false)
                .setOnClickListener(new CommonTipsDialog.OnClickListener() {
                    @Override
                    public void cancel() {

                    }

                    @Override
                    public void confirm() {
                        Map<String,String> params = RS.getBaseParams(context);
                        params.put("id",bankCardModel.getId());
                        bankCardPresenter.deleteBankCard(params);
                    }
                }).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bind:
                if (checkInputValiable()){
                    bankCardPresenter.bindBankCard(getRequestParams());
                }else{
                    CommonTipsDialog.show(context,"温馨提示","请输入完整信息",false);
                }
                break;
        }
    }

    void setInfo(BankCardModel bankCardModel){
        if (bankCardModel == null) return;
        this.bankCardModel = bankCardModel;
        bank_name.setText(bankCardModel.getBank_name());
        bankcard_number.setText(bankCardModel.getBankcard_number());
        bankcard_username.setText(bankCardModel.getBankcard_username());
        bankcard_phone.setText(bankCardModel.getBankcard_phone());
        setBindBtnEnable(false);
    }

    void resetInfo(){
        bank_name.setText("");
        bankcard_number.setText("");
        bankcard_username.setText("");
        bankcard_phone.setText("");
    }

    void setBindBtnEnable(boolean enable){
        bindBtn.setVisibility(enable?View.VISIBLE:View.GONE);
    }

    public boolean checkInputValiable(){
        String bankNameStr = bank_name.getText().toString();
        String bankCardNumberStr = bankcard_number.getText().toString();
        String bankCardUsernameStr = bankcard_username.getText().toString();
        String bankCardPhoneStr = bankcard_phone.getText().toString();
        if (!TextUtils.isEmpty(bankNameStr)
                && !TextUtils.isEmpty(bankCardNumberStr)
                && !TextUtils.isEmpty(bankCardUsernameStr)
                && !TextUtils.isEmpty(bankCardPhoneStr)){
            return true;
        }else {
            return false;
        }
    }

    public Map<String,String> getRequestParams(){
        Map<String,String> params = RS.getBaseParams(this);
        params.put("bank_name",bank_name.getText().toString());
        params.put("bankcard_number",bankcard_number.getText().toString());
        params.put("bankcard_username",bankcard_username.getText().toString());
        params.put("bankcard_phone",bankcard_phone.getText().toString());
        return params;
    }
}
