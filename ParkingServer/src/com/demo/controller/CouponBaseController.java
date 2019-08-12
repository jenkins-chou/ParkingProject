package com.demo.controller;

import com.demo.models.AccountModel;
import com.demo.models.CarModel;
import com.demo.models.CouponBaseModel;
import com.demo.models.UserModel;

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
}
