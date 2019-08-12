package com.demo.controller;

import com.demo.models.AccountModel;
import com.demo.models.UserModel;

public class AccountController extends DefaultController<AccountModel>{
	public static final String DB_TABLE = "account";//�޸��� 1�����ݿ������
	public static final String HTML_KEY = "account";//�޸���2��ҳ��ؼ���
	
	@Override
	public void setData() {
		modelClass = AccountModel.class;
		tableName = DB_TABLE;
		htmlKey = HTML_KEY;
		entityDao = AccountModel.dao;
	}
}
