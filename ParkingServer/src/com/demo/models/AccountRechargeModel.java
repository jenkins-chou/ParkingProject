package com.demo.models;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class AccountRechargeModel extends Model<AccountRechargeModel>{
	public static final AccountRechargeModel dao = new AccountRechargeModel();
	
	public Page<AccountRechargeModel> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from account_recharge order by id asc");
	}
}
