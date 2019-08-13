package com.example.parkingandroid.tools;

import android.content.Context;

import com.example.parkingandroid.models.business.UserModel;
import com.google.gson.Gson;

public class AccountTool {
    //判断用户是否登录
    public final static boolean isLogin(Context context){
        if (context==null)return false;
        UserModel userModel = new Gson().fromJson((String) SPUtils.get(context,SPUtils.FILE_USER,SPUtils.user_object,""),UserModel.class);
        if (userModel!=null&&StringUtil.isNotEmpty(userModel.getId())){
            return true;
        }else{
            return false;
        }
    }

    //保存用户信息
    public final static void saveUser(Context context,Object userObject){
        if (context==null)return;
        String userModelJson = new Gson().toJson(userObject);
        SPUtils.put(context,SPUtils.FILE_USER,SPUtils.user_object,userModelJson);
    }

    //获取用户类型
    public final static String getUserType(Context context){
        if (context==null)return null;
        if (!isLogin(context))return null;
        UserModel userModel = new Gson().fromJson((String) SPUtils.get(context,SPUtils.FILE_USER,SPUtils.user_object,""),UserModel.class);
        return userModel.getType();
    }


    //获取登录用户的全部信息
    public final static UserModel getLoginUser(Context context){
        if (context==null)return null;
        if (!isLogin(context))return null;
        UserModel userModel = new Gson().fromJson((String) SPUtils.get(context,SPUtils.FILE_USER,SPUtils.user_object,""),UserModel.class);
        return userModel;
    }

    public final static void reSaveUser(Context context,UserModel tempModel){
        UserModel localModel = getLoginUser(context);
        if (tempModel != null && localModel != null){
            localModel.name = tempModel.name;
            localModel.sex = tempModel.sex;
            localModel.nation = tempModel.nation;
            localModel.age = tempModel.age;
            localModel.email = tempModel.email;
            localModel.phone = tempModel.phone;
            localModel.realname = tempModel.realname;
            localModel.slogan = tempModel.slogan;
            saveUser(context,localModel);
        }
    }

    //退出登录
    public final static void logout(Context context){
        if (context==null)return;
        SPUtils.clear(context,SPUtils.FILE_USER);
    }
}
