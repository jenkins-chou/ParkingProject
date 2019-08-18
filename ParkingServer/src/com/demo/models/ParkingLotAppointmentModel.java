package com.demo.models;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class ParkingLotAppointmentModel extends Model<ParkingLotAppointmentModel>{
	public static final ParkingLotAppointmentModel dao = new ParkingLotAppointmentModel();
	
	public Page<ParkingLotAppointmentModel> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from parking_lot_appointment where del != 'delete' order by id asc");
	}
}
