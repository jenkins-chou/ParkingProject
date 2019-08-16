package com.example.parkingandroid.models.business.account;

public class BankCardModel {
    private String id;
    private String user_id;
    private String bank_name;
    private String bankcard_number;
    private String bankcard_username;
    private String bankcard_phone;
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

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getBankcard_number() {
        return bankcard_number;
    }

    public void setBankcard_number(String bankcard_number) {
        this.bankcard_number = bankcard_number;
    }

    public String getBankcard_username() {
        return bankcard_username;
    }

    public void setBankcard_username(String bankcard_username) {
        this.bankcard_username = bankcard_username;
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

    public String getBankcard_phone() {
        return bankcard_phone;
    }

    public void setBankcard_phone(String bankcard_phone) {
        this.bankcard_phone = bankcard_phone;
    }

    @Override
    public String toString() {
        return "BankCardModel{" +
                "id='" + id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", bank_name='" + bank_name + '\'' +
                ", bankcard_number='" + bankcard_number + '\'' +
                ", bankcard_username='" + bankcard_username + '\'' +
                ", bankcard_phone='" + bankcard_phone + '\'' +
                ", create_time='" + create_time + '\'' +
                ", remark='" + remark + '\'' +
                ", del='" + del + '\'' +
                '}';
    }
}
