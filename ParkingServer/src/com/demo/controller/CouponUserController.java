package com.demo.controller;

import com.demo.models.AccountModel;
import com.demo.models.CarModel;
import com.demo.models.CouponUserModel;
import com.demo.models.UserModel;

public class CouponUserController extends DefaultController<CouponUserModel>{
	public static final String DB_TABLE = "coupon_user";//�޸��� 1�����ݿ������
	public static final String HTML_KEY = "coupon_user";//�޸���2��ҳ��ؼ���
	
	@Override
	public void setData() {
		modelClass = CouponUserModel.class;
		tableName = DB_TABLE;
		htmlKey = HTML_KEY;
		entityDao = CouponUserModel.dao;
	}
}
