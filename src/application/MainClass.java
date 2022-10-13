package application;

import java.util.Scanner;

public class MainClass {

	@SuppressWarnings("null")
	public static void main(String[] args) {
		Florist florist = null;
	
		//Read products.txt & tickets.txt to recover all data
		
		// Check if there exists a florist. If not, we should create one.
		
		if (florist == null) {
			createFlorist(florist);
		}
		
		boolean exit = false;
		
		while (!exit) {
			byte opt = mainMenu();			
			
			switch(opt) {
			case 1: florist.addTree();
					break;
			case 2: florist.addFlower();
					break;
			case 3: florist.addDecoration();
					break;
			case 4: florist.printStock();
					break;
			case 5: florist.removeTree();
					break;
			case 6: florist.removeFlower();
					break;
			case 7: florist.removeDecoration();
					break;
			case 8: florist.printStockQuantities();
					break;
			case 9: florist.createTicket();
					break;
			case 10: florist.printTickets();
					break;
			case 11: florist.printTotalGain();
					break;
			case 0: exit = true; System.out.println("Bye!");
					break;
			}
		}

	}
	
	public static byte mainMenu() {
		final int MAX_OPTS = 11;
		byte opt = -1;
		
		while (opt < 0 || opt > MAX_OPTS) {
			System.out.println("Choose the desired option:");
			System.out.println("    1. Add a tree");
			System.out.println("    2. Add a flower");
			System.out.println("    3. Add a decoration");
			System.out.println("    4. Print stock");
			System.out.println("    5. Remove a tree");
			System.out.println("    6. Remove a flower");
			System.out.println("    7. Remove a decoration");
			System.out.println("    8. Print stock quantities");
			System.out.println("    9. Create ticket");
			System.out.println("    10. Print old tickets");
			System.out.println("    11. Get total gain");
			System.out.println("    0. Exit");
		}
		return opt;
	}

	
	public static void createFlorist(Florist f) {

		if (f == null) {
			Scanner sc = new Scanner(System.in);
			System.out.println("Introduce the name of the new florist:");
			
			String name = sc.nextLine();
			
			f = new Florist(name);
		} else {
			System.out.println("There already exists a florist");
		}
	}	

}
