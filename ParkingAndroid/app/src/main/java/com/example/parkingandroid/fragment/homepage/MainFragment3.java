package com.example.parkingandroid.fragment.homepage;

import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.parkingandroid.R;
import com.example.parkingandroid.api.BaseAPI;
import com.example.parkingandroid.fragment.business.BaseFragment;
import com.example.parkingandroid.intent.IntentManager;
import com.example.parkingandroid.models.business.UserModel;
import com.example.parkingandroid.tools.AccountTool;

import butterknife.BindView;
import butterknife.OnClick;

public class MainFragment3 extends BaseFragment {

    @BindView(R.id.user_avatar)
    ImageView userAvatar;
    @BindView(R.id.user_name)
    TextView userName;
    @OnClick(R.id.user_bar)
    void user_bar(){
        if (!AccountTool.isLogin(context)){
            IntentManager.intentToLogin(context);
        }
    }

    @OnClick(R.id.setting)
    void setting(){
        IntentManager.intentToSetting(context);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_main_three;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (AccountTool.isLogin(context)){
            UserModel userModel = AccountTool.getLoginUser(context);
            if (userModel!=null){
                //Log.e("getLoginUser",userModel.toString());
                showUserInfo(userModel);
            }
        }else{
            resetUserInfo();
        }
    }

    public void showUserInfo(UserModel userModel){
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.circleCrop();
        requestOptions.placeholder(R.mipmap.avatar_default);
        requestOptions.error(R.mipmap.avatar_default);
        Glide.with(context).load(BaseAPI.base_url+userModel.getAvatar()).apply(requestOptions).into(userAvatar);
        userName.setText(userModel.getName());
    }

    public void resetUserInfo(){
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.circleCrop();
        requestOptions.placeholder(R.mipmap.avatar_default);
        requestOptions.error(R.mipmap.avatar_default);
        Glide.with(context).load(R.mipmap.avatar_default).apply(requestOptions).into(userAvatar);
        userName.setText("请登录");
    }
}
