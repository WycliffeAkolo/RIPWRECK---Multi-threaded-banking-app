
public class Main {
    public static void main(String[] args) {
    	Account shared_account = new Account(25000);
    	Account recipient_account = new Account(0);


        Thread customerA = new Thread(()->{
			shared_account.withdraw("A", 20000);
		});
        Thread customerB = new Thread(()->{
			shared_account.deposit("B", 15000);
		});
        Thread customerC = new Thread(()->{
			shared_account.transfer( "C", recipient_account, 25000);
		});
        Thread customerD = new Thread(()->{
			shared_account.withdraw("D", 25000);
		});
        Thread customerE = new Thread(()->{
			shared_account.withdraw("E", 10000);
		});
        Thread customerF = new Thread(()->{
			double balance = shared_account.checkBalance("F");
			System.out.println("Account balance: "+balance);

		});

		try {
			customerA.start();
			customerB.start();
			customerC.start();
			customerD.start();
			customerE.start();
			customerF.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	

		try {
			customerA.join();
			customerB.join();
			customerC.join();
			customerD.join();
			customerE.join();
			customerF.join();

		} catch (Exception e) {
			e.printStackTrace();
		}
		shared_account.displayTransactions();

		System.out.println("Final Account Balance: "+shared_account.getBalance());
    }
}
