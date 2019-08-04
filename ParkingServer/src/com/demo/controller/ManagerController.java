package com.demo.controller;

import java.util.List;

import com.demo.models.EnterpriseModel;
import com.demo.models.UserModel;
import com.jfinal.core.Controller;

public class ManagerController extends Controller {
	
	public void login(){
		String username = getPara("username");
		String password = getPara("password");
		String type = getPara("type");
		
		System.out.println(username);
		System.out.println(password);
		System.out.println(type);
		
		if(type!=null){
			if(type.equals("system")){//管理员用户
				
				String sql = "select * from user_base where useridentify = '"+username+"' and pass = '"+password+"' and type = 'system' and del != 'delete'";
				System.out.println(sql);
				
				List<UserModel> userModels = UserModel.dao.find(sql);
				System.out.println(userModels);
				if(userModels!=null&&userModels.size()>0){
					setAttr("manager",userModels.get(0));
					render("index.html");
				}else{
					setAttr("status","不存在该管理员");  
					render("login.html");
				}
				
			}else{
				render("login.html");
			}
		}else{
			render("login.html");
		}
	}

}
