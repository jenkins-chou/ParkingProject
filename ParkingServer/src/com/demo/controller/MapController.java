package com.demo.controller;

import com.demo.models.BankCardModel;
import com.jfinal.core.Controller;

public class MapController extends Controller{
	public static final String HTML_KEY = "map";//修改项2：页面关键字
	
	public void showIndex(){
		render("index.html");
	}
}
