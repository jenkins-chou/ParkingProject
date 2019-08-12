package com.demo.controller;

import com.demo.models.AccountModel;
import com.demo.models.CarModel;
import com.demo.models.CouponBaseModel;
import com.demo.models.UserModel;

public class CouponBaseController extends DefaultController<CouponBaseModel>{
	public static final String DB_TABLE = "coupon_base";//修改项 1：数据库表名称
	public static final String HTML_KEY = "coupon_base";//修改项2：页面关键字
	
	@Override
	public void setData() {
		modelClass = CouponBaseModel.class;
		tableName = DB_TABLE;
		htmlKey = HTML_KEY;
		entityDao = CouponBaseModel.dao;
	}
}
