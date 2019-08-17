package com.demo.controller;

import java.util.List;

import com.demo.models.AccountModel;
import com.demo.models.CarModel;
import com.demo.models.CouponUserModel;
import com.demo.models.DriverModel;
import com.demo.models.UserModel;
import com.demo.utils.CheckUtils;
import com.demo.utils.Const;

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
	
	public void update(){
		setData();
		String userId = getPara(Const.KEY_DB_USER_ID);
		String sql = "select * from "+DB_TABLE+" where user_id = '"+userId+"' and del != 'delete'";
		List<DriverModel> models = entityDao.find(sql);
		if(CheckUtils.checkArrayIsNotNull(models)){
			updateEntity();
		}else{
			addEntity();
		}
	}
}
