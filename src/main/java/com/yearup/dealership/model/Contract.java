package com.yearup.dealership.model;

public abstract class Contract {
    private String customerName;
    private String customerEmail;
    private Vehicle vehicle;
    private String contractDate;

    public Contract(String customerName, String customerEmail, Vehicle vehicle, String contractDate) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicle = vehicle;
        this.contractDate = contractDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public String getContractDate() {
        return contractDate;
    }

    public void setContractDate(String contractDate) {
        this.contractDate = contractDate;
    }

    public abstract double getTotalPrice();

    public abstract double getMonthlyPayment();
}

