package com.example.goconnect;

public class OrderHelper {

    String customerName;
    String customerAddress;
    String customerDate;
    String customerNumber;


    public OrderHelper(){

    }

    public OrderHelper(String customerName, String customerAddress, String customerDate, String customerNumber) {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerDate = customerDate;
        this.customerNumber = customerNumber;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerDate() {
        return customerDate;
    }

    public void setCustomerDate(String customerDate) {
        this.customerDate = customerDate;
    }

    @Override
    public String toString() {
        return "customerName='" + customerName + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", customerDate='" + customerDate + '\'' +
                ", customerPhoneNumber='" + customerNumber;
    }

}
