import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This class has only one responsibility: start the ATM program!
 */
public class Tester {
	
	/**
	 * Main method.
	 * 
	 * @param args
	 */
	
	public static void main(String[] args) {
		
		Database db = new Database();
		try {
			db.makeDatabase();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ATM atm1 = new ATM(db);
		atm1.showMenu();
		
	}
}
