package com.example.parkingandroid.models.business.account;

public class BalanceAndCoupon {
    String balance;
    String coupon;

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    @Override
    public String toString() {
        return "BalanceAndCoupon{" +
                "balance='" + balance + '\'' +
                ", coupon='" + coupon + '\'' +
                '}';
    }
}
