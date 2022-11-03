package com.zezoo.accountant;

public class SubjectListChildItem {
    private String Name, Code, Folder, Buyer, Spec, Time, Date;
    private int Column, Id;
    private double Amount,AmountLast,AmountLock, Cost, Last, Lock;

    SubjectListChildItem() {

    }

    SubjectListChildItem(int Column, int Id, String buyer, String Folder, String Name, double Amount, double AmountLast, double AmountLock, double Cost, double Last, double Lock, String Spec, String Code, String Time, String Date) {
        this.Column = Column;
        this.Id = Id;
        this.Buyer = buyer;
        this.Folder = Folder;
        this.Name = Name;
        this.Amount = Amount;
        this.AmountLast = AmountLast;
        this.AmountLock = AmountLock;
        this.Cost = Cost;
        this.Last = Last;
        this.Lock = Lock;
        this.Spec = Spec;
        this.Code = Code;
        this.Time = Time;
        this.Date = Date;
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

    public double getCost() {
        return Cost;
    }

    public void setCost(double cost) {
        Cost = cost;
    }

    public double getLast() {
        return Last;
    }

    public void setLast(double last) {
        Last = last;
    }

    public String getFolder() {
        return Folder;
    }

    public void setFolder(String folder) {
        Folder = folder;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getColumn() {
        return Column;
    }

    public void setColumn(int column) {
        Column = column;
    }

    public String getSpec() {
        return Spec;
    }

    public void setSpec(String spec) {
        Spec = spec;
    }

    public double getLock() {
        return Lock;
    }

    public void setLock(double lock) {
        Lock = lock;
    }

    public String getBuyer() {
        return Buyer;
    }

    public void setBuyer(String buyer) {
        Buyer = buyer;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public double getAmountLast() {
        return AmountLast;
    }

    public void setAmountLast(double amountLast) {
        AmountLast = amountLast;
    }

    public double getAmountLock() {
        return AmountLock;
    }

    public void setAmountLock(double amountLock) {
        AmountLock = amountLock;
    }
}
