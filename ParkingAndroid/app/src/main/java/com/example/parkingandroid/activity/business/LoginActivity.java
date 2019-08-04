package com.example.parkingandroid.activity.business;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.parkingandroid.R;
import com.example.parkingandroid.activity.base.BaseActivity;
import com.example.parkingandroid.api.RS;
import com.example.parkingandroid.dialog.CommonTipsDialog;
import com.example.parkingandroid.models.base.ResultModel;
import com.example.parkingandroid.models.business.UserModel;
import com.example.parkingandroid.presenter.UserPresenter;
import com.example.parkingandroid.tools.AccountTool;
import com.example.parkingandroid.tools.StringUtil;
import com.example.parkingandroid.ui.CommonLoading;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    private UserPresenter userPresenter;

    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.pass)
    EditText pass;

    @BindView(R.id.loading)
    CommonLoading loading;

    @OnClick(R.id.forget_password)
    void forget_password(){
        CommonTipsDialog.showTip(this,"温馨提示","忘记登录密码或账号请联系管理员找回",false);
    }

    @OnClick(R.id.goto_register)
    void goto_register(){
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.goto_login)
    void goto_login(){
        String name_str = name.getText().toString();
        String pass_str = pass.getText().toString();
        if (!StringUtil.isNotEmpty(name_str)
                &&!StringUtil.isNotEmpty(pass_str)){
            CommonTipsDialog.showTip(this,"温馨提示","请完善信息",false);
            return;
        }else{
            Map<String,String> params = RS.getBaseParams(this);
            params.put("useridentify",name_str);
            params.put("pass",pass_str);
            userPresenter.login(params);
            setLoadingEnable(true);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    void setLoadingEnable(boolean enable){
        if (loading==null)return;
        if (enable){
            loading.setVisibility(View.VISIBLE);
        }else{
            loading.setVisibility(View.GONE);
        }
    }

    @Override
    public void initData() {
        super.initData();
        userPresenter = new UserPresenter(this);
        userPresenter.setOnCallBack(new UserPresenter.OnCallBack() {

            @Override
            public void login(boolean isSuccess, Object object) {
                setLoadingEnable(false);
                if (isSuccess){
                    if (object!=null){
                        ResultModel resultModel = (ResultModel)object;
                        if (resultModel!=null&&StringUtil.isEquals(resultModel.getCode(),"200")){
                            if (resultModel.getData()!=null&&resultModel.getData().size()>0){
                                List<UserModel> userModels = resultModel.getData();
                                UserModel userModel = userModels.get(0);
                                Log.e("login",userModel.toString());
                                Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                                AccountTool.saveUser(LoginActivity.this,userModel);
                                finish();
                            }else{
                                Toast.makeText(LoginActivity.this, "账号不存在或登录信息有误", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void addUser(boolean isSuccess, Object object) {

            }

            @Override
            public void updateUser(boolean isSuccess, Object object) {

            }

            @Override
            public void deleteUser(boolean isSuccess, Object object) {

            }
        });
    }
}
