package com.example.parkingandroid.intent;

import android.content.Context;
import android.content.Intent;

import com.example.parkingandroid.activity.business.AccountBalanceActivity;
import com.example.parkingandroid.activity.business.AccountPayPwdActivity;
import com.example.parkingandroid.activity.business.AppointmentOrderActivity;
import com.example.parkingandroid.activity.business.BindBankCardActivity;
import com.example.parkingandroid.activity.business.CarActivity;
import com.example.parkingandroid.activity.business.ConsumeRecordActivity;
import com.example.parkingandroid.activity.business.CouponListUserActivity;
import com.example.parkingandroid.activity.business.CouponStoreActivity;
import com.example.parkingandroid.activity.business.DriverActivity;
import com.example.parkingandroid.activity.business.LoginActivity;
import com.example.parkingandroid.activity.business.ParkingLotDetailActivity;
import com.example.parkingandroid.activity.business.ParkingLotListActivity;
import com.example.parkingandroid.activity.business.RechargeActivity;
import com.example.parkingandroid.activity.business.RechargeRecordActivity;
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

    /**
     * 跳转到用户的优惠券列表
     * @param context
     */
    public static void intentToCouponListUser(Context context){
        Intent intent = new Intent(context,CouponListUserActivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳转到用户余额页面
     * @param context
     */
    public static void intentToBalance(Context context){
        Intent intent = new Intent(context,AccountBalanceActivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳转到充值页面
     * @param context
     */
    public static void intentToRecharge(Context context){
        Intent intent = new Intent(context,RechargeActivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳转到银行卡绑定界面
     * @param context
     */
    public static void intentToBindBankcard(Context context){
        Intent intent = new Intent(context,BindBankCardActivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳转到充值记录界面
     * @param context
     */
    public static void intentToRechargeRecord(Context context){
        Intent intent = new Intent(context,RechargeRecordActivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳转到车主信息界面
     * @param context
     */
    public static void intentToDriver(Context context){
        Intent intent = new Intent(context,DriverActivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳转到车主信息界面
     * @param context
     */
    public static void intentToCar(Context context){
        Intent intent = new Intent(context,CarActivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳转到消费记录
     * @param context
     */
    public static void intentToConsumeRecord(Context context){
        Intent intent = new Intent(context,ConsumeRecordActivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳转到优惠券商店
     * @param context
     */
    public static void intentToCouponStore(Context context){
        Intent intent = new Intent(context,CouponStoreActivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳转到停车场列表
     * @param context
     */
    public static void intentToParkingLotList(Context context){
        Intent intent = new Intent(context,ParkingLotListActivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳转到停车场详情
     * @param context
     */
    public static void intentToParkingLotDetail(Context context,String key,String value){
        Intent intent = new Intent(context,ParkingLotDetailActivity.class);
        intent.putExtra(key,value);
        context.startActivity(intent);
    }

    /**
     * 跳转到支付密码设置界面
     * @param context
     */
    public static void intentToAccountPayPwd(Context context){
        Intent intent = new Intent(context,AccountPayPwdActivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳转到我的预约订单界面
     * @param context
     */
    public static void intentToAppointmentOrder(Context context){
        Intent intent = new Intent(context,AppointmentOrderActivity.class);
        context.startActivity(intent);
    }
}
