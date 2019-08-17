package com.demo.controller;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.demo.models.AccountModel;
import com.demo.models.BankCardModel;
import com.demo.models.CouponBaseModel;
import com.demo.utils.Const;
import com.demo.utils.Log;

public class BankCardController extends DefaultController{
	public static final String DB_TABLE = "bankcard";//修改项 1：数据库表名称
	public static final String HTML_KEY = "bankcard";//修改项2：页面关键字
	
	@Override
	public void setData() {
		modelClass = BankCardModel.class;
		tableName = DB_TABLE;
		htmlKey = HTML_KEY;
		entityDao = BankCardModel.dao;
	}
	
}
