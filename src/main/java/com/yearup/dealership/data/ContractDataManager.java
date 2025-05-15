package com.yearup.dealership.data;

import com.yearup.dealership.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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

    public List<Contract> getAllContracts() {
        List<Contract> contracts = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("contracts.csv"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");

                String contractType = parts[0];
                String contractDate = parts[1];
                String customerName = parts[2];
                String customerEmail = parts[3];

                int vin = Integer.parseInt(parts[4]);
                int year = Integer.parseInt(parts[5]);
                String make = parts[6];
                String model = parts[7];
                String vehicleType = parts[8];
                String color = parts[9];
                int odometer = Integer.parseInt(parts[10]);
                double price = Double.parseDouble(parts[11]);

                Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);

                if (contractType.equals("SALE")) {
                    boolean isFinanced = parts[16].equalsIgnoreCase("YES");
                    SalesContract salesContract = new SalesContract(customerName, customerEmail, vehicle, contractDate, price, isFinanced);
                    contracts.add(salesContract);
                } else if (contractType.equals("LEASE")) {
                    LeaseContract leaseContract = new LeaseContract(customerName, customerEmail, vehicle, contractDate, price);
                    contracts.add(leaseContract);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contracts;
    }

    public List<Contract> getLast10Contracts() {
        List<Contract> contracts = getAllContracts();
        int startIndex = Math.max(0, contracts.size() - 10);
        return contracts.subList(startIndex, contracts.size());
    }
}

