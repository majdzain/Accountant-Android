package com.zezoo.accountant;

import android.graphics.drawable.Drawable;

public class ListItem {
    private String customerName;
    private String date;
    private String total;
    private String name;
    private int color;
    private String basic;

    public ListItem(String customerName, String date, String total, String name, int color, String basic) {
        this.customerName = customerName;
        this.date = date;
        this.total = total;
        this.name = name;
        this.color = color;
        this.basic = basic;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getTotal() {
        return total;
    }
    public void setTotal(String total) {
        this.total = total;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public int getColor(){
        return color;
    }
    public void setColor(int color){
        this.color = color;
    }
    public String getBasic(){
        return basic;
    }
    public void setBasic(String basic){
        this.basic = basic;
    }
    /** @Override
    public String toString() {
        return title + "\n" + desc;
    }
    **/
}
