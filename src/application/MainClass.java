package application;

import java.util.Scanner;

public class MainClass {

	@SuppressWarnings("null")
	public static void main(String[] args) {
		Florist florist = null;
	
		//Read products.txt & tickets.txt to recover all data
		
		// Check if there exists a florist. If not, we should create one.
		
		if (florist == null) {
			florist = createFlorist();
		}
		
		boolean exit = false;
		
		while (!exit) {
			byte opt = mainMenu();			
			
			switch(opt) {
			case 1: florist.addProduct();
					break;
			case 2: florist.printStock();
					break;
			case 3: florist.removeProduct();
					break;
			case 4: florist.printStockQuantities();
					break;
			case 5: florist.createTicket();
					break;
			case 6: florist.printTickets();
					break;
			case 7: florist.printTotalGain();
					break;
			case 0: exit = true; System.out.println("Bye!");
					break;
			}
		}

	}
	
	public static byte mainMenu() {
		final int MAX_OPTS = 7;
		byte opt = -1;
		
		Scanner sc = new Scanner(System.in);
		
		while (opt < 0 || opt > MAX_OPTS) {
			System.out.println("Choose the desired option:");
			System.out.println("    1. Add a product");
			System.out.println("    2. Print stock");
			System.out.println("    3. Remove a product");
			System.out.println("    4. Print stock quantities");
			System.out.println("    5. Create ticket");
			System.out.println("    6. Print old tickets");
			System.out.println("    7. Get total gain");
			System.out.println("    0. Exit");
			
			opt = sc.nextByte();
			
			if (opt < 0 || opt > MAX_OPTS) {
				System.out.println("This is not a valid option.");
			}
			
		}
		return opt;
	}

	
	public static Florist createFlorist() {

		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce the name of the new florist:");
		
		String name = sc.nextLine();
		
		return new Florist(name); 
	}	

}
