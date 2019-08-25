package com.demo.controller;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.demo.models.AccountModel;
import com.demo.models.CarModel;
import com.demo.models.ConsumeRecordModel;
import com.demo.models.CouponBaseModel;
import com.demo.models.CouponUserModel;
import com.demo.models.UserModel;
import com.demo.utils.CheckUtils;
import com.demo.utils.Const;
import com.demo.utils.Log;
import com.demo.utils.PageJson;
import com.demo.utils.ParamUtil;
import com.demo.utils.StringUtil;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Page;

import cn.jiguang.common.utils.StringUtils;

public class CouponUserController extends DefaultController<CouponUserModel>{
	public static final String DB_TABLE = "coupon_user";//修改项 1：数据库表名称
	public static final String HTML_KEY = "coupon_user";//修改项2：页面关键字
	
	@Override
	public void setData() {
		modelClass = CouponUserModel.class;
		tableName = DB_TABLE;
		htmlKey = HTML_KEY;
		entityDao = CouponUserModel.dao;
	}
	
	public void buyCoupon(){
		String user_id = getPara(Const.KEY_DB_USER_ID);
		String coupon_money = getPara("coupon_money");
		String coupon_id = getPara("coupon_id");
		Log.i(user_id);
		Log.i(coupon_money);
		Log.i(coupon_id);
		Log.i(StringUtil.isNumber(coupon_money)+"");
		
		List<AccountModel> accountModels = AccountModel.dao.find("select * from account where user_id = '"+user_id+"' and del != 'delete'");
		if(CheckUtils.checkArrayIsNotNull(accountModels)){
			AccountModel accountModel = accountModels.get(0);
			if(StringUtil.isNumber(coupon_money)){
				double preBalance = accountModel.getDouble("balance");
				double couponMoneyDouble = Double.parseDouble(coupon_money);
				if(preBalance>couponMoneyDouble){
					
					//添加消费记录
					ConsumeRecordModel consumeModel = new ConsumeRecordModel();
					consumeModel.set("user_id", user_id);
					consumeModel.set("money", coupon_money);
					consumeModel.set("address", "online");
					consumeModel.set("status", Const.OPTION_SUCCESS);
					consumeModel.set("duration", "none");
					consumeModel.set("detail", "优惠券商店线上消费");
					consumeModel.set("create_time", System.currentTimeMillis()/1000+"");
					consumeModel.set("remark", "none");
					consumeModel.set("del", "normal");
					consumeModel.save();
					
					accountModel.set("balance", preBalance-couponMoneyDouble);
					accountModel.update();
					addEntity();
				}else{
					JSONObject js = new JSONObject();
					js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_201);
					js.put(Const.KEY_RES_MESSAGE, "余额不足0");
					renderJson(js.toJSONString());
				}
			}else{
				JSONObject js = new JSONObject();
				js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_201);
				js.put(Const.KEY_RES_MESSAGE, "余额不足1");
				renderJson(js.toJSONString());
			}
			
		}else{
			JSONObject js = new JSONObject();
			js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_201);
			js.put(Const.KEY_RES_MESSAGE, "余额不足2");
			renderJson(js.toJSONString());
		}
		
	}
	
	public void getAllEntityByUserId() {
		setData();
		String sql = "select a.* from coupon_base a,coupon_user b where a.id = b.coupon_id and b.user_id = '"+getPara(Const.KEY_DB_USER_ID)+"' and b.del != 'delete' and a.del != 'delete'";
		Log.i(sql);
		List<CouponBaseModel> list = CouponBaseModel.dao.find(sql);
		JSONObject js = new JSONObject();
		js.put(Const.KEY_RES_CODE, Const.KEY_RES_CODE_200);
		js.put(Const.KEY_RES_DATA, list);
		renderJson(js);
	}
}
