package com.demo.controller;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.demo.models.AccountModel;
import com.demo.models.CarModel;
import com.demo.models.ConsumeRecordModel;
import com.demo.models.CouponBaseModel;
import com.demo.models.CouponUserModel;
import com.demo.models.DriverModel;
import com.demo.models.MessageModel;
import com.demo.models.ParkingLotAppointmentModel;
import com.demo.models.ParkingLotModel;
import com.demo.models.UserModel;
import com.demo.utils.CheckUtils;
import com.demo.utils.Const;
import com.demo.utils.StringUtil;

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
	
	//����ԤԼ
	public void addAppointment(){
		try {
			String user_id = getPara(Const.KEY_DB_USER_ID);
			String money = getPara("money");
			
			List<AccountModel> accountModels = AccountModel.dao.find("select * from account where user_id = '"+user_id+"' and del != 'delete'");
			if(CheckUtils.checkArrayIsNotNull(accountModels)){
				AccountModel accountModel = accountModels.get(0);
				if(StringUtil.isNumber(money)){
					double preBalance = accountModel.getDouble("balance");
					double couponMoneyDouble = Double.parseDouble(money);
					if(preBalance>couponMoneyDouble){
						
						//������Ѽ�¼
						ConsumeRecordModel consumeModel = new ConsumeRecordModel();
						consumeModel.set("user_id", user_id);
						consumeModel.set("money", money);
						consumeModel.set("address", "online");
						consumeModel.set("status", Const.OPTION_SUCCESS);
						consumeModel.set("duration", "none");
						consumeModel.set("detail", "��λԤԼ��������");
						consumeModel.set("create_time", System.currentTimeMillis()/1000+"");
						consumeModel.set("remark", "none");
						consumeModel.set("del", "normal");
						consumeModel.save();
						
						accountModel.set("balance", preBalance-couponMoneyDouble);
						accountModel.update();
						
						ParkingLotAppointmentModel model = getModel(ParkingLotAppointmentModel.class, "", true);
						model.set(Const.KEY_DB_CREATE_TIME, System.currentTimeMillis()/1000+"");
						model.set(Const.KEY_DB_DEL, "normal");
						System.out.println("model:"+model);
						model.save();
						JSONObject js = new JSONObject();
						js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_200);
						renderJson(js.toJSONString());
						
					}else{
						JSONObject js = new JSONObject();
						js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_201);
						js.put(Const.KEY_RES_MESSAGE, "����0");
						renderJson(js.toJSONString());
					}
				}else{
					JSONObject js = new JSONObject();
					js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_201);
					js.put(Const.KEY_RES_MESSAGE, "����1");
					renderJson(js.toJSONString());
				}
				
			}else{
				JSONObject js = new JSONObject();
				js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_201);
				js.put(Const.KEY_RES_MESSAGE, "����2");
				renderJson(js.toJSONString());
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
			JSONObject js = new JSONObject();
			js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_500);
			js.put(Const.KEY_RES_MESSAGE, e.toString());
			renderJson(js.toJSONString());
		}
	}
	
	//��ȡ�������е�ԤԼ����
	public void getAllAppointmentByUserID(){
		
	}
}
