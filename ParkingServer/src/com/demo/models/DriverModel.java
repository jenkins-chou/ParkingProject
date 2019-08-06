package com.demo.models;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class DriverModel extends Model<DriverModel>{
	public static final DriverModel dao = new DriverModel();
	
	public Page<DriverModel> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from driver order by id asc");
	}
}
