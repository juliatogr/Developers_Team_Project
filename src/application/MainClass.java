package application;

import java.util.ArrayList;
import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) {
		ArrayList<Florist> florists = new ArrayList<Florist>();
	
		//Read products.txt & tickets.txt to recover all data
		
		boolean exit = false;
		
		while (!exit) {
			byte opt = mainMenu();
			
			switch(opt) {
			case 1: createFlorist(florists);
					break;
			case 2: addTree();
					break;
			case 3: addFlower();
					break;
			case 4: addDecoration();
					break;
			case 5: printStock();
					break;
			case 6: removeTree();
					break;
			case 7: removeFlower();
					break;
			case 8: removeDecoration();
					break;
			case 9: printStockQuantities();
					break;
			case 10: createTicket();
					break;
			case 11: printTickets();
					break;
			case 12: getTotalGain();
					break;
			case 0: exit = true; System.out.println("Bye!");
					break;
			}
		}

	}
	
	public static byte mainMenu() {
		final int MAX_OPTS = 12;
		byte opt = -1;
		
		while (opt < 0 || opt > MAX_OPTS) {
			System.out.println("Choose the desired option:");
			System.out.println("    1. Create a florist");
			System.out.println("    2. Add a tree");
			System.out.println("    3. Add a flower");
			System.out.println("    4. Add a decoration");
			System.out.println("    5. Print stock");
			System.out.println("    6. Remove a tree");
			System.out.println("    7. Remove a flower");
			System.out.println("    8. Remove a decoration");
			System.out.println("    9. Print stock quantities");
			System.out.println("    10. Create ticket");
			System.out.println("    11. Print old tickets");
			System.out.println("    12. Get total gain");
			System.out.println("    0. Exit");
		}
		return opt;
	}

	
	public static void createFlorist(ArrayList<Florist> florists) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce the name of the new florist:");
		
		String name = sc.nextLine();
		
		Florist f = findFlorist(florists, name);
		
		if (f == null) {
			florists.add(new Florist(name));
		} else {
			System.out.println("There exists another florist with name " + name);
		}
	}
	
	public static void addTree() {
		System.out.println("Mètode pendent de implementar");

	}
	public static void addFlower() {
		System.out.println("Mètode pendent de implementar");

	}
	public static void addDecoration() {
		System.out.println("Mètode pendent de implementar");

	}
	
	
	public static void removeTree() {
		System.out.println("Mètode pendent de implementar");

	}
	public static void removeFlower() {
		System.out.println("Mètode pendent de implementar");

	}
	public static void removeDecoration() {
		System.out.println("Mètode pendent de implementar");

	}
	
	public static void printStock() {
		System.out.println("Mètode pendent de implementar");

	}
	
	public static void printStockQuantities() {
		System.out.println("Mètode pendent de implementar");

	}
	
	public static void createTicket() {
		System.out.println("Mètode pendent de implementar");

	}
	
	public static void getTotalGain() {
		System.out.println("Mètode pendent de implementar");

	}
	public static void printTickets() {
		System.out.println("Mètode pendent de implementar");

	}
	
	public static Florist findFlorist(ArrayList<Florist> florists, String name) {
		
		Florist florist = null;
		boolean found = false;
		int counter = 0;
		int numFlorists = florists.size();
		
		while(!found && counter < numFlorists) {
			Florist f = florists.get(counter);
			if (f.getName().equalsIgnoreCase(name)) {
				florist = f;
				found = true;
			}
		}
		
		return florist;
	}

}
