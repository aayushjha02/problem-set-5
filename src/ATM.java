import java.util.Scanner;

public class ATM {

	private Database database;
	public ATM(Database database) {
		this.database = database;
	}
	public static String padRight(String s, int n) {
	     return String.format("%1$-" + n + "s", s);  
	}
	
	public void showMenu() {
		
		Scanner in = new Scanner(System.in);
		int choiceAdvanced = 100;
		int choiceSimple = 0;
		String PINCheck;
		int bankAccountNumCheck;
		System.out.println("Hello welcome to Aayush's bank!");
		do {
			System.out.println("Would you like to open an account, login to an existing account, or quit? Press 1, 2, or 3 for each choice respectively.");
			choiceSimple = Integer.parseInt(in.nextLine());
			if(choiceSimple == 1) {
				String PINinput;
				String last_nameInput;
				String first_nameInput;
				String dobInput;
				String phoneInput;
				String addressInput;
				String cityInput;
				String stateInput;
				String postalCodeInput;
				do  {
					System.out.println("What would you like your PIN number to be? Only 4 numbers long.");
					PINinput = in.nextLine();
					if (PINinput.length() != 4) {
						System.out.println("Sorry that is an incorrect amount of characters.!");
					}
				} while (PINinput.length() != 4);
				do  {
					System.out.println("What is your last name? First 20 characters only!");
					last_nameInput = in.nextLine();
					if (last_nameInput.length() > 20) {
						System.out.println("Sorry that is too long!");
					}
				} while (last_nameInput.length() > 20);
				do  {
					System.out.println("What is your first name? First 15 characters only!");
					first_nameInput = in.nextLine();
					if (first_nameInput.length() > 15) {
						System.out.println("Sorry that is too long!");
					}
				} while (first_nameInput.length() > 15);
				do  {
					System.out.println("What is your date of birth? Please input it in the format YYYYMMDD!");
					dobInput = in.nextLine();
					if (dobInput.length() != 8) {
						System.out.println("Sorry that is not the correct date of birth format.");
					}
				} while (dobInput.length() != 8);
				do  {
					System.out.println("What is your phone number?");
					phoneInput = in.nextLine();
					if (phoneInput.length() != 10) {
						System.out.println("Sorry that is an invalid phone number!");
					}
				} while (phoneInput.length() != 10);
				do  {
					System.out.println("What is your address? First 30 characters only!");
					addressInput = in.nextLine();
					if (addressInput.length() > 30) {
						System.out.println("Sorry that is too long!");
					}
				} while (addressInput.length() > 30);
				do  {
					System.out.println("What city do you live in? First 30 characters only!");
					cityInput = in.nextLine();
					if (cityInput.length() > 30) {
						System.out.println("Sorry that is too long!");
					}
				} while (cityInput.length() > 30);
				do  {
					System.out.println("What state do you live in? Please put the abbrievation in! (New Jersey would be NJ)");
					stateInput = in.nextLine();
					if (stateInput.length() != 2) {
						System.out.println("Sorry that is an invalid state!");
					}
				} while (stateInput.length() != 2);
				do  {
					System.out.println("What is your postal code? It should only be 5 characters!");
					postalCodeInput = in.nextLine();
					if (postalCodeInput.length() != 5) {
						System.out.println("Sorry that is not a valid postal code!");
					}
				} while (postalCodeInput.length() != 5);
				int BANewNumber = database.getNextBankAccountNumber();
				System.out.println("Your bank account number will be " + BANewNumber);
				
				User newUser = new User(PINinput, last_nameInput, first_nameInput, dobInput, phoneInput, addressInput, cityInput, stateInput, postalCodeInput);
				BankAccount newAccount = new BankAccount(BANewNumber, newUser, 0.00, "Y");
				database.addBankAccount(newAccount);
				
			}
			else if(choiceSimple == 2) {
				System.out.println("Please enter your bank account number.");
				bankAccountNumCheck = Integer.parseInt(in.nextLine());
				System.out.println("Please enter your PIN.");
				PINCheck = in.nextLine(); 
				BankAccount account1 = database.getAccount(bankAccountNumCheck);
				if(database.getAccount(bankAccountNumCheck) == null) {
					System.out.println("This account has been closed");
				}
				else if(account1.getUser().getPIN().equals(PINCheck)) {
					while (choiceAdvanced != 8) {
						System.out.println("Please press 1 to withdraw money, 2 to deposit money, 3 to show the balance in your account"
								+ " 4 to transfer money to another account, 5 to view personal information, \n 6 to update personal information"
								+ ", 7 to close your account, or 8 to exit");
						choiceAdvanced = Integer.parseInt(in.nextLine());
						if (choiceAdvanced == 1) {
							System.out.println("Enter how much you would like to withdraw.");
							double withdrawAmount_ = in.nextDouble();
							if(withdrawAmount_ <= account1.getBalance() ) {
								account1.withdraw(withdrawAmount_);
								System.out.println("Your balance is now $" + account1.getBalance());
								database.updateAccounts();
							}
							else {
								System.out.println("You tried to withdraw more money than you have in your bank account. Sorry!");
								System.out.println("Your balance is $" + account1.getBalance());
							}
						}
						else if (choiceAdvanced == 2) {
							System.out.println("Enter how much you would like to deposit.");
							double depositAmount_ = in.nextDouble();
							account1.deposit(depositAmount_);
							System.out.println("Your balance is now $" + account1.getBalance());
							database.updateAccounts();
						}
						else if(choiceAdvanced == 3) {
							System.out.println("Your balance is $" + account1.getBalance());
						}
						else if(choiceAdvanced == 4) {
							System.out.println("Please enter the amount of money you would like to transfer!");
							double transferamount = in.nextDouble();
							System.out.println("Please enter the bank account number in which the money will be transferred into!");
							int BANuminput = Integer.parseInt(in.nextLine());
							BankAccount accreceive = database.getAccount(BANuminput);
							if(account1.transfer(transferamount, accreceive) == false) {
								System.out.println("Sorry there is not enough money in your account to transfer that amount of money to another account.");
							}
							else {
								System.out.println("Transfer complete! Your balance is now " + account1.getBalance());
								database.updateAccounts();
							}
							
						}
						else if(choiceAdvanced == 7) {
							System.out.println("Are you sure you would like to close your account? Type 'N' if you would like to close your account");
							String closeChoice = in.nextLine();
							if(account1.setClose(closeChoice) == true) {
								System.out.println("Your acccount has been closed");
								database.updateAccounts();
								break;
							}
							else if(account1.setClose(closeChoice) == false) {
								System.out.println("Your account has not been closed.");
							}
						}
						else if(choiceAdvanced == 5) {
								System.out.println("Your name is " + account1.getUser().getFirst_Name() + " " + account1.getUser().getLast_Name());
								System.out.println("Your date of birth is " + account1.getUser().getdob().substring(6,8) + "/" + account1.getUser().getdob().substring(4,6) + "/" + account1.getUser().getdob().substring(0,4));
								System.out.println("Your phone number is " + account1.getUser().getPhone());
								System.out.println("Your street address is " + account1.getUser().getAddress());
								System.out.println("Your city is " + account1.getUser().getCity());
								System.out.println("Your state is " + account1.getUser().getState());
								System.out.println("Your postal code is " + account1.getUser().getPostal_Code());
								System.out.println(" \n ");
						}
						else if(choiceAdvanced == 6) {
							int personalInfoChoice = 100;
							do {
								System.out.println("What personal information would you like to update? Enter 1 for phone number, "
										+ "2 for street address, 3 for city, 4 for state, 5 for postal code, 6 for PIN, and 7 to exit");  
								personalInfoChoice = Integer.parseInt(in.nextLine());
								if (personalInfoChoice == 1) {
									String input;
									do {
										System.out.println("What is your new phone number? Only 10 characters!");
										input = in.nextLine();
									} while(input.length() != 10);
									input = padRight(input, 10);
									account1.getUser().setPhone(input);
								}
								else if (personalInfoChoice == 2) {
									String input;
									do {
										System.out.println("What is your new street address? Only 30 characters please!");
										input = in.nextLine();
									} while(input.length() > 30);
									input = padRight(input, 30);
									account1.getUser().setAddress(input);
								}
								else if (personalInfoChoice == 3) {
									String input;
									do {
										System.out.println("What is your new city? Only 30 characters please!");
										input = in.nextLine();
									} while(input.length() > 30);
									input = padRight(input, 30);
									account1.getUser().setAddress(input);
								}
								else if (personalInfoChoice == 4) {
									String input;
									do {
										System.out.println("What is your new state? Two character abbrieviation please!");
										input = in.nextLine();
									} while(input.length() != 2);
									input = padRight(input, 2);
									account1.getUser().setState(input);
								}
								else if (personalInfoChoice == 5) {
									String input;
									do {
										System.out.println("What is your new postal code?");
										input = in.nextLine();
									} while(input.length() != 5);
									input = padRight(input, 5);
									account1.getUser().setPostal_Code(input);
								}
								else if (personalInfoChoice == 6) {
									String input;
										System.out.println("Please enter your current PIN to confirm your identity.");
										String PINChangeCheck = in.nextLine();
										if(PINChangeCheck == account1.getUser().getPIN() ) {
											System.out.println("PIN check confirmed, please enter the new PIN you would like. 4 characters only");
											input = in.nextLine();
											while(input.length() != 4) {
												System.out.println("That PIN is an incorrect amount of characters.");
												input = in.nextLine();
											}
											input = padRight(input, 4);
											account1.getUser().setPIN(input);
										}
										else {
											System.out.println("PIN's do not match");
										}
								}
								database.updateAccounts();	
							}while(personalInfoChoice != 7);
							
						}
					}
				}
				else {
					System.out.println("Sorry that PIN number is incorrect for the given bank account number.");
				}
			}
		} while(choiceSimple != 3);
		database.updateAccounts();
		System.out.println("Thank you for visiting!");
		in.close();
	}
}
