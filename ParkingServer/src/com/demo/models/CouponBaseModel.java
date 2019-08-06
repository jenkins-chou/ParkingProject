package com.demo.models;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class CouponBaseModel extends Model<CouponBaseModel>{
	public static final CouponBaseModel dao = new CouponBaseModel();
	
	public Page<CouponBaseModel> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from coupon_base order by id asc");
	}
}
