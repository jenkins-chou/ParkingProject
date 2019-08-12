package com.demo.controller;

import com.demo.models.AccountModel;
import com.demo.models.CarModel;
import com.demo.models.CouponUserModel;
import com.demo.models.DriverModel;
import com.demo.models.UserModel;

public class DriverController extends DefaultController<DriverModel>{
	public static final String DB_TABLE = "driver";//�޸��� 1�����ݿ������
	public static final String HTML_KEY = "driver";//�޸���2��ҳ��ؼ���
	
	@Override
	public void setData() {
		modelClass = DriverModel.class;
		tableName = DB_TABLE;
		htmlKey = HTML_KEY;
		entityDao = DriverModel.dao;
	}
}
