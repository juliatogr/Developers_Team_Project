package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

/*
 * Class TxtPersistence to read/write Stock and Ticket files
 */
public class TxtPersistence {

	private static TxtPersistence instance;

	public static TxtPersistence getInstance() { // Singleton pattern
		if (instance == null) {
			instance = new TxtPersistence();
		}
		return instance;
	}

	private TxtPersistence() {

	}

	public void writeTxt(String fileName, ArrayList<?> objects) { // need to control if its ONLY ticket or
																	// product class
		/*
		 * Method to write Stock or Ticket files
		 * 
		 * @param fileName - path to write file.
		 * 
		 * @param objects - ArrayList of tickets or products to write
		 */
		boolean ticketFormat = false;
		if (objects.get(0).getClass().getSimpleName().equalsIgnoreCase("ticket")) {
			ticketFormat = true;
		}

		FileWriter file;
		BufferedWriter output = null;

		try {
			file = new FileWriter(fileName);
			output = new BufferedWriter(file);

			for (Object object : objects) {
				if (ticketFormat) {
					Ticket ticket = (Ticket) object;
					output.write(ticket.toStringTxt());
				} else {
					Product product = (Product) object;
					output.write(product.toStringTxt());
				}
				output.newLine();
			}
			output.flush(); // Send info

		} catch (IOException e) {

			System.out.println("Write buffer exception");
		} finally {
			try {
				if (output != null) {
					output.close();// Closes the writer
				}

			} catch (IOException e) {

				System.out.println("Closing buffer exception");
			}
		}

	}

	public ArrayList<Ticket> readTickets(String fileName, ArrayList<Product> products) {

		/*
		 * Method to read Ticket file
		 * 
		 * @param fileName - path to read file.
		 * 
		 * @param products - we need the arraylist of products to get their data because
		 * the ticket file only contains the product id
		 * 
		 * @returns Arraylist of tickets whit all info of products ( the product objects
		 * contained in the Treemap of the ticket class refer to the same memory space
		 * as in the product array)
		 */
		// format : id;date;price_idproduct;quantity_idproduct;quantity.....
		// date format: yyyyy-mm-ddThh:mm:ss there's a T between date and time

		FileReader fr = null;
		BufferedReader bf = null;
		String line = "";
		TreeMap<Product, Integer> productsList = new TreeMap<Product, Integer>();
		int lineNum = 0;
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();

		try {
			fr = new FileReader(fileName);
			bf = new BufferedReader(fr);
			StringTokenizer allTicket;
			StringTokenizer item;

			while ((line = bf.readLine()) != null) {
				try {
					lineNum++;
					allTicket = new StringTokenizer(line, "_");

					item = new StringTokenizer(allTicket.nextToken(), ";");

					if (item.countTokens() != 3) { // ticketId;date;price

						throw new InputMismatchException();
					}

					int ticketId = Integer.parseInt(item.nextToken());
					LocalDateTime date = LocalDateTime.parse(item.nextToken());
					double ticketPrice = Double.parseDouble(item.nextToken());

					while (allTicket.hasMoreTokens()) {
						item = new StringTokenizer(allTicket.nextToken(), ";");

						if (item.countTokens() != 2) { // idProduct;quantity

							throw new InputMismatchException();
						}

						int productId = Integer.parseInt(item.nextToken());
						int quantity = Integer.parseInt(item.nextToken());
						products.stream().filter((Product p) -> {
							return p.getId() == productId;

						}).findFirst().ifPresentOrElse(p -> productsList.put(p, quantity), () -> {
							throw new ProductNotFound(); // if we don't find product by id in stock
						});

					}

					tickets.add(new Ticket(ticketId, date, ticketPrice, productsList));

				} catch (NumberFormatException e) {// string ->double
					System.out.println(
							"Something went wrong (string as a number) reading the file Ticket in line: " + lineNum);

				} catch (ProductNotFound e) {
					System.out.println(
							"One ticket contains wrong data of product (not found in stock) in line: " + lineNum);

				} catch (InputMismatchException e) { // string ->int
					System.out.println("Wrong format or wrong field in line: " + lineNum + " in ticket file");
				}
			}

		} catch (FileNotFoundException e) {

			System.out.println("Wrong path of Ticket File");

		} catch (IOException e) {

			System.out.println("Something went wrong reading ticket file in line:" + lineNum);
		} finally {
			if (bf != null) {
				try {
					bf.close();
				} catch (IOException e) {
					System.out.println("Something went wrong closing the buffer (ReadTickets)");
				}
			}
		}

		return tickets;

	}

	public ArrayList<Product> readStock(String fileName) {
		/*
		 * Method to read Stock file
		 * 
		 * @param fileName - path to read file.
		 * 
		 * @returns Arraylist of products readed.
		 */
		FileReader fr = null;
		BufferedReader bf = null;
		String line = "";
		String type = "";
		int lineNum = 0;
		ArrayList<Product> products = new ArrayList<Product>();

		try {
			fr = new FileReader(fileName);
			bf = new BufferedReader(fr);
			StringTokenizer st;

			while ((line = bf.readLine()) != null) {
				try {
					st = new StringTokenizer(line, ";");
					lineNum++;

					if (st.countTokens() != 6) { // if we have more tokens this data is corrupt.
													// type;id;name;price;quantity;otherAtribute

						throw new InputMismatchException();
					}

					type = st.nextToken();

					switch (type) {
					case "T":

						int treeId = Integer.parseInt(st.nextToken());
						String treeName = st.nextToken();
						Double treePrice = Double.valueOf(st.nextToken());
						int treeQuantity = Integer.parseInt(st.nextToken());
						double treeHeight = Double.valueOf(st.nextToken());
						products.add(new Tree(treeId, treeName, treePrice, treeQuantity, treeHeight));

						break;
					case "D":

						int decorationId = Integer.parseInt(st.nextToken());
						String decorationName = st.nextToken();
						Double decorationPrice = Double.valueOf(st.nextToken());
						int decorationQuantity = Integer.parseInt(st.nextToken());
						String decorationMaterialString = st.nextToken();
						Decoration.MaterialType decorationMaterial;

						if (decorationMaterialString.equalsIgnoreCase("WOOD")) {
							decorationMaterial = Decoration.MaterialType.WOOD;
						} else if (decorationMaterialString.equalsIgnoreCase("PLASTIC")) {
							decorationMaterial = Decoration.MaterialType.PLASTIC;
						} else {
							throw new InputMismatchException(); // we can't create decoration object if we don't
																// know
																// material
						}

						products.add(new Decoration(decorationId, decorationName, decorationPrice, decorationQuantity,
								decorationMaterial));

						break;
					case "F":

						int flowerId = Integer.parseInt(st.nextToken());
						String flowerName = st.nextToken();
						Double flowerPrice = Double.valueOf(st.nextToken());
						int flowerQuantity = Integer.parseInt(st.nextToken());
						String flowerColour = st.nextToken();
						products.add(new Flower(flowerId, flowerName, flowerPrice, flowerQuantity, flowerColour));

						break;
					default: // if we don't know type, data is corrupt
						throw new InputMismatchException();

					}
				} catch (NumberFormatException e) { // string ->double
					System.out.println(
							"Something went wrong (string as a number) reading the file Stock in line: " + lineNum);
				} catch (InputMismatchException e) { // string ->int
					System.out.println("Wrong product type or wrong field in line: " + lineNum + " in Stock");
				}

			}
		} catch (FileNotFoundException e) {

			System.out.println("Wrong path of Stock File");

		} catch (IOException e) {

			System.out.println("Something went wrong reading the file in line:" + lineNum);

		} finally {
			if (bf != null) {
				try {
					bf.close();
				} catch (IOException e) {

					System.out.println("Something went wrong closing the buffer");
				}
			}
		}
		return products;

	}

}
