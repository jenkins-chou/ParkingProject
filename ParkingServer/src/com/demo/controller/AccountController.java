package com.demo.controller;

import com.demo.models.AccountModel;
import com.demo.models.UserModel;

public class AccountController extends DefaultController<AccountModel>{
	public static final String DB_TABLE = "account";//修改项 1：数据库表名称
	public static final String HTML_KEY = "account";//修改项2：页面关键字
	
	@Override
	public void setData() {
		modelClass = AccountModel.class;
		tableName = DB_TABLE;
		htmlKey = HTML_KEY;
		entityDao = AccountModel.dao;
	}
}
