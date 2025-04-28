package com.pluralsight;

import java.util.Scanner;

public class Ledger {

    // Ledger ekranında işlem yapacağımız class

    public void addDeposit(Scanner scanner) {
        System.out.println("=== Add Deposit ===");

        System.out.print("Enter date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        System.out.print("Enter time (HH:MM:): ");
        String time = scanner.nextLine();

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Enter amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        // Kullanıcıdan alınan bilgilerle yeni bir Transaction nesnesi oluşturuyoruz
        Transaction transaction = new Transaction(date, time, description, vendor, amount);

        // TransactionManager sınıfını kullanarak dosyaya kaydediyoruz
        TransactionManager.saveTransaction(transaction);

        System.out.println("Deposit recorded successfully!");
    }

    public void makePayment(Scanner scanner) {
        System.out.println("=== Make Payment (Debit) ===");

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

        amount = -Math.abs(amount); // Ödeme olduğu için miktarı negatif yapıyoruz

        // Kullanıcıdan alınan bilgilerle yeni bir Transaction nesnesi oluşturuyoruz
        Transaction transaction = new Transaction(date, time, description, vendor, amount);

        // TransactionManager sınıfını kullanarak dosyaya kaydediyoruz
        TransactionManager.saveTransaction(transaction);

        System.out.println("Payment recorded successfully!");
    }

    public void showLedger(Scanner scanner) {
        System.out.println("=== Ledger Screen ===");
        // Burayı ilerleyen adımlarda dolduracağız
        // Kullanıcıya "A) All, D) Deposits, P) Payments, R) Reports, H) Home" gibi seçenekler sunacağız
    }

    public void showReportsMenu(Scanner scanner) {
        boolean inReportsMenu = true;

        while (inReportsMenu) {
            System.out.println("==== Reports Menu ====");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Back");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

        }
    }
}
