package com.example.parkingandroid.app;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.example.parkingandroid.tools.GlobalExcaption;

import org.xutils.x;

public class MyApp extends Application {
    public static MyApp appOS;
    @Override
    public void onCreate() {
        super.onCreate();
        appOS = this;
        GlobalExcaption.getInstance().init();//初始化全局异常拦截
        //文件上传
        x.Ext.init(this);
        //x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}