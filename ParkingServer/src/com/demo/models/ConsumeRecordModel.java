package com.demo.models;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class ConsumeRecordModel extends Model<ConsumeRecordModel>{
	public static final ConsumeRecordModel dao = new ConsumeRecordModel();
	
	public Page<ConsumeRecordModel> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from consume_record order by id asc");
	}
}
