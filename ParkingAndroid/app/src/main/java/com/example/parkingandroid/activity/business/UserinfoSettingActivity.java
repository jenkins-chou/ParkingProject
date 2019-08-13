package com.example.parkingandroid.activity.business;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
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
import com.example.parkingandroid.tools.Const;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class UserinfoSettingActivity extends BaseEmptyActivity {
    EditText name;
    EditText sex;
    EditText nation;
    EditText age;
    EditText email;
    EditText phone;
    EditText realname;
    EditText slogan;
    TextView submit;
    UserPresenter userPresenter;

    @Override
    protected int setContentRes() {
        return R.layout.activity_userinfo_setting;
    }

    @Override
    public void initData() {
        super.initData();
        name = findViewById(R.id.name);
        sex = findViewById(R.id.sex);
        nation = findViewById(R.id.nation);
        age = findViewById(R.id.age);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        realname = findViewById(R.id.realname);
        slogan = findViewById(R.id.slogan);

        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userPresenter.updateUser(getInputInfo());
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
                        if (resultModel != null && TextUtils.equals(resultModel.getCode(),Const.KEY_RES_CODE_200)){
                            Toast.makeText(context, "更新成功", Toast.LENGTH_SHORT).show();

                            AccountTool.reSaveUser(context,getTempModel());
                        }
                    }
                }
            }

            @Override
            public void deleteUser(boolean isSuccess, Object object) {

            }
        });
    }

    @Override
    public void initView() {
        super.initView();
        UserModel userModel = AccountTool.getLoginUser(context);
        if (userModel != null){
            name.setText(userModel.name);
            sex.setText(userModel.sex);
            nation.setText(userModel.nation);
            age.setText(userModel.age);
            email.setText(userModel.email);
            phone.setText(userModel.phone);
            realname.setText(userModel.realname);
            slogan.setText(userModel.slogan);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("用户信息");
    }

    public Map getInputInfo(){
        Map<String,String> params = RS.getBaseParams(context);
        params.put("id",AccountTool.getLoginUser(context).getId()+"");
        params.put("name",name.getText().toString()+"");
        params.put("sex",sex.getText().toString()+"");
        params.put("nation",nation.getText().toString()+"");
        params.put("age",age.getText().toString()+"");
        params.put("email",email.getText().toString()+"");
        params.put("phone",phone.getText().toString()+"");
        params.put("realname",realname.getText().toString()+"");
        params.put("slogan",slogan.getText().toString()+"");
        return params;
    }

    public UserModel getTempModel(){
        UserModel temp = new UserModel();
        temp.name = name.getText().toString()+"";
        temp.sex = sex.getText().toString()+"";
        temp.nation = nation.getText().toString()+"";
        temp.age = age.getText().toString()+"";
        temp.email = email.getText().toString()+"";
        temp.phone = phone.getText().toString()+"";
        temp.realname = realname.getText().toString()+"";
        temp.slogan = slogan.getText().toString()+"";
        return temp;
    }
}
