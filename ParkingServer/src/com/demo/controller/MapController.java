package com.demo.controller;

import com.demo.models.BankCardModel;
import com.jfinal.core.Controller;

public class MapController extends Controller{
	public static final String HTML_KEY = "map";//�޸���2��ҳ��ؼ���
	
	public void showIndex(){
		render("index.html");
	}
}
