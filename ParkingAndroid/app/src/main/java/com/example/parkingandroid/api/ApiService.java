package com.example.parkingandroid.api;

import com.example.parkingandroid.models.base.ResultModel;
import com.example.parkingandroid.models.business.CarModel;
import com.example.parkingandroid.models.business.CouponModel;
import com.example.parkingandroid.models.business.DriverModel;
import com.example.parkingandroid.models.business.UserModel;
import com.example.parkingandroid.models.business.account.AccountRechargeModel;
import com.example.parkingandroid.models.business.account.BalanceAndCoupon;
import com.example.parkingandroid.models.business.account.BankCardModel;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by zhouzhenjian on 2018/3/26.
 */

public interface ApiService {

    //模板接口
    @FormUrlEncoded
    @POST("user/login")
    Observable<ResultModel> template(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("user/loginMobile")
    Observable<ResultModel<UserModel>> login(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("user/registerMobile")
    Observable<ResultModel<UserModel>> addUser(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("user/deleteEntity")
    Observable<ResultModel<UserModel>> deleteUser(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("user/updateEntity")
    Observable<ResultModel<UserModel>> updateUser(@FieldMap Map<String, String> body);

    //account
    @FormUrlEncoded
    @POST("account/getBalanceAndCoupon")
    Observable<ResultModel<BalanceAndCoupon>> getBalanceAndCoupon(@FieldMap Map<String, String> body);

    //充值
    @FormUrlEncoded
    @POST("account_recharge/recharge")
    Observable<ResultModel> recharge(@FieldMap Map<String, String> body);

    //充值记录
    @FormUrlEncoded
    @POST("account_recharge/getAllEntityByUserId")
    Observable<ResultModel<AccountRechargeModel>> getRechargeRecord(@FieldMap Map<String, String> body);

    //获取用户所有银行卡
    @FormUrlEncoded
    @POST("bankcard/getAllEntityByUserId")
    Observable<ResultModel<BankCardModel>> getBankCard(@FieldMap Map<String, String> body);

    //绑定银行卡
    @FormUrlEncoded
    @POST("bankcard/addEntity")
    Observable<ResultModel> bindBankCard(@FieldMap Map<String, String> body);

    //重置银行卡
    @FormUrlEncoded
    @POST("bankcard/deleteEntity")
    Observable<ResultModel> deleteBankCard(@FieldMap Map<String, String> body);

    //购买商店所有的优惠券
    @FormUrlEncoded
    @POST("coupon_user/buyCoupon")
    Observable<ResultModel<CouponModel>> buyCoupon(@FieldMap Map<String, String> body);

    //获取个人所有的优惠券
    @FormUrlEncoded
    @POST("coupon_user/getAllEntityByUserId")
    Observable<ResultModel<CouponModel>> getCouponListByUserId(@FieldMap Map<String, String> body);

    //获取商店所有的优惠券
    @FormUrlEncoded
    @POST("coupon_base/getAllEntityMobile")
    Observable<ResultModel<CouponModel>> getAllCouponStoreData(@FieldMap Map<String, String> body);

    //获取个人驾驶证数据
    @FormUrlEncoded
    @POST("driver/getAllEntityByUserId")
    Observable<ResultModel<DriverModel>> getDriver(@FieldMap Map<String, String> body);

    //更新个人驾驶证数据
    @FormUrlEncoded
    @POST("driver/update")
    Observable<ResultModel<DriverModel>> setDriver(@FieldMap Map<String, String> body);

    //获取个人车辆信息
    @FormUrlEncoded
    @POST("car/getAllEntityByUserId")
    Observable<ResultModel<CarModel>> getCar(@FieldMap Map<String, String> body);

    //更新个人车辆信息
    @FormUrlEncoded
    @POST("car/update")
    Observable<ResultModel<CarModel>> setCar(@FieldMap Map<String, String> body);

    //获取个人消费记录
    @FormUrlEncoded
    @POST("consume_record/getAllEntityByUserId")
    Observable<ResultModel> getConsumeRecord(@FieldMap Map<String, String> body);

    //获取所有停车场
    @FormUrlEncoded
    @POST("parking_lot/getAllEntityMobile")
    Observable<ResultModel> getAllParkingLot(@FieldMap Map<String, String> body);

    //预约停车场
    @FormUrlEncoded
    @POST("parking_lot/addAppointment")
    Observable<ResultModel> addAppointment(@FieldMap Map<String, String> body);
}
