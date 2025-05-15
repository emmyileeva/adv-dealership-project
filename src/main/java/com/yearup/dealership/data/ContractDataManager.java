package com.yearup.dealership.data;

import com.yearup.dealership.model.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ContractDataManager {

    public void saveContract(Contract contract) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("contracts.csv", true))) {
            String line;
            Vehicle vehicle = contract.getVehicle();

            if (contract instanceof SalesContract) {
                SalesContract salesContract = (SalesContract) contract;
                line = String.format("SALE|%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%.2f|%.2f|%.2f|%.2f|%.2f|%s|%.2f",
                        salesContract.getContractDate(),
                        salesContract.getCustomerName(),
                        salesContract.getCustomerEmail(),
                        vehicle.getVin(),
                        vehicle.getYear(),
                        vehicle.getMake(),
                        vehicle.getModel(),
                        vehicle.getVehicleType(),
                        vehicle.getColor(),
                        vehicle.getOdometer(),
                        vehicle.getPrice(),
                        salesContract.getSalesTaxAmount(),
                        salesContract.getRecordingFee(),
                        salesContract.getProcessingFee(),
                        salesContract.getTotalPrice(),
                        salesContract.isFinanced() ? "YES" : "NO",
                        salesContract.getMonthlyPayment());
            } else if (contract instanceof LeaseContract) {
                LeaseContract leaseContract = (LeaseContract) contract;
                line = String.format("LEASE|%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%.2f|%.2f|%.2f|%.2f|%.2f",
                        leaseContract.getContractDate(),
                        leaseContract.getCustomerName(),
                        leaseContract.getCustomerEmail(),
                        vehicle.getVin(),
                        vehicle.getYear(),
                        vehicle.getMake(),
                        vehicle.getModel(),
                        vehicle.getVehicleType(),
                        vehicle.getColor(),
                        vehicle.getOdometer(),
                        vehicle.getPrice(),
                        leaseContract.getExpectedEndingValue(),
                        leaseContract.getLeaseFee(),
                        leaseContract.getTotalPrice(),
                        leaseContract.getMonthlyPayment());
            } else {
                // in case of an unknown contract type
                line = String.format("UNKNOWN|%s|%s|%s|%d",
                        contract.getCustomerName(),
                        contract.getCustomerEmail(),
                        contract.getContractDate(),
                        vehicle.getVin());
            }
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

