package com.demo.controller;

import com.demo.models.AccountModel;
import com.demo.models.CarModel;
import com.demo.models.UserModel;

public class CarController extends DefaultController<CarModel>{
	public static final String DB_TABLE = "car";//�޸��� 1�����ݿ������
	public static final String HTML_KEY = "car";//�޸���2��ҳ��ؼ���
	
	@Override
	public void setData() {
		modelClass = CarModel.class;
		tableName = DB_TABLE;
		htmlKey = HTML_KEY;
		entityDao = CarModel.dao;
	}
}
