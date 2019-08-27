package com.example.parkingandroid.models.business;

public class ParkingLotAppointmentModel {
    private String parking_address;
    private String img;
    private String code;
    private String create_time;
    private String latitude;
    private String end_time;
    private String remark;
    private String del;
    private String parking_num;
    private String start_time;
    private String number;
    private String money;
    private String user_id;
    private String parking_id;
    private String id;
    private String parking_name;
    private String detail;
    private String status;
    private String parking_price;
    private String longitude;

    public String getParking_address() {
        return parking_address;
    }

    public void setParking_address(String parking_address) {
        this.parking_address = parking_address;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del;
    }

    public String getParking_num() {
        return parking_num;
    }

    public void setParking_num(String parking_num) {
        this.parking_num = parking_num;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getParking_id() {
        return parking_id;
    }

    public void setParking_id(String parking_id) {
        this.parking_id = parking_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParking_name() {
        return parking_name;
    }

    public void setParking_name(String parking_name) {
        this.parking_name = parking_name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getParking_price() {
        return parking_price;
    }

    public void setParking_price(String parking_price) {
        this.parking_price = parking_price;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "ParkingLotAppointmentModel{" +
                "parking_address='" + parking_address + '\'' +
                ", img='" + img + '\'' +
                ", code='" + code + '\'' +
                ", create_time='" + create_time + '\'' +
                ", latitude='" + latitude + '\'' +
                ", end_time='" + end_time + '\'' +
                ", remark='" + remark + '\'' +
                ", del='" + del + '\'' +
                ", parking_num='" + parking_num + '\'' +
                ", start_time='" + start_time + '\'' +
                ", number='" + number + '\'' +
                ", money='" + money + '\'' +
                ", user_id='" + user_id + '\'' +
                ", parking_id='" + parking_id + '\'' +
                ", id='" + id + '\'' +
                ", parking_name='" + parking_name + '\'' +
                ", detail='" + detail + '\'' +
                ", status='" + status + '\'' +
                ", parking_price='" + parking_price + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}
