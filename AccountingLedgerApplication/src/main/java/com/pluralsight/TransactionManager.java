package com.pluralsight;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// TransactionManager class is responsible for loading transactions from files, storing them in log(Mean memory)
// and adding new transactions and saving them to the file

public class TransactionManager {
    //This list storage all process in memory
    private List<Transaction> transactions;

    // Constructor:
    public TransactionManager() {
        transactions = new ArrayList<>(); // Creating Array List for temporary memorized transactions
        loadTransactions(); // Read file while program starting
    }


    // Getter so you can view the process list of external classes.
    public List<Transaction> getTransactions() {
        return transactions;
    }

    // Reads all transactions from the CSV file and adds them to the transaction list.
    public void loadTransactions() {
        transactions = new ArrayList<>();
        try {
            File file = new File("transactions.csv");
            if (!file.exists()) { //Checking file exist or not ==
                file.createNewFile(); // If we dont have a file crate it (Why its yellow I coldnt understood)
            }
            // We read the file line by line with BufferedReader.
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            // We just wanna separate all lines with |
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");// We divide that and If we want we can use patternQuotes

                if (parts.length == 5) {
                    String date = parts[0];
                    String time = parts[1];
                    String description = parts[2];
                    String vendor = parts[3];
                    double amount = Double.parseDouble(parts[4]); //Convert double to String
                    Transaction transaction = new Transaction(date, time, description, vendor, amount);
                    transactions.add(transaction); //We try adding new created file to file
                }
            }
            reader.close(); //We close reading app while reading process end
        } catch (IOException e) {
            // If an error occurs during file operations, a message is displayed to the user
            System.out.println("Error: File cant found " + e.getMessage());
        }
    }
    // When a new transaction is added it is written to csv
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        saveTransaction(transaction); //Save memory
    }


    public static void saveTransaction(Transaction transaction) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("transactions.csv", true))) {
            //FileWriter adding last part of file ist not override to file.
            //PrintWriter using fot printing something in file

            //We write all data separated by | thing
            writer.println(transaction.getDate() + "|" +
                    transaction.getTime() + "|" +
                    transaction.getDescription() + "|" +
                    transaction.getVendor() + "|" +
                    transaction.getAmount());
        } catch (IOException e) {
            // If an error occurs during file operations, a message is displayed to the user
            System.out.println("Error saving transaction: " + e.getMessage());
        }
    }
}

