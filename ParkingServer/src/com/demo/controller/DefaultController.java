package com.demo.controller;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.demo.interfaces.ControllerInterface;
import com.demo.models.CouponBaseModel;
import com.demo.models.UserModel;
import com.demo.utils.Const;
import com.demo.utils.Log;
import com.demo.utils.PageJson;
import com.demo.utils.ParamUtil;
import com.demo.utils.RecordJson;
import com.demo.utils.StatusJson;
import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public abstract class DefaultController<T extends Model> extends Controller {

	public ControllerInterface controllerInterface;
	
//	public void setControllerInterface(ControllerInterface controllerInterface){
//		this.controllerInterface = controllerInterface;
//	}
	public Class<T> modelClass;
	public String tableName;
	public String htmlKey;
	public T entityDao;
	
	public void getEntityById(){
		setData();
		T model = (T) entityDao.findById(getPara(Const.KEY_DB_ID));
		renderJson(JsonKit.toJson(new RecordJson(Const.KEY_RES_CODE_200, Const.OPTION_SUCCESS, model)));
	}
	
	public void getAllEntity() {
		setData();
		ParamUtil param = new ParamUtil(getRequest());
		Page<T> page = entityDao.paginate(param.getPageNumber(),
				param.getPageSize(), "select * ", "from "+tableName+" where del != 'delete'");
		System.out.println(page.getList().toString());
		renderJson(JsonKit.toJson(new PageJson<T>("0", "", page)));
	}
	
	public void getAllEntityByUserId() {
		setData();
		String sql = "select * from "+tableName+" where user_id = '"+getPara(Const.KEY_DB_USER_ID)+"' and del != 'delete';";
		Log.i(sql);
		List<T> list = entityDao.find(sql);
		JSONObject js = new JSONObject();
		js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_200);
		js.put(Const.KEY_RES_DATA, list);
		renderJson(js);
	}
	
	public void addEntity(){
		setData();
		try {
			T model = (T) getModel(modelClass, "", true);
			model.set(Const.KEY_DB_CREATE_TIME, System.currentTimeMillis()/1000+"");
			model.set(Const.KEY_DB_DEL, "normal");
			System.out.println("model:"+model);
			model.save();
			JSONObject js = new JSONObject();
			js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_200);
			renderJson(js.toJSONString());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
			JSONObject js = new JSONObject();
			js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_500);
			js.put(Const.KEY_RES_MESSAGE, e.toString());
			renderJson(js.toJSONString());
		}
	}

	
	public void deleteEntity(){
		setData();
		try {
			T model = (T) getModel(modelClass, "", true);
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
	
	public void deleteSelectEntity(){
		setData();
		try {
			String[] ids = getParaValues(Const.KEY_DB_ID);
			for (String id : ids) {
				T model = (T) entityDao.findById(id);
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
	public void updateEntity(){
		setData();
		try {
			T model = (T) getModel(modelClass, "", true);
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
		setData();
		Log.i("htmlKey:"+htmlKey);
		setAttr(Const.KEY_HTML, htmlKey);
		render("list.html");
	}
	
	//显示添加页
	public void showHtmlAdd() {
		setData();
		Log.i("htmlKey:"+htmlKey);
		setAttr(Const.KEY_HTML, htmlKey);
		render("add.html");
	}
	
	//显示修改页
	public void showHtmlModify() {
		setData();
		Log.i("htmlKey:"+htmlKey);
		setAttr(Const.KEY_HTML, htmlKey);
		setAttr(Const.KEY_DB_ID, getPara(Const.KEY_DB_ID));
		render("add.html");
	}
	
	public void setData() {
	}
}
