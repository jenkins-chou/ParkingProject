package com.demo.controller;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.demo.models.AccountModel;
import com.demo.models.CarModel;
import com.demo.models.CouponBaseModel;
import com.demo.models.CouponUserModel;
import com.demo.models.UserModel;
import com.demo.utils.Const;
import com.demo.utils.Log;
import com.demo.utils.PageJson;
import com.demo.utils.ParamUtil;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Page;

public class CouponUserController extends DefaultController<CouponUserModel>{
	public static final String DB_TABLE = "coupon_user";//修改项 1：数据库表名称
	public static final String HTML_KEY = "coupon_user";//修改项2：页面关键字
	
	@Override
	public void setData() {
		modelClass = CouponUserModel.class;
		tableName = DB_TABLE;
		htmlKey = HTML_KEY;
		entityDao = CouponUserModel.dao;
	}
	
	public void getAllEntityByUserId() {
		setData();
		String sql = "select a.* from coupon_base a,coupon_user b where a.id = b.coupon_id and b.user_id = '"+getPara(Const.KEY_DB_USER_ID)+"' and b.del != 'delete' and a.del != 'delete'";
		Log.i(sql);
		List<CouponBaseModel> list = CouponBaseModel.dao.find(sql);
		JSONObject js = new JSONObject();
		js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_200);
		js.put(Const.KEY_RES_DATA, list);
		renderJson(js);
	}
}
