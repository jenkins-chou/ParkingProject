package com.example.parkingandroid.activity.business;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.parkingandroid.R;
import com.example.parkingandroid.activity.base.BaseActivity;
import com.example.parkingandroid.api.RS;
import com.example.parkingandroid.dialog.CommonTipsDialog;
import com.example.parkingandroid.models.base.ResultModel;
import com.example.parkingandroid.models.business.UserModel;
import com.example.parkingandroid.presenter.UserPresenter;
import com.example.parkingandroid.tools.AccountTool;
import com.example.parkingandroid.tools.Const;
import com.example.parkingandroid.tools.StringUtil;
import com.example.parkingandroid.ui.CommonLoading;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {

    private UserPresenter userPresenter;
    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.pass)
    TextView password;
    @BindView(R.id.confirm_pass)
    TextView confirm_pass;

    @BindView(R.id.loading)
    CommonLoading loading;

    @OnClick(R.id.goto_register)
    void goto_register(){
        String username_str = username.getText().toString();
        String password_str = password.getText().toString();
        String confirm_pass_str = confirm_pass.getText().toString();
        if (!StringUtil.isNotEmpty(username_str)
                ||!StringUtil.isNotEmpty(password_str)
                ||!StringUtil.isNotEmpty(confirm_pass_str)){
            CommonTipsDialog.showTip(this,"温馨提示","请完善信息",false);
            return;
        }else{
            Map<String,String> params = RS.getBaseParams(this);
            params.put("useridentify",username_str);
            params.put("pass",password_str);
            params.put("type","1");
            userPresenter.addUser(params);
            setLoadingEnable(true);
        }
    }

    @OnClick(R.id.return_login)
    void return_login(){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
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

            }

            @Override
            public void addUser(boolean isSuccess, Object object) {
                setLoadingEnable(false);
                if (isSuccess){
                    if (object!=null){
                        ResultModel resultModel = (ResultModel)object;
                        if (resultModel!=null&&StringUtil.isEquals(resultModel.getCode(),Const.KEY_RES_CODE_200)){
                            if (resultModel.getData()!=null&&resultModel.getData().size()>0){
                                List<UserModel> userModels = resultModel.getData();
                                UserModel userModel = userModels.get(0);
                                Toast.makeText(RegisterActivity.this, "注册成功，已默认登录", Toast.LENGTH_SHORT).show();
                                AccountTool.saveUser(RegisterActivity.this,userModel);
                                finish();
                            }

                        }else{
                            Toast.makeText(RegisterActivity.this, "已存在该用户名", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
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
