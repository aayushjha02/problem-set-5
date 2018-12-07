import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class will serve as the intermediary between our ATM program and
 * the database of BankAccounts. It'll be responsible for fetching accounts
 * when users try to login, as well as updating those accounts after any
 * changes are made.
 */
	

public class Database {
	private HashMap<Integer, BankAccount> data = new HashMap<Integer, BankAccount>();
	private int lastBANumber;
	//private String filePath = "C:\\Users\\Aayush\\eclipse-workspace\\pset5\\src\\";
	public void makeDatabase() throws FileNotFoundException, IOException {
		
		try(BufferedReader br = new BufferedReader(new FileReader(/* filePath */"accounts-db.txt"))) {
 			String line;
 			while((line = br.readLine()) != null) {
 				int size[] = {9, 4, 15, 20, 15, 8, 10, 30, 30, 2, 5, 1};
 				ArrayList<String> account = new ArrayList<String>();
 				int count = 0;
 				for(int i = 0; i<12; i++) {
 					account.add(line.substring(count, count+size[i]));
 					count = count + size[i];
 				}
 				User user = new User(account.get(1), account.get(3), account.get(4), account.get(5), account.get(6), account.get(7), account.get(8), account.get(9), account.get(10));
 				BankAccount bankAccount = new BankAccount(Integer.parseInt(account.get(0)), user, Double.parseDouble(account.get(2)), account.get(11));
 				data.put(bankAccount.getAccountNumber(), bankAccount);
 				lastBANumber = bankAccount.getAccountNumber();
 			}
	 	}
	 }
	public int getNextBankAccountNumber() {
		lastBANumber += 1;
		return lastBANumber;
	}
	public BankAccount getAccount(int bankAccountNumber) {
		BankAccount hold = data.get(bankAccountNumber);
		if (hold.getClose().equals("N")) {
			return null;
		}
		else {
			return hold;
		}
	}
	public static String padRight(String s, int n) {
	     return String.format("%1$-" + n + "s", s);  
	}
	public String convertAccountToString(BankAccount input) {
		String PIN = input.getUser().getPIN();
		String last_name = padRight(input.getUser().getLast_Name(), 20);
		String first_name = padRight(input.getUser().getFirst_Name(), 15);
		String dob = input.getUser().getdob();
		String phone = input.getUser().getPhone();
		String address = padRight(input.getUser().getAddress(), 30);
		String city = padRight(input.getUser().getCity(), 30);
		String state = padRight(input.getUser().getState(), 2);
		String postalCode = input.getUser().getPostal_Code();
		String BankAccountNumber = Integer.toString(input.getAccountNumber());
		String balance = padRight(Double.toString(input.getBalance()), 15);
		String close = input.getClose();
		
		String total = BankAccountNumber + PIN + balance + last_name + first_name + dob + phone + address + city + state + postalCode + close;
		return total; 
	}
	
	public boolean addBankAccount(BankAccount input) {
		try(FileWriter fw = new FileWriter(/* filePath */"accounts-db.txt", true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
			    out.println(convertAccountToString(input));
			    data.put(input.getAccountNumber(), input);
			} catch (IOException e) {
			    //exception handling left as an exercise for the reader
			}
		return true;
	}
	public boolean updateAccounts() {
		try(FileWriter fw = new FileWriter(/* filePath */"accounts-db.txt", false);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))  
			{
			for (Map.Entry<Integer, BankAccount> entry : data.entrySet()) {
			    Integer key = entry.getKey();
			    BankAccount value = entry.getValue();
			    out.println(convertAccountToString(value));
			}
			    
			} catch (IOException e) {
			    //exception handling left as an exercise for the reader
			}
		return true;
	}
}

