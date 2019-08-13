package com.demo.controller;

//import java.io.File;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.demo.interfaces.ControllerInterface;
import com.demo.models.UserModel;
import com.demo.utils.Const;
import com.demo.utils.Log;
import com.demo.utils.PageJson;
import com.demo.utils.ParamUtil;
import com.demo.utils.RecordJson;
import com.demo.utils.StatusJson;
import com.demo.utils.StringUtil;
import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.upload.UploadFile;

public class UserController extends DefaultController<UserModel>{
	
	public static final String DB_TABLE = "user_base";//修改项 1：数据库表名称
	public static final String HTML_KEY = "user";//修改项2：页面关键字
	
	@Override
	public void setData() {
		modelClass = UserModel.class;
		tableName = DB_TABLE;
		htmlKey = HTML_KEY;
		entityDao = UserModel.dao;
		
	}
	
	public void uploadFileMobile(){
		
		UploadFile f = getFile();
		renderJson("upload/"+f.getFileName());
		
	}
	
	public void loginMobile() {
		setData();
		String useridentify = getPara("useridentify");
		String pass = getPara("pass");
		List<UserModel> userModels = entityDao.find("select * from "+DB_TABLE+" where useridentify = '"+useridentify+"' and pass = '"+pass+"' and del != 'delete'");
		JSONObject js = new JSONObject();
		if(userModels!=null&&userModels.size()==1){
			js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_200);
			js.put(Const.KEY_RES_DATA, userModels);
			renderJson(JsonKit.toJson(js));
		}else{
			js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_201);
			renderJson(js.toJSONString());
		}
	}
	
	public void registerMobile() {
		setData();
		String useridentify = getPara("useridentify");
		String pass = getPara("pass");
		Log.i(useridentify);
		Log.i(pass);
		List<UserModel> userModels = entityDao.find("select * from "+DB_TABLE+" where useridentify = '"+useridentify+"' and del != 'delete'");
		JSONObject js = new JSONObject();
		if(userModels!=null&&userModels.size()>=1){
			js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_201);
			js.put(Const.KEY_RES_DATA, userModels);
			renderJson(JsonKit.toJson(js));
		}else{
			UserModel model = getModel(modelClass, "", true);
			model.set("name",useridentify+StringUtil.getStrTime(System.currentTimeMillis()/1000+""));
			model.set(Const.KEY_DB_CREATE_TIME, System.currentTimeMillis()/1000+"");
			model.set(Const.KEY_DB_DEL, Const.OPTION_DB_NORMAL);
			System.out.println("model:"+model);
			model.save();
			List<UserModel> userModelsResult = entityDao.find("select * from "+DB_TABLE+" where useridentify = '"+useridentify+"' and del != 'delete'");
			
			js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_200);
			js.put("data", userModelsResult);
			renderJson(JsonKit.toJson(js));
		}
	}


	
}
