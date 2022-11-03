package com.zezoo.accountant;

public class CustomerListChildItem {
    private String Company, Customer, CompanyPho, CustomerPho, Email, Spec, Cash, Currency, Time, Date;
    private int Column, Account;

    CustomerListChildItem() {

    }

    CustomerListChildItem(int column, int account, String company, String customer, String companyPho, String customerPho, String email, String spec, String cash, String currency, String time, String date) {
        Company = company;
        Customer = customer;
        CompanyPho = companyPho;
        CustomerPho = customerPho;
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

    public String getCustomer() {
        return Customer;
    }

    public void setCustomer(String customer) {
        Customer = customer;
    }

    public String getCompanyPho() {
        return CompanyPho;
    }

    public void setCompanyPho(String companyPho) {
        CompanyPho = companyPho;
    }

    public String getCustomerPho() {
        return CustomerPho;
    }

    public void setCustomerPho(String customerPho) {
        CustomerPho = customerPho;
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
