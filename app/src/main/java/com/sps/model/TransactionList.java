package com.sps.model;

/**
 * Created by Jai1.Kumar on 29-04-2018.
 */

   public class TransactionList {

    int id;
    String name;
    String totalAmount;
    String dueAmount;

    public TransactionList(int id, String name, String totalAmount, String dueAmount) {
        this.id = id;
        this.name = name;
        this.totalAmount = totalAmount;
        this.dueAmount = dueAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getDueAmount() {
        return dueAmount;
    }

    public void setDueAmount(String dueAmount) {
        this.dueAmount = dueAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
