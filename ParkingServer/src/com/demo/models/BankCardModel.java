package com.demo.models;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class BankCardModel extends Model<BankCardModel>{
	public static final BankCardModel dao = new BankCardModel();
	
	public Page<BankCardModel> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from bankcard order by id asc");
	}
	
	public static String getQueryAllByUserId(String userId){
		return "select * from bankcard where user_id = '"+userId+"' and del != 'delete'";
	}
}
