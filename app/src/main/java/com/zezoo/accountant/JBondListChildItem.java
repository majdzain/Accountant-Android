package com.zezoo.accountant;

public class JBondListChildItem {
    private String DebCre, Phone, Spec, Currency, Time, Date;
    private int Column, CAmount, DAmount;

    JBondListChildItem() {

    }

    JBondListChildItem(int column, int camount, int damount, String deb_cre, String phone, String spec, String currency, String time, String date) {
        DebCre = deb_cre;
        Phone = phone;
        Spec = spec;
        Currency = currency;
        Column = column;
        CAmount = camount;
        Time = time;
        Date = date;
        DAmount = damount;
    }

    public String getDebCre() {
        return DebCre;
    }

    public void setDebCre(String deb_cre) {
        DebCre = deb_cre;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getSpec() {
        return Spec;
    }

    public void setSpec(String spec) {
        Spec = spec;
    }

    public String getCurrency() {
        return Currency;
    }

    public void setCurrency(String currency) {
        Currency = currency;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getColumn() {
        return Column;
    }

    public void setColumn(int column) {
        Column = column;
    }

    public int getCAmount() {
        return CAmount;
    }

    public void setCAmount(int camount) {
        CAmount = camount;
    }


    public int getDAmount() {
        return DAmount;
    }

    public void setDAmount(int DAmount) {
        this.DAmount = DAmount;
    }
}
