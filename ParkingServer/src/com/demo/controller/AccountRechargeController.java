package com.demo.controller;

import com.demo.models.AccountModel;
import com.demo.models.AccountRechargeModel;
import com.demo.models.UserModel;

public class AccountRechargeController extends DefaultController<AccountRechargeModel>{
	public static final String DB_TABLE = "account_recharge";//�޸��� 1�����ݿ������
	public static final String HTML_KEY = "account_recharge";//�޸���2��ҳ��ؼ���
	
	@Override
	public void setData() {
		modelClass = AccountRechargeModel.class;
		tableName = DB_TABLE;
		htmlKey = HTML_KEY;
		entityDao = AccountRechargeModel.dao;
	}
}
