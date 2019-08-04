package com.demo.controller;

import java.io.File;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
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

public class UserController extends Controller {
	
	public void uploadFileMobile(){
		UploadFile f = getFile();
		renderJson("upload/"+f.getFileName());
		
	}
	public void loginMobile() {
		String useridentify = getPara("useridentify");
		String pass = getPara("pass");
		List<UserModel> userModels = UserModel.dao.find("select * from user_base where useridentify = '"+useridentify+"' and pass = '"+pass+"' and del != 'delete'");
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
		String useridentify = getPara("useridentify");
		String pass = getPara("pass");
		Log.i(useridentify);
		Log.i(pass);
		List<UserModel> userModels = UserModel.dao.find("select * from user_base where useridentify = '"+useridentify+"' and del != 'delete'");
		JSONObject js = new JSONObject();
		if(userModels!=null&&userModels.size()>=1){
			js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_201);
			js.put(Const.KEY_RES_DATA, userModels);
			renderJson(JsonKit.toJson(js));
		}else{
			UserModel model = getModel(UserModel.class, "", true);
			model.set("name",useridentify+StringUtil.getStrTime(System.currentTimeMillis()/1000+""));
			model.set(Const.KEY_DB_CREATE_TIME, System.currentTimeMillis()/1000+"");
			model.set(Const.KEY_DB_DEL, Const.OPTION_DB_NORMAL);
			System.out.println("model:"+model);
			model.save();
			List<UserModel> userModelsResult = UserModel.dao.find("select * from user_base where useridentify = '"+useridentify+"' and del != 'delete'");
			
			js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_200);
			js.put("data", userModelsResult);
			renderJson(JsonKit.toJson(js));
		}
	}
	
	public void getUserById(){
		UserModel model = UserModel.dao.findById(getPara(Const.KEY_DB_ID));
		renderJson(JsonKit.toJson(new RecordJson(Const.KEY_RES_CODE_200, Const.OPTION_SUCCESS, model)));
	}
	
	public void getAllUser() {
		ParamUtil param = new ParamUtil(getRequest());
		Page<UserModel> page = UserModel.dao.paginate(param.getPageNumber(),
				param.getPageSize(), "select * ", "from user_base where del != 'delete'");
		System.out.println(page.getList().toString());
		renderJson(JsonKit.toJson(new PageJson<UserModel>("0", "", page)));
	}
	
	public void addUser(){
		try {
			UserModel model = getModel(UserModel.class, "", true);
			model.set("create_time", System.currentTimeMillis()/1000+"");
			System.out.println("model:"+model);
			model.save();
			JSONObject js = new JSONObject();
			js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_200);
			renderJson(js.toJSONString());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
			JSONObject js = new JSONObject();
			js.put(Const.KEY_RES_CODE, 500);
			js.put(Const.KEY_RES_MESSAGE, e.toString());
			renderJson(js.toJSONString());
		}
	}

	
	public void deleteUser(){
		try {
			UserModel model = getModel(UserModel.class, "", true);
			model.set(Const.KEY_DB_DEL, Const.OPTION_DB_DELETE);
			model.update();
			JSONObject js = new JSONObject();
			js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_200);
			renderJson(js.toJSONString());
		} catch (Exception e) {
			// TODO: handle exception
			JSONObject js = new JSONObject();
			js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_500);
			js.put(Const.KEY_RES_MESSAGE, e.toString());
			renderJson(js.toJSONString());
		}
	}
	
	public void deleteSelectUser(){
		try {
			String[] ids = getParaValues(Const.KEY_DB_ID);
			for (String id : ids) {
				UserModel model = UserModel.dao.findById(id);
				model.set(Const.KEY_DB_DEL, Const.OPTION_DB_DELETE);
				model.update();
			}
			renderJson(JsonKit.toJson(new StatusJson(Const.KEY_RES_CODE_200, Const.OPTION_SUCCESS, true)));
		} catch (Exception e) {
			// TODO: handle exception
			renderJson(JsonKit.toJson(new StatusJson(Const.KEY_RES_CODE_500,Const.OPTION_FAILED, true)));
		}
	}
	
	//更新接口
	public void updateUser(){
		try {
			UserModel model = getModel(UserModel.class, "", true);
			System.out.println(model.toJson());
			model.update();
			JSONObject js = new JSONObject();
			js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_200);
			renderJson(js.toJSONString());
		} catch (Exception e) {
			JSONObject js = new JSONObject();
			js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_500);
			js.put(Const.KEY_RES_MESSAGE, e.toString());
			renderJson(js.toJSONString());
		}
	}
	
	//显示列表
	public void showHtmlList() {
		render("list_user.html");
	}
	
	//显示添加页
	public void showHtmlAdd() {
		render("add_user.html");
	}
	
	//显示修改页
	public void showHtmlModify() {
		setAttr(Const.KEY_DB_ID, getPara(Const.KEY_DB_ID));
		render("add_user.html");
	}
}
