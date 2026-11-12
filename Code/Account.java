import java.util.ArrayList;
import java.util.List;

public class Account {

    private double balance;
    private List<String> transactions = new ArrayList<>();

    // Constructor
    public Account(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }
    // Deposit money
    public void deposit(String customer, double amount) {
        if (amount <= 0) {
            transactions.add(customer + ": Invalid deposit.");
            return;
        }
        double currentBalance = balance;
        balance = currentBalance + amount;
        transactions.add(customer + " deposited KSh "
                + amount + ". New balance: KSh " + balance);
    }

    // Withdraw money
    public void withdraw(String customer, double amount) {
        if (amount <= 0) {
            transactions.add(customer + ": Invalid withdrawal.");
            return;
        }
        double currentBalance = balance;
        if (currentBalance >= amount) {
            balance = currentBalance - amount;
            transactions.add(customer + " withdrew KSh "
                    + amount + ". New balance: KSh " + balance);
        } else {
            transactions.add(customer
                    + ": Withdrawal failed. Insufficient funds.");
        }
    }

    // Transfer money
    public void transfer(String customer, Account recipient, double amount) {
        if (amount <= 0 || recipient == this) {
            transactions.add(customer + ": Invalid transfer.");
            return;
        }

        if (balance >= amount) {
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            double currentBalance = balance;
            balance = currentBalance - amount;
            recipient.balance += amount;
            transactions.add(customer + " transferred KSh "
                    + amount + " to another account.");
        } else {

            transactions.add(customer
                    + ": Transfer failed. Insufficient funds.");
        }
    }

    // Check account balance
    public double checkBalance(String customer) {
        transactions.add(customer
                + " checked balance: KSh " + balance);
        return  balance;
    }

    // Display transaction history
    public void displayTransactions() {
        System.out.println("\nTransaction History:");
        for (String transaction : transactions) {
            System.out.println(transaction);
        }
    }


}