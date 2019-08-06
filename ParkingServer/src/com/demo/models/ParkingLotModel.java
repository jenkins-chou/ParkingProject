package com.demo.models;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class ParkingLotModel extends Model<ParkingLotModel>{
	public static final ParkingLotModel dao = new ParkingLotModel();
	
	public Page<ParkingLotModel> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from parking_lot order by id asc");
	}
}
