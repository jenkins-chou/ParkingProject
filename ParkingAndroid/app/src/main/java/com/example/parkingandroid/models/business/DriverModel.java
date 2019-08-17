package com.example.parkingandroid.models.business;

public class DriverModel {
    private String id;
    private String user_id;
    private String photo;
    private String award_time;
    private String validity;
    private String awrad_unit;
    private String number;

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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getAward_time() {
        return award_time;
    }

    public void setAward_time(String award_time) {
        this.award_time = award_time;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public String getAwrad_unit() {
        return awrad_unit;
    }

    public void setAwrad_unit(String awrad_unit) {
        this.awrad_unit = awrad_unit;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "DriverModel{" +
                "id='" + id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", photo='" + photo + '\'' +
                ", award_time='" + award_time + '\'' +
                ", validity='" + validity + '\'' +
                ", awrad_unit='" + awrad_unit + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
