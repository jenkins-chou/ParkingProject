package com.example.parkingandroid.activity.business;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.parkingandroid.R;
import com.example.parkingandroid.activity.base.BaseEmptyActivity;
import com.example.parkingandroid.api.RS;
import com.example.parkingandroid.models.base.ResultModel;
import com.example.parkingandroid.models.business.UserModel;
import com.example.parkingandroid.presenter.UserPresenter;
import com.example.parkingandroid.tools.AccountTool;
import com.example.parkingandroid.tools.StringUtil;

import java.util.List;
import java.util.Map;

public class AccountPayPwdActivity extends BaseEmptyActivity implements View.OnClickListener{
    UserPresenter userPresenter;
    EditText login_password_edit;
    EditText pay_pwd_edit;
    TextView check_status_text;
    Button btn;
    @Override
    protected int setContentRes() {
        return R.layout.activity_pay_pwd;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("支付密码");
    }

    @Override
    public void initData() {
        super.initData();
        login_password_edit = findViewById(R.id.login_password);
        pay_pwd_edit = findViewById(R.id.pay_pwd);
        check_status_text = findViewById(R.id.check_status);
        btn = findViewById(R.id.btn);

        btn.setOnClickListener(this);

        login_password_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (checkInputPwd()){
                    check_status_text.setText("正确");
                }else{
                    check_status_text.setText("错误");
                }
            }
        });

        userPresenter = new UserPresenter(context);
        userPresenter.setOnCallBack(new UserPresenter.OnCallBack() {
            @Override
            public void login(boolean isSuccess, Object object) {

            }

            @Override
            public void addUser(boolean isSuccess, Object object) {

            }

            @Override
            public void updateUser(boolean isSuccess, Object object) {
                if (isSuccess){
                    if (object!=null){
                        ResultModel resultModel = (ResultModel)object;
                        if (resultModel!=null&&StringUtil.isEquals(resultModel.getCode(),"200")){
                            String input_pay_pwd = pay_pwd_edit.getText().toString();
                            UserModel userModel = AccountTool.getLoginUser(AccountPayPwdActivity.this);
                            userModel.setPay_pwd(input_pay_pwd);
                            AccountTool.reSaveUser(AccountPayPwdActivity.this,userModel);
                            Toast.makeText(AccountPayPwdActivity.this, "设置成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(AccountPayPwdActivity.this, "更新失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void deleteUser(boolean isSuccess, Object object) {

            }
        });
    }

    private boolean checkInputPwd(){
        if (login_password_edit == null){
            return false;
        }
        String input_pwd = login_password_edit.getText().toString();
        UserModel userModel = AccountTool.getLoginUser(AccountPayPwdActivity.this);
        if (userModel != null){
            if (TextUtils.equals(userModel.getPass(),input_pwd)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn:
                if (checkInputPwd()){
                    String input_pay_pwd = pay_pwd_edit.getText().toString();
                    if (!TextUtils.isEmpty(input_pay_pwd) && input_pay_pwd.length()==6){
                        Map<String,String> params = RS.getBaseParams(this);
                        params.put("id",AccountTool.getLoginUser(AccountPayPwdActivity.this).getId());
                        params.put("pay_pwd",input_pay_pwd);
                        userPresenter.updateUser(params);
                    }else{
                        Toast.makeText(context, "新支付密码少于6位数字", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(context, "登录密码校验错误，请重新确认", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
