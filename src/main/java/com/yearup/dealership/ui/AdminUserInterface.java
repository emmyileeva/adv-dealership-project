package com.yearup.dealership.ui;

import com.yearup.dealership.data.ContractDataManager;
import com.yearup.dealership.model.Contract;

import java.util.List;
import java.util.Scanner;

public class AdminUserInterface {
    private Scanner scanner = new Scanner(System.in);
    private ContractDataManager contractDataManager = new ContractDataManager();

    public void displayAdminMenu() {
        String choice = "";

        while (!choice.equals("exit")) {
            System.out.println("Admin Menu:");
            System.out.println("1. View all contracts");
            System.out.println("2. View the last 10 contracts");
            System.out.println("3. Type 'exit' to exit the admin menu");
            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    viewAllContracts();
                    break;
                case "2":
                    viewLast10Contracts();
                    break;
                case "3":
                    System.out.println("Exiting admin menu and returning to main menu...");
                    choice = "exit";
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void viewAllContracts() {
        List<Contract> contracts = contractDataManager.getAllContracts();
        for (Contract contract : contracts) {
            System.out.println(contract);
        }
    }

    public void viewLast10Contracts() {
        List<Contract> contracts = contractDataManager.getLast10Contracts();
        int startIndex = Math.max(0, contracts.size() - 10);
        List<Contract> last10Contracts = contracts.subList(startIndex, contracts.size());
        for (Contract contract : last10Contracts) {
            System.out.println(contract);
        }
    }
}
