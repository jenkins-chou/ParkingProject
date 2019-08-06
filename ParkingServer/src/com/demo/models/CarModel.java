package com.demo.models;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class CarModel extends Model<CarModel>{
	public static final CarModel dao = new CarModel();
	
	public Page<CarModel> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from car order by id asc");
	}
}
