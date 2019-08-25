package com.example.parkingandroid.models.business;

public class UserModel {
    public String id;
    public String name;
    public String pass;
    public String realname;
    public String avatar;
    public String slogan;
    public String sex;
    public String age;
    public String idnum;
    public String nation;
    public String registered_residence;//户籍
    public String email;
    public String useridentify;
    public String phone;
    public String address;
    public String health;
    public String type;
    public String pay_pwd;
    public String create_time;
    public String remark;
    public String del;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getIdnum() {
        return idnum;
    }

    public void setIdnum(String idnum) {
        this.idnum = idnum;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getRegistered_residence() {
        return registered_residence;
    }

    public void setRegistered_residence(String registered_residence) {
        this.registered_residence = registered_residence;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUseridentify() {
        return useridentify;
    }

    public void setUseridentify(String useridentify) {
        this.useridentify = useridentify;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPay_pwd() {
        return pay_pwd;
    }

    public void setPay_pwd(String pay_pwd) {
        this.pay_pwd = pay_pwd;
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
        return "UserModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", pass='" + pass + '\'' +
                ", realname='" + realname + '\'' +
                ", avatar='" + avatar + '\'' +
                ", slogan='" + slogan + '\'' +
                ", sex='" + sex + '\'' +
                ", age='" + age + '\'' +
                ", idnum='" + idnum + '\'' +
                ", nation='" + nation + '\'' +
                ", registered_residence='" + registered_residence + '\'' +
                ", email='" + email + '\'' +
                ", useridentify='" + useridentify + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", health='" + health + '\'' +
                ", type='" + type + '\'' +
                ", pay_pwd='" + pay_pwd + '\'' +
                ", create_time='" + create_time + '\'' +
                ", remark='" + remark + '\'' +
                ", del='" + del + '\'' +
                '}';
    }
}
