package com.example.parkingandroid.intent;

import android.content.Context;
import android.content.Intent;

import com.example.parkingandroid.activity.business.LoginActivity;
import com.example.parkingandroid.activity.business.SettingActivity;
import com.example.parkingandroid.activity.business.UserInfoAvatarActivity;
import com.example.parkingandroid.activity.business.UserinfoSettingActivity;

public class IntentManager {
    /**
     * 跳转登录
     * @param context
     */
    public static void intentToLogin(Context context){
        Intent intent = new Intent(context,LoginActivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳转到设置
     * @param context
     */
    public static void intentToSetting(Context context){
        Intent intent = new Intent(context,SettingActivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳转到用户信息设置
     * @param context
     */
    public static void intentToUserinfoSetting(Context context){
        Intent intent = new Intent(context,UserinfoSettingActivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳转到用户信息头像
     * @param context
     */
    public static void intentToUserinfoAvatar(Context context){
        Intent intent = new Intent(context,UserInfoAvatarActivity.class);
        context.startActivity(intent);
    }
}
