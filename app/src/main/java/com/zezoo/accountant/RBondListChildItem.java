package com.zezoo.accountant;

public class RBondListChildItem {
    private String Creditor, Phone, Spec, Currency, Time, Date;
    private int Column, Amount;

    RBondListChildItem() {

    }

    RBondListChildItem(int column, int amount, String creditor, String phone, String spec, String currency, String time, String date) {
        Creditor = creditor;
        Phone = phone;
        Spec = spec;
        Currency = currency;
        Column = column;
        Amount = amount;
        Time = time;
        Date = date;
    }

    public String getCreditor() {
        return Creditor;
    }

    public void setCreditor(String creditor) {
        Creditor = creditor;
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

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }


}
