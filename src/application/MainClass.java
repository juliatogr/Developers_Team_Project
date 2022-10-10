package application;

import java.util.ArrayList;
import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) {
		ArrayList<Florist> florists = new ArrayList<Florist>();
	
		
		boolean exit = false;
		
		while (!exit) {
			byte opt = mainMenu();
			
			switch(opt) {
			case 1: addProducts();
					break;
			case 0: exit = true; System.out.println("Bye!");
					break;
			}
		}

	}
	
	public static byte mainMenu() {
		final int MAX_OPTS = 3;
		byte opt = -1;
		
		while (opt < 0 || opt > MAX_OPTS) {
			System.out.println("Choose the desired option:");
			System.out.println("    1. Add products to the stock");
			System.out.println("    2. Remove products of the stock");
			System.out.println("    3. Add products to a ticket");
			System.out.println("    4. Remove products of a ticket");
			System.out.println("    1. Add products to the stock");
			System.out.println("    2. Remove products of the stock");
			System.out.println("    3. Add products to a ticket");
			System.out.println("    4. Remove products of a ticket");
			System.out.println("    0. Exit");
		}
		return opt;
	}
	
	public static void addProducts() {
		System.out.println("Mètode pendent de implementar");

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
