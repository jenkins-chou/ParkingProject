package com.example.parkingandroid.api;

import com.example.parkingandroid.models.base.ResultModel;
import com.example.parkingandroid.models.business.UserModel;

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
    @POST("user/deleteUser")
    Observable<ResultModel<UserModel>> deleteUser(@FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("user/updateUser")
    Observable<ResultModel<UserModel>> updateUser(@FieldMap Map<String, String> body);
}
