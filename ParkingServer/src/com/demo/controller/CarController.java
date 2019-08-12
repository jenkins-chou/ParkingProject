package com.demo.controller;

import com.demo.models.AccountModel;
import com.demo.models.CarModel;
import com.demo.models.UserModel;

public class CarController extends DefaultController<CarModel>{
	public static final String DB_TABLE = "car";//修改项 1：数据库表名称
	public static final String HTML_KEY = "car";//修改项2：页面关键字
	
	@Override
	public void setData() {
		modelClass = CarModel.class;
		tableName = DB_TABLE;
		htmlKey = HTML_KEY;
		entityDao = CarModel.dao;
	}
}
