package com.demo.common;

import java.util.HashMap;




import java.util.Map;

import org.beetl.core.GroupTemplate;
import org.beetl.ext.jfinal3.JFinal3BeetlRenderFactory;


import com.demo.controller.ManagerController;
import com.demo.controller.UserController;
import com.demo.index.IndexController;
import com.demo.models.AccountModel;
import com.demo.models.AccountRechargeModel;
import com.demo.models.CarModel;
import com.demo.models.ConsumeRecordModel;
import com.demo.models.CouponBaseModel;
import com.demo.models.CouponUserModel;
import com.demo.models.DriverModel;
import com.demo.models.MessageModel;
import com.demo.models.ParkingLotModel;
import com.demo.models.UserModel;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;

public class DemoConfig extends JFinalConfig {
	
	public void configConstant(Constants me) {
		PropKit.use("db_config.txt");				
		me.setDevMode(PropKit.getBoolean("devMode", false));
		me.setViewType(ViewType.JSP); 	
		
		JFinal3BeetlRenderFactory rf = new JFinal3BeetlRenderFactory();
		rf.config();
		me.setRenderFactory(rf);
		GroupTemplate gt = rf.groupTemplate;
		Map<String, Object> shard = new HashMap<String, Object>();// 共享变量
		shard.put("ctx", JFinal.me().getContextPath());// 添加共享变量上下文路�?
		gt.setSharedVars(shard);// 设置共享变量
		me.setMaxPostSize(1200000000);
		
		//me.setBaseUploadPath("/upload");
	}
	
	public void configRoute(Routes me) {
		me.add("/", IndexController.class);	
		me.add("/manager", ManagerController.class);	
		me.add("/user", UserController.class);
	}
	 
	public void configPlugin(Plugins me) {
		C3p0Plugin c3p0Plugin = new C3p0Plugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
		me.add(c3p0Plugin);
		
		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
		me.add(arp);
		arp.addMapping("account", AccountModel.class);
		arp.addMapping("account_recharge", AccountRechargeModel.class);
		arp.addMapping("car", CarModel.class);
		arp.addMapping("consume_record", ConsumeRecordModel.class);
		arp.addMapping("coupon_base", CouponBaseModel.class);
		arp.addMapping("coupon_user", CouponUserModel.class);
		arp.addMapping("driver", DriverModel.class);
		arp.addMapping("message", MessageModel.class);
		arp.addMapping("parking_lot", ParkingLotModel.class);
		arp.addMapping("user_base", UserModel.class);
	}
	
	public void configInterceptor(Interceptors me) {
		
	}
	
	public void configHandler(Handlers me) {
		me.add(new ContextPathHandler("ctx"));
	}
	
	public static void main(String[] args) {
		
		//PathKit.setWebRootPath("/WebRoot");
		JFinal.start("WebRoot", 8007, "/", 5);
	}

	@Override
	public void configEngine(Engine arg0) {
		// TODO Auto-generated method stub
	}
}
