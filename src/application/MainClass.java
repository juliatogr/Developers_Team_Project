package application;

import java.util.Scanner;

public class MainClass {

	private static Input input = Input.getInstance();

	public static void main(String[] args) {
		Florist florist = null;

		// Read products.txt & tickets.txt to recover all data
		// Maybe to florist.txt too to know its name

		// Check if there exists a Florist. If not, we should create one.

		if (florist == null) {
			florist = createFlorist();
		}

		boolean exit = false;

		while (!exit) {
			byte opt = mainMenu(); // ask for an option

			// make the corresponding action depending on the selected option
			switch (opt) {
			case 1:
				florist.addProduct();
				break;
			case 2:
				florist.printStock();
				break;
			case 3:
				florist.removeProduct();
				break;
			case 4:
				florist.printStockQuantities();
				break;
			case 5:
				florist.createTicket();
				break;
			case 6:
				florist.printTickets();
				break;
			case 7:
				florist.printTotalGain();
				break;
			case 0:
				exit = true;
				input.closeScan(); // we will not use it anymore
				System.out.println("Bye!");

				break;
			}
		}
	}

	public static byte mainMenu() {
		/*
		 * Main menu of the application.
		 * 
		 * @return selected valid option
		 */

		final int MAX_OPTS = 7;
		byte opt = -1;

		while (opt < 0 || opt > MAX_OPTS) {
			opt = input.askByte(
					"****Main menu****\nChoose the desired option:\n    1. Add a product\n    2. Print stock\n    3. Remove a product\n    4. Print stock quantities\n    5. Create ticket\n    6. Print old tickets\n    7. Get total gain\n    0. Exit");

			if (opt < 0 || opt > MAX_OPTS) {
				System.out.println("This is not a valid option.");
			}
		}

		return opt;
	}

	public static Florist createFlorist() {
		/*
		 * Returns a Florist with name chosen by the user
		 */

		String name = input.askString("Introduce the name of the new florist:");

		return new Florist(name);
	}

	public static Input getInput() {
		return input;
	}

}
