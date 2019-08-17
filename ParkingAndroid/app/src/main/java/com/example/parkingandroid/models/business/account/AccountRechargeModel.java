package com.example.parkingandroid.models.business.account;

public class AccountRechargeModel {
    private String id;
    private String user_id;
    private String bankcard_id;
    private String money;
    private String status;
    private String detail;
    private String create_time;
    private String remark;
    private String del;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getBankcard_id() {
        return bankcard_id;
    }

    public void setBankcard_id(String bankcard_id) {
        this.bankcard_id = bankcard_id;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
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
        return "AccountRechargeModel{" +
                "id='" + id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", bankcard_id='" + bankcard_id + '\'' +
                ", money='" + money + '\'' +
                ", status='" + status + '\'' +
                ", detail='" + detail + '\'' +
                ", create_time='" + create_time + '\'' +
                ", remark='" + remark + '\'' +
                ", del='" + del + '\'' +
                '}';
    }
}
