package com.demo.controller;

import com.demo.models.AccountModel;
import com.demo.models.CarModel;
import com.demo.models.CouponUserModel;
import com.demo.models.DriverModel;
import com.demo.models.MessageModel;
import com.demo.models.ParkingLotModel;
import com.demo.models.UserModel;

public class ParkingLotController extends DefaultController<ParkingLotModel>{
	public static final String DB_TABLE = "parking_lot";//�޸��� 1�����ݿ������
	public static final String HTML_KEY = "parking_lot";//�޸���2��ҳ��ؼ���
	
	@Override
	public void setData() {
		modelClass = ParkingLotModel.class;
		tableName = DB_TABLE;
		htmlKey = HTML_KEY;
		entityDao = ParkingLotModel.dao;
	}
}
