package com.demo.controller;

import com.demo.models.AccountModel;
import com.demo.models.CarModel;
import com.demo.models.CouponUserModel;
import com.demo.models.DriverModel;
import com.demo.models.MessageModel;
import com.demo.models.UserModel;

public class MessageController extends DefaultController<MessageModel>{
	public static final String DB_TABLE = "message";//修改项 1：数据库表名称
	public static final String HTML_KEY = "message";//修改项2：页面关键字
	
	@Override
	public void setData() {
		modelClass = MessageModel.class;
		tableName = DB_TABLE;
		htmlKey = HTML_KEY;
		entityDao = MessageModel.dao;
	}
}
