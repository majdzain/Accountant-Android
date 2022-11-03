package com.zezoo.accountant;

public class PBondListChildItem {
    private String Debtor, Phone, Spec, Currency, Time, Date;
    private int Column, Amount;

    PBondListChildItem() {

    }

    PBondListChildItem(int column, int amount, String debtor, String phone, String spec, String currency, String time, String date) {
        Debtor = debtor;
        Phone = phone;
        Spec = spec;
        Currency = currency;
        Column = column;
        Amount = amount;
        Time = time;
        Date = date;
    }

    public String getDebtor() {
        return Debtor;
    }

    public void setDebtor(String debtor) {
        Debtor = debtor;
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
