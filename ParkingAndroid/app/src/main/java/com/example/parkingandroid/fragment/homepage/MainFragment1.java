package com.example.parkingandroid.fragment.homepage;

import android.view.View;
import android.widget.RelativeLayout;

import com.example.parkingandroid.R;
import com.example.parkingandroid.dialog.CommonTipsDialog;
import com.example.parkingandroid.fragment.business.BaseFragment;
import com.example.parkingandroid.intent.IntentManager;
import com.example.parkingandroid.models.business.UserModel;
import com.example.parkingandroid.tools.AccountTool;

import butterknife.BindView;
import butterknife.OnClick;

public class MainFragment1 extends BaseFragment {

    @BindView(R.id.login_tips)
    RelativeLayout loginTipsLayout;

    @OnClick(R.id.login_btn)
    void goLogin(){
        IntentManager.intentToLogin(context);
    }
    @OnClick(R.id.get_parking)
    void get_parking(){
        if (!AccountTool.isLogin(context)){
            CommonTipsDialog.create(context,"温馨提示","请登录后重试,是否跳转登录界面",false)
                    .setOnClickListener(new CommonTipsDialog.OnClickListener() {
                        @Override
                        public void cancel() {

                        }

                        @Override
                        public void confirm() {
                            IntentManager.intentToLogin(context);
                        }
                    }).show();
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_main_one;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (AccountTool.isLogin(context)){
            UserModel userModel = AccountTool.getLoginUser(context);
            if (userModel!=null){
                loginTipsLayout.setVisibility(View.GONE);
            }
        }else{
            loginTipsLayout.setVisibility(View.VISIBLE);
        }
    }
}
