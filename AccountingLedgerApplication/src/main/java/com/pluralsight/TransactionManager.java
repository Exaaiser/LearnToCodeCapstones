package com.pluralsight;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionManager {
    private List<Transaction> transactions;

    public TransactionManager() {
        transactions = new ArrayList<>(); // Creating Array List for memorized transactions
        loadTransactions(); // Read file while program starting
    }

    public void loadTransactions() {
        try {
            File file = new File("transactions.csv");
            if (!file.exists()) { //Checking file exist or not
                file.createNewFile(); // If we dont have a file crate it
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    String date = parts[0];
                    String time = parts[1];
                    String description = parts[2];
                    String vendor = parts[3];
                    double amount = Double.parseDouble(parts[4]);
                    transactions.add(new Transaction(date, time, description, vendor, amount));
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error: File cant found " + e.getMessage());
        }
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        saveTransaction(transaction);
    }

    public static void saveTransaction(Transaction transaction) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("transactions.csv", true))) {
            writer.println(transaction.getDate() + "|" +
                    transaction.getTime() + "|" +
                    transaction.getDescription() + "|" +
                    transaction.getVendor() + "|" +
                    transaction.getAmount());
        } catch (IOException e) {
            System.out.println("Error saving transaction: " + e.getMessage());
        }
    }
}
