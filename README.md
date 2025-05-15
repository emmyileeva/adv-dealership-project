# 🚗 Advanced Car Dealership

This project is a Java console application that expands the original dealership system with new features for contracts and admin access. Users can now record sales and leases, while administrators can view all contract history. All data is saved through local text files.

---

## 📚 Features Included

### 1. 🏠 Main Menu
- Lets the user:
  - View all vehicles  
  - Search by make & model, year, color, type, mileage, or price  
  - Add or remove vehicles  
  - Sell or lease a vehicle  
  - Enter admin panel  
  - Exit the app  

### 2. 🔍 Vehicle Search & Inventory Management
- Supports filtering inventory by multiple attributes
- Allows users to:
  - Add new vehicles by entering details like VIN, make, model, year, and price
  - Remove a vehicle using its VIN
- Inventory changes are saved to `inventory.txt`

### 3. 📝 Sell or Lease Contracts
- Users can:
  - Enter customer info and select a vehicle
  - Choose between sales or lease contracts
  - Automatically calculate total price and monthly payments
- Saves all contracts to `contracts.csv` using a pipe-delimited format
- Vehicles sold/leased are removed from inventory

### 4. 🔐 Admin Panel (Bonus)
- Password-protected admin mode
- View **all contracts** or just the **last 10**
- Reads from `contracts.csv` and reconstructs contract objects

---

## ✨ Interesting Code Highlight

```java
if (contract instanceof SalesContract) {
    SalesContract salesContract = (SalesContract) contract;
    line = String.format("SALE|%s|%s|...|%.2f", salesContract.getCustomerName(), salesContract.getContractDate(), ...);
}
```
## 💡 Why it's interesting
This code uses instanceof to check the contract type and cast it appropriately. It then formats the contract data for saving, showing how polymorphism and inheritance work together with file writing logic.
