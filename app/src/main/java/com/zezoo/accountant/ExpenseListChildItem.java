package com.zezoo.accountant;

public class ExpenseListChildItem {
    private String Consumer, Expense, Category, Spec, Currency, Time, Date;
    private int Column, Amount;

    ExpenseListChildItem() {

    }

    ExpenseListChildItem(int column, int amount, String consumer, String expense, String category, String spec, String currency, String time, String date) {
        Consumer = consumer;
        Expense = expense;
        Category = category;
        Spec = spec;
        Currency = currency;
        Column = column;
        Amount = amount;
        Time = time;
        Date = date;
    }

    public String getConsumer() {
        return Consumer;
    }

    public void setConsumer(String consumer) {
        Consumer = consumer;
    }

    public String getExpense() {
        return Expense;
    }

    public void setExpense(String expense) {
        Expense = expense;
    }


    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
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
