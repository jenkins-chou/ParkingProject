package com.demo.controller;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.demo.models.AccountModel;
import com.demo.models.AccountRechargeModel;
import com.demo.models.UserModel;
import com.demo.utils.CheckUtils;
import com.demo.utils.Const;
import com.demo.utils.StringUtil;

public class AccountRechargeController extends DefaultController<AccountRechargeModel>{
	public static final String DB_TABLE = "account_recharge";//修改项 1：数据库表名称
	public static final String HTML_KEY = "account_recharge";//修改项2：页面关键字
	
	@Override
	public void setData() {
		modelClass = AccountRechargeModel.class;
		tableName = DB_TABLE;
		htmlKey = HTML_KEY;
		entityDao = AccountRechargeModel.dao;
	}
	
	public void recharge(){
		setData();
		String userId = getPara(Const.KEY_DB_USER_ID);
		String bankcardId = getPara("bankcard_id");
		String money = getPara("money");
		
		AccountRechargeModel model = getModel(modelClass, "", true);
		model.set("status", Const.OPTION_SUCCESS);
		model.set(Const.KEY_DB_CREATE_TIME, System.currentTimeMillis()/1000+"");
		model.set(Const.KEY_DB_DEL, "normal");
		System.out.println("model:"+model);
		model.save();
	
		List<AccountModel> accountModels = AccountModel.dao.find(getQueryAllSql(userId));
		if(CheckUtils.checkArrayIsNotNull(accountModels)){
			System.out.println("accountModels:"+accountModels);
			AccountModel accountModel = accountModels.get(0);
			double preBalance = accountModel.get("balance");
			System.out.println("preBalance:"+accountModels);
			if(StringUtil.isNumber(money)){
				System.out.println("money:"+money);
				double lastMoney = Double.parseDouble(money) + preBalance;
				System.out.println("lastMoney:"+lastMoney);
				accountModel.set("balance", lastMoney);
			}
			accountModel.set(Const.KEY_DB_UPDATE_TIME, System.currentTimeMillis()/1000+"");
			accountModel.update();
		}else{
			System.out.println("accountModel:");
			AccountModel accountModel = new AccountModel();
			accountModel.set("user_id", userId);
			accountModel.set("balance", money);
			accountModel.set(Const.KEY_DB_CREATE_TIME, System.currentTimeMillis()/1000+"");
			accountModel.set(Const.KEY_DB_DEL, "normal");
			accountModel.save();
		}
		
		JSONObject js = new JSONObject();
		js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_200);
		renderJson(js.toJSONString());
		
	}
	
	public String getQueryAllSql(String userId){
		return "select * from account where user_id = '"+userId+"' and del != 'delete'";
	}
}
