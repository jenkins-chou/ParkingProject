package com.demo.models;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class AccountModel extends Model<AccountModel>{
	public static final AccountModel dao = new AccountModel();
	
	public Page<AccountModel> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from account order by id asc");
	}
	
	public static String getQueryAllByUserId(String userId){
		return "select * from account where user_id = '"+userId+"' and del != 'delete'";
	}
}
