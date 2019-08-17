package com.demo.controller;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.demo.models.AccountModel;
import com.demo.models.BankCardModel;
import com.demo.models.CouponBaseModel;
import com.demo.utils.Const;
import com.demo.utils.Log;

public class BankCardController extends DefaultController{
	public static final String DB_TABLE = "bankcard";//�޸��� 1�����ݿ������
	public static final String HTML_KEY = "bankcard";//�޸���2��ҳ��ؼ���
	
	@Override
	public void setData() {
		modelClass = BankCardModel.class;
		tableName = DB_TABLE;
		htmlKey = HTML_KEY;
		entityDao = BankCardModel.dao;
	}
	
}
