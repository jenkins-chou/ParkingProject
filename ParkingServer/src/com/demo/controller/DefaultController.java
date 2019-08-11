package com.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.demo.models.UserModel;
import com.demo.utils.Const;
import com.demo.utils.PageJson;
import com.demo.utils.ParamUtil;
import com.demo.utils.RecordJson;
import com.demo.utils.StatusJson;
import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public abstract class DefaultController<T extends Model> extends Controller {

	public abstract T getDao();
	public abstract Class<T> getModelClass();
	public abstract String getTableName();
	public abstract String getHtmlKey();
	
	public void getEntityById(){
		T model = (T) getDao().findById(getPara(Const.KEY_DB_ID));
		renderJson(JsonKit.toJson(new RecordJson(Const.KEY_RES_CODE_200, Const.OPTION_SUCCESS, model)));
	}
	
	public void getAllUser() {
		ParamUtil param = new ParamUtil(getRequest());
		Page<T> page = getDao().paginate(param.getPageNumber(),
				param.getPageSize(), "select * ", "from "+getTableName()+" where del != 'delete'");
		System.out.println(page.getList().toString());
		renderJson(JsonKit.toJson(new PageJson<T>("0", "", page)));
	}
	
	public void addEntity(){
		try {
			T model = getModel(getModelClass(), "", true);
			model.set(Const.KEY_DB_CREATE_TIME, System.currentTimeMillis()/1000+"");
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
		try {
			T model = getModel(getModelClass(), "", true);
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
		try {
			String[] ids = getParaValues(Const.KEY_DB_ID);
			for (String id : ids) {
				T model = (T) getDao().findById(id);
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
		try {
			T model = getModel(getModelClass(), "", true);
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
		setAttr(Const.KEY_HTML, getHtmlKey());
		render("list_"+getHtmlKey()+".html");
	}
	
	//显示添加页
	public void showHtmlAdd() {
		setAttr(Const.KEY_HTML, getHtmlKey());
		render("add_"+getHtmlKey()+".html");
	}
	
	//显示修改页
	public void showHtmlModify() {
		setAttr(Const.KEY_HTML, getHtmlKey());
		setAttr(Const.KEY_DB_ID, getPara(Const.KEY_DB_ID));
		render("add_"+getHtmlKey()+".html");
	}
}
