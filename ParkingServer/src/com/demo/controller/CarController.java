package com.demo.controller;

import java.util.List;

import com.demo.models.AccountModel;
import com.demo.models.CarModel;
import com.demo.models.DriverModel;
import com.demo.models.UserModel;
import com.demo.utils.CheckUtils;
import com.demo.utils.Const;

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
	
	public void update(){
		setData();
		String userId = getPara(Const.KEY_DB_USER_ID);
		String sql = "select * from "+DB_TABLE+" where user_id = '"+userId+"' and del != 'delete'";
		List<CarModel> models = entityDao.find(sql);
		if(CheckUtils.checkArrayIsNotNull(models)){
			updateEntity();
		}else{
			addEntity();
		}
	}
	
}
