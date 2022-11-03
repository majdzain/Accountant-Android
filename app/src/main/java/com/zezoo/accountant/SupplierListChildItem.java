package com.zezoo.accountant;

public class SupplierListChildItem {
    private String Company, Supplier, CompanyPho, SupplierPho, Email, Spec, Cash, Currency, Time, Date;
    private int Column, Account;

    SupplierListChildItem() {

    }

    SupplierListChildItem(int column, int account, String company, String supplier, String companyPho, String supplierPho, String email, String spec, String cash, String currency, String time, String date) {
        Company = company;
        Supplier = supplier;
        CompanyPho = companyPho;
        SupplierPho = supplierPho;
        Email = email;
        Spec = spec;
        Cash = cash;
        Currency = currency;
        Column = column;
        Account = account;
        Time = time;
        Date = date;
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String company) {
        Company = company;
    }

    public String getSupplier() {
        return Supplier;
    }

    public void setSupplier(String supplier) {
        Supplier = supplier;
    }

    public String getCompanyPho() {
        return CompanyPho;
    }

    public void setCompanyPho(String companyPho) {
        CompanyPho = companyPho;
    }

    public String getSupplierPho() {
        return SupplierPho;
    }

    public void setSupplierPho(String supplierPho) {
        SupplierPho = supplierPho;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getSpec() {
        return Spec;
    }

    public void setSpec(String spec) {
        Spec = spec;
    }

    public String getCash() {
        return Cash;
    }

    public void setCash(String cash) {
        Cash = cash;
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

    public int getAccount() {
        return Account;
    }

    public void setAccount(int account) {
        Account = account;
    }


}
