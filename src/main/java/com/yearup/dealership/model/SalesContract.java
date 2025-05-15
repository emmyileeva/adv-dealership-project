package com.yearup.dealership.model;

public class SalesContract extends Contract {
    private double salesTaxAmount;
    private double recordingFee = 100;
    private double processingFee;
    private boolean isFinanced;
    private double vehiclePrice;

    public SalesContract(String customerName, String customerEmail, Vehicle vehicle, String contractDate, double vehiclePrice, boolean isFinanced) {
        super(customerName, customerEmail, vehicle, contractDate);
        this.salesTaxAmount = vehiclePrice * 0.05; // sales tax rate of 5%
        this.processingFee = (vehiclePrice < 10000) ? 295 : 495; // processing fee based on vehicle price
        this.isFinanced = isFinanced;
        this.vehiclePrice = vehiclePrice;
    }

    public double getSalesTaxAmount() {
        return salesTaxAmount;
    }

    public void setSalesTaxAmount(double salesTaxAmount) {
        this.salesTaxAmount = salesTaxAmount;
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    public void setRecordingFee(double recordingFee) {
        this.recordingFee = recordingFee;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(double processingFee) {
        this.processingFee = processingFee;
    }

    public boolean isFinanced() {
        return isFinanced;
    }

    public void setFinanced(boolean financed) {
        isFinanced = financed;
    }

    public double getVehiclePrice() {
        return vehiclePrice;
    }

    public void setVehiclePrice(double vehiclePrice) {
        this.vehiclePrice = vehiclePrice;
    }

    @Override
    public double getTotalPrice() {
        return vehiclePrice + salesTaxAmount + recordingFee + processingFee;
    }

    @Override
    public double getMonthlyPayment() {
        if (!isFinanced) {
            return 0;
        }
        double interestRate;
        int loanTerm;

        if (vehiclePrice >= 10000) {
            interestRate = 0.0425;
            loanTerm = 48;
        } else {
            interestRate = 0.0525;
            loanTerm = 24;
        }
        return (getTotalPrice() * interestRate) / loanTerm;
    }
}
