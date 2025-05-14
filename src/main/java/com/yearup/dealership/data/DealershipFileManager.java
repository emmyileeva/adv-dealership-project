package com.yearup.dealership.data;


import com.yearup.dealership.model.Dealership;
import com.yearup.dealership.model.Vehicle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class DealershipFileManager {

    public Dealership getDealership(String fileName) {
        try {
            // Read the dealership information from the first line of the file
            BufferedReader br = new BufferedReader(new FileReader("inventory.txt"));

            String header = br.readLine();
            String[] info = header.split("\\|");
            // info[0] = name, info[1] = address, info[2] = phone
            String name = info[0];
            String address = info[1];
            String phone = info[2];

            Dealership dealership = new Dealership(name, address, phone);

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                int vin = Integer.parseInt(parts[0]);
                int year = Integer.parseInt(parts[1]);
                String make = parts[2];
                String model = parts[3];
                String vehicleType = parts[4];
                String color = parts[5];
                int odometer = Integer.parseInt(parts[6]);
                double price = Double.parseDouble(parts[7]);

                Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
                dealership.addVehicle(vehicle);
            }
            br.close();
            return dealership;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void saveDealership(Dealership dealership) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("inventory.txt"));
            writer.write(dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhoneNumber());
            writer.newLine();

            for (Vehicle vehicle : dealership.getAllVehicles()) {
                String line = vehicle.getVin() + "|" +
                        vehicle.getYear() + "|" +
                        vehicle.getMake() + "|" +
                        vehicle.getModel() + "|" +
                        vehicle.getVehicleType() + "|" +
                        vehicle.getColor() + "|" +
                        vehicle.getOdometer() + "|" +
                        String.format("%.2f", vehicle.getPrice());

                writer.write(line);
                writer.newLine();
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

