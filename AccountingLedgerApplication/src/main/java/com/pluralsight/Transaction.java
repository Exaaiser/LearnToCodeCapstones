package com.pluralsight;

public class Transaction {
    private String date;
    private String time;
    private String description;
    private String vendor;
    private double amount;


   // Constructor we creating this for all codes
    public Transaction(String date, String time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
        // Not: In this class we just keep values
    }
//Getter for keepin all values and creating get commands
    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public String getVendor() {
        return vendor;
    }

    public double getAmount() {
        return amount;
    }

    // This method prepares the process object in a special format for printing to the screen.
//  alignment is done. Provides a regular view to the user
    public String toString() {
        return String.format("%s %s | %-20s | %-15s | %10.2f",
                date, time, description, vendor, amount);
        // Not: I did not put dollar sign cause If user want another money type them can use
    }
}
