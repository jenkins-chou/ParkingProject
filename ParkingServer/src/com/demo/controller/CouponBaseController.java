package com.demo.controller;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.demo.models.AccountModel;
import com.demo.models.CarModel;
import com.demo.models.CouponBaseModel;
import com.demo.models.UserModel;
import com.demo.utils.Const;
import com.demo.utils.PageJson;
import com.demo.utils.ParamUtil;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Page;

public class CouponBaseController extends DefaultController<CouponBaseModel>{
	public static final String DB_TABLE = "coupon_base";//�޸��� 1�����ݿ������
	public static final String HTML_KEY = "coupon_base";//�޸���2��ҳ��ؼ���
	
	@Override
	public void setData() {
		modelClass = CouponBaseModel.class;
		tableName = DB_TABLE;
		htmlKey = HTML_KEY;
		entityDao = CouponBaseModel.dao;
	}
	
	public void getAllEntityMobile() {
		setData();
		List<CouponBaseModel> baseModels = entityDao.find("select * from "+tableName+" where del != 'delete'");
		System.out.println(baseModels.toString());
		JSONObject js = new JSONObject();
		js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_200);
		js.put(Const.KEY_RES_DATA, baseModels);
		renderJson(js);
	}
}
