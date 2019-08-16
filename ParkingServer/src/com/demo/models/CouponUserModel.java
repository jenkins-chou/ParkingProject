package com.demo.models;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class CouponUserModel extends Model<CouponUserModel>{
	public static final CouponUserModel dao = new CouponUserModel();
	
	public Page<CouponUserModel> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from coupon_user order by id asc");
	}
	
	public static String getQueryAllByUserId(String userId){
		return "select * from coupon_user where user_id = '"+userId+"' and del != 'delete'";
	}
}
