package com.pluralsight;

import java.util.Scanner;

public class FinancialApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Creating scanner for starting app
        Ledger ledger = new Ledger(); // We got ledger class here
        boolean running = true; // Checking program working or not

        while (running) { // We want program should be in eterner cycle
            System.out.println("==== Home Screen ====");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment (Debit)");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            System.out.print("Choose an option: ");
            // Creating main menu with keyboard letter

            String choice = scanner.nextLine().toUpperCase(); // Ignoring upper-lower case statements for comfortable using

            switch (choice) {
                case "D": //Adding deposit
                    ledger.addDeposit(scanner);
                    break;
                case "P"://Make Payment
                    ledger.makePayment(scanner);
                    break;
                case "L"://Show Ledger
                    ledger.showLedger(scanner);
                    break;
                case "X"://Exiting App
                    running = false;
                    System.out.println("Exiting the application...");
                    break;
                default:// If customer make different and meaningless choice we want them make choice again
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

