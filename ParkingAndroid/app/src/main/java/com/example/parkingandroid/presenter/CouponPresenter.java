package com.example.parkingandroid.presenter;

import android.content.Context;
import android.util.Log;

import com.example.parkingandroid.api.ApiService;
import com.example.parkingandroid.api.ApiUtil;
import com.example.parkingandroid.constants.BaseCallBack;
import com.example.parkingandroid.models.base.ResultModel;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * presenter模板
 */
public class CouponPresenter {

    private Context context;

    private BaseCallBack view;

    private OnCallBack onCallBack;

    public CouponPresenter(Context context){
        this.context = context;
        this.view = view;
    }

    public void setOnCallBack(OnCallBack onCallBack){
        this.onCallBack = onCallBack;
    }

    //获取个人的优惠券列表
    public void getCouponListByUserId(Map<String,String> params){
        if (params==null)return;
        Log.e("开始请求","p-->"+params.toString());
        new ApiUtil(context)
                .getServer(ApiService.class)
                //记得更改请求接口数据
                .getCouponListByUserId(params)
                .subscribeOn(Schedulers.io())//后台处理线程
                .observeOn(AndroidSchedulers.mainThread())//指定回调发生的线程
                .subscribe(new Observer<ResultModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.print(d);
                    }

                    @Override
                    public void onNext(ResultModel resultModel) {
                        //更新视图
                        if (onCallBack!=null){
                            onCallBack.getCouponListByUserId(true,resultModel);
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        System.out.print("----error");
                        e.printStackTrace();
                        if (onCallBack!=null){
                            onCallBack.getCouponListByUserId(false,e);
                        }
                        //view.failed(e);
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }

    //获取商店里所有优惠券
    public void getAllCouponStoreData(Map<String,String> params){
        if (params==null)return;
        Log.e("开始请求","p-->"+params.toString());
        new ApiUtil(context)
                .getServer(ApiService.class)
                //记得更改请求接口数据
                .getAllCouponStoreData(params)
                .subscribeOn(Schedulers.io())//后台处理线程
                .observeOn(AndroidSchedulers.mainThread())//指定回调发生的线程
                .subscribe(new Observer<ResultModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.print(d);
                    }

                    @Override
                    public void onNext(ResultModel resultModel) {
                        //更新视图
                        if (onCallBack!=null){
                            onCallBack.getAllCouponStoreData(true,resultModel);
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        System.out.print("----error");
                        e.printStackTrace();
                        if (onCallBack!=null){
                            onCallBack.getAllCouponStoreData(false,e);
                        }
                        //view.failed(e);
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }

    //购买优惠券
    public void buyCoupon(Map<String,String> params){
        if (params==null)return;
        Log.e("开始请求","p-->"+params.toString());
        new ApiUtil(context)
                .getServer(ApiService.class)
                //记得更改请求接口数据
                .buyCoupon(params)
                .subscribeOn(Schedulers.io())//后台处理线程
                .observeOn(AndroidSchedulers.mainThread())//指定回调发生的线程
                .subscribe(new Observer<ResultModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.print(d);
                    }

                    @Override
                    public void onNext(ResultModel resultModel) {
                        //更新视图
                        if (onCallBack!=null){
                            onCallBack.buyCoupon(true,resultModel);
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        System.out.print("----error");
                        e.printStackTrace();
                        if (onCallBack!=null){
                            onCallBack.buyCoupon(false,e);
                        }
                        //view.failed(e);
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }

    public interface OnCallBack{
        void callback(boolean isSuccess, Object object);
        void getCouponListByUserId(boolean isSuccess, Object object);
        void getAllCouponStoreData(boolean isSuccess, Object object);
        void buyCoupon(boolean isSuccess, Object object);
        void failed(Object object);
    }

}
