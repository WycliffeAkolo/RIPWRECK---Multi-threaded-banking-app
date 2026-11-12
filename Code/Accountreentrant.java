import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Accountreentrant {

    private double balance;
    private List<String> transactions = new ArrayList<>();
    private ReentrantLock lock = new ReentrantLock();

    // Constructor
    public Accountreentrant(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }
    // Deposit money
    public void deposit(String customer, double amount) {
        System.out.println("deposit: " + customer);
        lock.lock();
        try{
            if (amount <= 0) {
                transactions.add(customer + ": Invalid deposit.");
                return;
            }
            double currentBalance = balance;
            balance = currentBalance + amount;
            transactions.add(customer + " deposited KSh " + amount + ". New balance: KSh " + balance);
        }finally{
            System.out.println("done deposit: " + customer);
            lock.unlock();
        }
    }

    // Withdraw money
    public void withdraw(String customer, double amount) {
        System.out.println("withdraw: "+customer);
        lock.lock();
        try{
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
            }catch(Exception e){
                e.printStackTrace();
        }finally{
            System.out.println("done withdraw: "+customer);
            lock.unlock();
        }
    }

    // Transfer money
    public void transfer(String customer, Accountreentrant recipient, double amount) {
        System.out.println("transfer: "+customer);

        lock.lock();
        try{
            if (amount <= 0 || recipient == this) {
                transactions.add(customer + ": Invalid transfer.");
                return;
            }

            if (balance >= amount) {
                double currentBalance = balance;
                balance = currentBalance - amount;
                recipient.balance += amount;
                transactions.add(customer + " transferred KSh "
                        + amount + " to another account.");
            } else {

                transactions.add(customer
                        + ": Transfer failed. Insufficient funds.");
            }
        }finally{
            System.out.println("done transfer: "+customer);
            lock.unlock();
        }
    }

    // Check account balance
    public double checkBalance(String customer) {
        System.out.println("checkbal: "+customer);
        lock.lock();
        try{
            transactions.add(customer
                    + " checked balance: KSh " + balance);
            return  balance;

        }finally{
            System.out.println("done checkbal: "+customer);
            lock.unlock();
        }
    }

    // Display transaction history
    public void displayTransactions() {
            System.out.println("\nTransaction History:");
            for (String transaction : transactions) {
                System.out.println(transaction);
            }
    }


}