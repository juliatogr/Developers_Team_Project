package application;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Class Input to control user input. (Scanner)
 */
public class Input {

	private static Input instance;
	private Scanner input;

	private Input() {
		input = new Scanner(System.in);
	}

	public static Input getInstance() { //Singleton pattern
		if (instance == null) {
			instance = new Input();
		}
		return instance;
	}

	public String askString(String message) {
		/**
		 * Method to ask a String, here we avoid special characters
		 * 
		 * @param message to show
		 * 
		 * @return string whitout special characters
		 **/
		String data = "";
		boolean ok = false;
		Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
		Matcher m;

		do {
			System.out.println(message);
			data = input.next();
			m = p.matcher(data);
			ok = !m.find(); // m.find() is true when there's a special character
			if (!ok) {
				System.out.println("Special characters are not allowed. Try it again.");
			}
		} while (!ok);

		return data;
	}

	public int askInt(String message) {
		/**
		 * Method to ask a Integer
		 * 
		 * @param message to show
		 * 
		 * @return an integer
		 **/
		int data = 0;
		boolean ok = false;
		do {

			try {
				System.out.println(message);
				data = input.nextInt();
				ok = true;
			} catch (InputMismatchException e) {
				System.out.println("Please enter an Integer");

			}
			input.nextLine(); //clean scanner

		} while (!ok);

		return data;
	}

	public double askDouble(String message) {
		/**
		 * Method to ask a Double
		 * 
		 * @param message to show
		 * 
		 * @return a double number
		 **/
		double dato = 0;
		boolean ok = false;
		do {

			try {
				System.out.println(message);
				dato = input.nextDouble();
				ok = true;
			} catch (InputMismatchException e) {
				System.out.println("Please enter a double");

			}
			input.nextLine(); //clean scanner
		} while (!ok);

		return dato;
	}

	public byte askByte(String message) {
		/**
		 * Method to ask a byte, for menu options
		 * 
		 * @param message to show
		 * 
		 * @return a byte
		 **/
		byte data = 0;
		boolean ok = false;
		do {

			try {
				System.out.println(message);
				data = input.nextByte();
				ok = true;
			} catch (InputMismatchException e) {
				System.out.println("Please enter a byte");

			}
			input.nextLine(); //clean scanner

		} while (!ok);

		return data;
	}

	public void closeScan() {
		input.close();
	}
}
