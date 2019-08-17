package com.example.parkingandroid.models.business;

public class CarModel {
    private String id;
    private String user_id;

    private String car_number;
    private String type;
    private String owner;
    private String brand;
    private String model;
    private String car_code;
    private String engine_number;
    private String photo;

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

    public String getCar_number() {
        return car_number;
    }

    public void setCar_number(String car_number) {
        this.car_number = car_number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCar_code() {
        return car_code;
    }

    public void setCar_code(String car_code) {
        this.car_code = car_code;
    }

    public String getEngine_number() {
        return engine_number;
    }

    public void setEngine_number(String engine_number) {
        this.engine_number = engine_number;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "CarModel{" +
                "id='" + id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", car_number='" + car_number + '\'' +
                ", type='" + type + '\'' +
                ", owner='" + owner + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", car_code='" + car_code + '\'' +
                ", engine_number='" + engine_number + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
