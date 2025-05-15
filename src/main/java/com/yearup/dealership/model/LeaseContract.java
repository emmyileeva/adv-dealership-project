package com.yearup.dealership.model;

public class LeaseContract extends Contract {
    private double expectedEndingValue;
    private double leaseFee;
    private double vehiclePrice;

    public LeaseContract(String customerName, String customerEmail, String vehicleSold, String contractDate, double vehiclePrice) {
        super(customerName, customerEmail, vehicleSold, contractDate);
        this.expectedEndingValue = vehiclePrice * 0.5; // expected ending value is 50% of vehicle price
        this.leaseFee = vehiclePrice * 0.07; // lease fee is 7% of vehicle price
        this.vehiclePrice = vehiclePrice;
    }

    public double getExpectedEndingValue() {
        return expectedEndingValue;
    }

    public void setExpectedEndingValue(double expectedEndingValue) {
        this.expectedEndingValue = expectedEndingValue;
    }

    public double getLeaseFee() {
        return leaseFee;
    }

    public void setLeaseFee(double leaseFee) {
        this.leaseFee = leaseFee;
    }

    public double getVehiclePrice() {
        return vehiclePrice;
    }

    public void setVehiclePrice(double vehiclePrice) {
        this.vehiclePrice = vehiclePrice;
    }

    @Override
    public double getTotalPrice() {
        return expectedEndingValue + leaseFee;
    }

    @Override
    public double getMonthlyPayment() {
        return (getTotalPrice() * 0.04) / 36; // all lease contracts are 36 months at 4% interest
    }
}
