package com.pluralsight;

import java.util.List;
import java.util.Scanner;

public class Ledger {
    private TransactionManager manager;
    // We got list from another class (reading)

    public Ledger() {
        // Creating a new transaction manager, we will manage all transactions with this class
        manager = new TransactionManager(); // this creates the object
        // When the application opens, we store previously recorded operations.
        manager.loadTransactions(); // Starting just when program starting
        // Starting the reading process from the CSV file
    }


    //Scanner scanner is like a shortcut for new scanner comand line
    //and use an already-created Scanner object for reading user input.

    public void addDeposit(Scanner scanner) {
        // A deposit transaction is created using the information received from the user
        System.out.println("=== Add Deposit ===");

        // We get all the necessary information from the user in order
        System.out.print("Enter date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        System.out.print("Enter time (HH:MM: ): ");
        String time = scanner.nextLine();

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Enter amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        // Transaction object is created and saved
        Transaction transaction = new Transaction(date, time, description, vendor, amount);
        TransactionManager.saveTransaction(transaction);
        System.out.println("Deposit recorded successfully!");
    }

    // We do the same operations this time for payment

    public void makePayment(Scanner scanner) {
        System.out.println("=== Make Payment ===");

        System.out.print("Enter date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        System.out.print("Enter time (HH:MM:SS): ");
        String time = scanner.nextLine();

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Enter amount: ");
        double amount = Double.parseDouble(scanner.nextLine());
        amount = -Math.abs(amount); // The amount entered these lines is made negative (because payment)

        Transaction transaction = new Transaction(date, time, description, vendor, amount);
        TransactionManager.saveTransaction(transaction);
        System.out.println("Payment recorded successfully!");
    }

    public void showLedger(Scanner scanner) {
        // Shows the Ledger menu
        boolean inLedgerMenu = true;

        // Shows the Ledger menu
        while (inLedgerMenu) {
            // We give options to our customer
            System.out.println("=== Ledger Menu ===");
            System.out.println("A) All Transactions");
            System.out.println("D) Deposits Only");
            System.out.println("P) Payments Only");
            System.out.println("R) Reports");
            System.out.println("H) Home");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().toUpperCase();
            List<Transaction> transactions = manager.getTransactions();

            //Creating switch cases for multiple options
            switch (choice) {
                case "A":
                    showTransactions(transactions);
                    break;
                case "D":
                    showTransactions(transactions, true);
                    break;
                case "P":
                    showTransactions(transactions, false);
                    break;
                case "R":
                    showReportsMenu(scanner);
                    break;
                case "H":
                    inLedgerMenu = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    //Reports Menu
    public void showReportsMenu(Scanner scanner) {
        boolean inReportsMenu = true;

        while (inReportsMenu) {
            System.out.println("==== Reports Menu ====");
            System.out.println("1) All Transactions");
            System.out.println("2) Deposits Only");
            System.out.println("3) Payments Only");
            System.out.println("4) Search by Vendor");
            System.out.println("0) Back to Ledger Menu");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine(); // User's selection input
            List<Transaction> transactions = manager.getTransactions(); // Memorized all proccess

            switch (choice) {
                case "1":
                    showTransactions(transactions);
                    break;
                case "2":
                    showTransactions(transactions, true);
                    break;
                case "3":
                    showTransactions(transactions, false);
                    break;
                case "4":
                    searchByVendor(scanner, transactions);
                    break;
                case "0":
                    inReportsMenu = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Printing(displayin) all information
    private void showTransactions(List<Transaction> transactions) {
        for (Transaction t : transactions) {
            //It takes each item (Transaction) in the transactions list in order.
            //It assigns that item to the variable t each time.
            System.out.println(t);
        }
    }

    //(true = deposits, false = payments)
    // Print the specific type of transaction (deposit or payment)
    private void showTransactions(List<Transaction> transactions, boolean showDeposits) {
        for (Transaction t : transactions) {
            if (showDeposits && t.getAmount() > 0) {
                // This method checks if the user wants to see a deposit or a payment.
                // If you want to see the deposit and the transaction is positive show
                System.out.println(t);
            } else if (!showDeposits && t.getAmount() < 0) {
                // If you want to see the payment and the transaction is negative show
                System.out.println(t);
            }
        }
    }

    // Search by vendor name
    private void searchByVendor(Scanner scanner, List<Transaction> transactions) {
        System.out.println("=== Search by Vendor ===");
        System.out.print("Enter vendor name: ");
        String vendorSearch = scanner.nextLine().toLowerCase(); //Destroying value of upper and lower cases

        boolean found = false; //We looking for vendor
        for (Transaction t : transactions) {
            if (t.getVendor().toLowerCase().contains(vendorSearch)) {
                //Contains part check seller, username
                //lower case command makes every case can eligible
                //get vendor do getting user name and vendor
                System.out.println(t);
                found = true;

                // This method takes the list of transactions and filters them to show only deposits or payments.
            }
        }

        if (!found) { //If we didnt found any vendor print that
            System.out.println("No transactions found for vendor: " + vendorSearch);
        }
    }
}
