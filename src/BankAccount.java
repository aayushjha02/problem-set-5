public class BankAccount {
	private User user;
	private double balance;
	private int accountNumber;
	private String close;
	
	
	public BankAccount(int accountNumber, User user, double balance, String close) {
		this.user = user;
		this.balance = balance;
		this.accountNumber = accountNumber;
		this.close = close;
		
	}
	public void deposit(double depositAmount) {
		setBalance(depositAmount + getBalance());
	}
	public void withdraw(double withdrawAmount) {
		setBalance(getBalance() - withdrawAmount);
	}
	public void showBalance() {
		System.out.println("Your balance is $" + getBalance());
	}
	public void setBalance(double balanceInput) {
		balance = balanceInput;
	}
	public void setAccountNumber(int accountNumberInput) {
		accountNumber = accountNumberInput;
	}
	
	public double getBalance() {
		return balance;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	
	public void setUser(User input) {
		user = input;
	}
	public User getUser() {
		return user;
	}
	public boolean transfer(double transferamount, BankAccount accreceive) {
	
		if(transferamount > this.getBalance() || accreceive.getClose().equals("N")) {
			return false;
		}
		else {
			this.withdraw(transferamount);
			accreceive.deposit(transferamount);
			return true;
		}
	}
	public String getClose() {
		return close;
	}
	public boolean setClose(String input) {
		if(input.equals("N")) {
			close = "N";
			return true;
		}
		else {
			return false;
		}
	}
	
	
}
