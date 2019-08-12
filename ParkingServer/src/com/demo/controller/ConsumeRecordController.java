package com.demo.controller;

import com.demo.models.AccountModel;
import com.demo.models.CarModel;
import com.demo.models.ConsumeRecordModel;
import com.demo.models.UserModel;

public class ConsumeRecordController extends DefaultController<ConsumeRecordModel>{
	public static final String DB_TABLE = "consume_record";//�޸��� 1�����ݿ������
	public static final String HTML_KEY = "consume_record";//�޸���2��ҳ��ؼ���
	
	@Override
	public void setData() {
		modelClass = ConsumeRecordModel.class;
		tableName = DB_TABLE;
		htmlKey = HTML_KEY;
		entityDao = ConsumeRecordModel.dao;
	}
}
