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
public class AAAPresenterTemplate {

    private Context context;

    private BaseCallBack view;

    private OnCallBack onCallBack;

    public AAAPresenterTemplate(Context context){
        this.context = context;
        this.view = view;
    }

    public void setOnCallBack(OnCallBack onCallBack){
        this.onCallBack = onCallBack;
    }

    //带参基本请求方法
    public void request(Map<String,String> params){
        if (params==null)return;
        Log.e("开始请求","p-->"+params.toString());
        new ApiUtil(context)
                .getServer(ApiService.class)
                //记得更改请求接口数据
                .template(params)
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
                            onCallBack.callback(true,resultModel);
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        System.out.print("----error");
                        e.printStackTrace();
                        if (onCallBack!=null){
                            onCallBack.callback(false,e);
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
        void failed(Object object);
    }

}
