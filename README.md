A small banking Java application used to show multi threading and concurrency issues in Java and how to takle them

## Tour
1. Account.java - Implementation without any race condition handling. The script runs the program over and over in a loop until it catches a race  condition to demonstrate
2. Accountreentrant.java - an implementation using reentrant locks
3. Accountsync.java - an implementation using synchronized methods

## How to use
In the main class, change the class of the `shared_account` and `recipient_account` objects to one of the 3 classes above then use the script to see if it catches a race condition. (After running for 3 minutes, assume no race condition is in the implementation)
