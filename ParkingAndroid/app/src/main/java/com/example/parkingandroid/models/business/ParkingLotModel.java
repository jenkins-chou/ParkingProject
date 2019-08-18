package com.example.parkingandroid.models.business;

public class ParkingLotModel {
    private String id;
    private String parking_name;
    private String parking_address;
    private String parking_num;
    private String parking_price;
    private String longitude;
    private String latitude;
    private String detail;
    private String img;
    private String create_time;
    private String remark;
    private String del;

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

    public String getParking_address() {
        return parking_address;
    }

    public void setParking_address(String parking_address) {
        this.parking_address = parking_address;
    }

    public String getParking_num() {
        return parking_num;
    }

    public void setParking_num(String parking_num) {
        this.parking_num = parking_num;
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

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
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

    @Override
    public String toString() {
        return "ParkingLotModel{" +
                "id='" + id + '\'' +
                ", parking_name='" + parking_name + '\'' +
                ", parking_address='" + parking_address + '\'' +
                ", parking_num='" + parking_num + '\'' +
                ", parking_price='" + parking_price + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", detail='" + detail + '\'' +
                ", img='" + img + '\'' +
                ", create_time='" + create_time + '\'' +
                ", remark='" + remark + '\'' +
                ", del='" + del + '\'' +
                '}';
    }
}
