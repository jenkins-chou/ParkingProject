package com.demo.controller;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.demo.models.AccountModel;
import com.demo.models.CouponUserModel;
import com.demo.models.UserModel;
import com.demo.utils.CheckUtils;
import com.demo.utils.Const;

public class AccountController extends DefaultController<AccountModel>{
	public static final String DB_TABLE = "account";//修改项 1：数据库表名称
	public static final String HTML_KEY = "account";//修改项2：页面关键字
	
	@Override
	public void setData() {
		modelClass = AccountModel.class;
		tableName = DB_TABLE;
		htmlKey = HTML_KEY;
		entityDao = AccountModel.dao;
	}
	
	public void getBalanceAndCoupon(){
		setData();
		String userId = getPara(Const.KEY_DB_USER_ID);
		try {
			
			List<AccountModel> accountModelList = entityDao.find(AccountModel.getQueryAllByUserId(userId));
			List<CouponUserModel> couponUserModelList = CouponUserModel.dao.find(CouponUserModel.getQueryAllByUserId(userId));

			JSONObject js = new JSONObject();
			js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_200);
			
			JSONObject modelJson = new JSONObject();
			if(CheckUtils.checkArrayIsNotNull(accountModelList)){
				modelJson.put("balance", accountModelList.get(0).get("balance")+"");
			}else{
				modelJson.put("balance", "0");
			}
			
			if(CheckUtils.checkArrayIsNotNull(couponUserModelList)){
				modelJson.put("coupon", couponUserModelList.size()+"");
			}else{
				modelJson.put("coupon", "0");
			}
			js.put(Const.KEY_RES_MODEL_JSON, modelJson.toString());
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
