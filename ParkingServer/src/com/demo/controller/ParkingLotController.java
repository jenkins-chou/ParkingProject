package com.demo.controller;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.demo.models.AccountModel;
import com.demo.models.CarModel;
import com.demo.models.CouponBaseModel;
import com.demo.models.CouponUserModel;
import com.demo.models.DriverModel;
import com.demo.models.MessageModel;
import com.demo.models.ParkingLotAppointmentModel;
import com.demo.models.ParkingLotModel;
import com.demo.models.UserModel;
import com.demo.utils.Const;

public class ParkingLotController extends DefaultController<ParkingLotModel>{
	public static final String DB_TABLE = "parking_lot";//修改项 1：数据库表名称
	public static final String HTML_KEY = "parking_lot";//修改项2：页面关键字
	
	@Override
	public void setData() {
		modelClass = ParkingLotModel.class;
		tableName = DB_TABLE;
		htmlKey = HTML_KEY;
		entityDao = ParkingLotModel.dao;
	}
	
	public void addEntity(){
		setData();
		try {
			ParkingLotModel model = getModel(modelClass, "", true);
			model.set(Const.KEY_DB_CREATE_TIME, System.currentTimeMillis()/1000+"");
			model.set(Const.KEY_DB_DEL, "normal");
			model.set("img","upload/default_parking_img.jpg");
			System.out.println("model:"+model);
			model.save();
			JSONObject js = new JSONObject();
			js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_200);
			renderJson(js.toJSONString());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
			JSONObject js = new JSONObject();
			js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_500);
			js.put(Const.KEY_RES_MESSAGE, e.toString());
			renderJson(js.toJSONString());
		}
	}
	
	public void getAllEntityMobile() {
		setData();
		List<ParkingLotModel> baseModels = entityDao.find("select * from "+tableName+" where del != 'delete'");
		System.out.println(baseModels.toString());
		JSONObject js = new JSONObject();
		js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_200);
		js.put(Const.KEY_RES_DATA, baseModels);
		renderJson(js);
	}
	
	//预约
	public void addAppointment(){
		try {
			ParkingLotAppointmentModel model = getModel(ParkingLotAppointmentModel.class, "", true);
			model.set(Const.KEY_DB_CREATE_TIME, System.currentTimeMillis()/1000+"");
			model.set(Const.KEY_DB_DEL, "normal");
			System.out.println("model:"+model);
			model.save();
			JSONObject js = new JSONObject();
			js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_200);
			renderJson(js.toJSONString());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
			JSONObject js = new JSONObject();
			js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_500);
			js.put(Const.KEY_RES_MESSAGE, e.toString());
			renderJson(js.toJSONString());
		}
	}
}
