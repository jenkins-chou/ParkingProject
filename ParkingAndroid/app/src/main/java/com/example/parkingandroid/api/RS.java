package com.example.parkingandroid.api;

import android.content.Context;
import android.util.Log;

import com.example.parkingandroid.tools.AccountTool;

import java.util.HashMap;
import java.util.Map;

//基本请求参数类
public class RS {

    /**
     * 获取基本请求参数
     * @param context
     * @return Map<String,String>  enum:token,device
     */
    public static Map<String,String> getBaseParams(Context context){
        Map<String,String> params = new HashMap<>();
        if (AccountTool.getLoginUser(context) != null){
            Log.e("getBaseParams","已登录");
            params.put("user_id",AccountTool.getLoginUser(context).getId());
        }else{
            Log.e("getBaseParams","未登录");
        }
        return params;
    }
}
