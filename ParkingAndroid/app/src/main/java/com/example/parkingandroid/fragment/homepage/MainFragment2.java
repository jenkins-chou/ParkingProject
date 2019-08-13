package com.example.parkingandroid.fragment.homepage;

import android.view.View;
import android.widget.RelativeLayout;

import com.example.parkingandroid.R;
import com.example.parkingandroid.fragment.business.BaseFragment;
import com.example.parkingandroid.intent.IntentManager;
import com.example.parkingandroid.models.business.UserModel;
import com.example.parkingandroid.tools.AccountTool;

import butterknife.BindView;
import butterknife.OnClick;

public class MainFragment2 extends BaseFragment {
    @BindView(R.id.login_tips_content)
    RelativeLayout loginTipsContent;

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
        if (AccountTool.isLogin(context)){
            UserModel userModel = AccountTool.getLoginUser(context);
            if (userModel!=null){
                loginTipsContent.setVisibility(View.GONE);
            }
        }else{
            loginTipsContent.setVisibility(View.VISIBLE);
        }
    }
}
