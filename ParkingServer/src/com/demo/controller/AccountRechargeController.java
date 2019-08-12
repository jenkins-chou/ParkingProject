package com.demo.controller;

import com.demo.models.AccountModel;
import com.demo.models.AccountRechargeModel;
import com.demo.models.UserModel;

public class AccountRechargeController extends DefaultController<AccountRechargeModel>{
	public static final String DB_TABLE = "account_recharge";//修改项 1：数据库表名称
	public static final String HTML_KEY = "account_recharge";//修改项2：页面关键字
	
	@Override
	public void setData() {
		modelClass = AccountRechargeModel.class;
		tableName = DB_TABLE;
		htmlKey = HTML_KEY;
		entityDao = AccountRechargeModel.dao;
	}
}
