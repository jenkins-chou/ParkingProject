package com.example.parkingandroid.models.business;

public class CouponModel {
    private String deduction;
    private String money;
    private String create_time;
    private String quota;
    private String discount;
    private String remark;
    private String del;
    private String id;
    private String deadline;
    private String type;
    private String coupon_name;
    private String coupon_publisher;


    private String coupon_user_id;

    public String getDeduction() {
        return deduction;
    }

    public void setDeduction(String deduction) {
        this.deduction = deduction;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getQuota() {
        return quota;
    }

    public void setQuota(String quota) {
        this.quota = quota;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCoupon_name() {
        return coupon_name;
    }

    public void setCoupon_name(String coupon_name) {
        this.coupon_name = coupon_name;
    }

    public String getCoupon_publisher() {
        return coupon_publisher;
    }

    public void setCoupon_publisher(String coupon_publisher) {
        this.coupon_publisher = coupon_publisher;
    }

    public String getCoupon_user_id() {
        return coupon_user_id;
    }

    public void setCoupon_user_id(String coupon_user_id) {
        this.coupon_user_id = coupon_user_id;
    }

    @Override
    public String toString() {
        return "CouponModel{" +
                "deduction='" + deduction + '\'' +
                ", money='" + money + '\'' +
                ", create_time='" + create_time + '\'' +
                ", quota='" + quota + '\'' +
                ", discount='" + discount + '\'' +
                ", remark='" + remark + '\'' +
                ", del='" + del + '\'' +
                ", id='" + id + '\'' +
                ", deadline='" + deadline + '\'' +
                ", type='" + type + '\'' +
                ", coupon_name='" + coupon_name + '\'' +
                ", coupon_publisher='" + coupon_publisher + '\'' +
                ", coupon_user_id='" + coupon_user_id + '\'' +
                '}';
    }
}
