package com.example.parkingandroid.api;

import com.example.parkingandroid.models.base.ResultModel;
import com.example.parkingandroid.models.business.CouponModel;
import com.example.parkingandroid.models.business.UserModel;
import com.example.parkingandroid.models.business.account.BalanceAndCoupon;

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

    //获取用户所有银行卡
    @FormUrlEncoded
    @POST("bankcard/getAllEntityByUserId")
    Observable<ResultModel<BalanceAndCoupon>> getBankCard(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("coupon_user/getAllEntityByUserId")
    Observable<ResultModel<CouponModel>> getCouponListByUserId(@FieldMap Map<String, String> body);
}
