package application;

import java.time.LocalDateTime;
import java.util.TreeMap;
import java.util.Iterator;

/*
 * Class Ticket to implement all functionalities of a ticket of the florist.
 */
public class Ticket {
	private static int idIncrement = 0; // saves the last used id. It increments each time an id is assigned
	private final int id; // Identifier of the object. Unique and non modificable
	private LocalDateTime date; // Closing date
	private double price; // need to compute price first
	private TreeMap<Product, Integer> products; // Product added, corresponding quantity

	// Constructors

	public Ticket() {
		this.products = new TreeMap<Product, Integer>();
		this.date = LocalDateTime.now();
		this.price = 0;
		this.id = idIncrement;
		Ticket.idIncrement++;
	}

	// Constructor for readTxt
	public Ticket(int id, LocalDateTime date, double price, TreeMap<Product, Integer> products) {

		this.id = id;
		this.date = date;
		this.price = price;
		this.products = products;
	}

	// Getters

	public int getId() {
		return id;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public double getPrice() {
		return price;
	}

	public TreeMap<Product, Integer> getProducts() {
		return products;
	}

	public int getProductQuantity(Integer id) {
		/*
		 * Returns the chosen quantity of a product in the products TreeMap
		 * 
		 * @param id - id of the product to find
		 * 
		 */
		Product p = findProductId(id);
		return products.get(p);
	}

	public static int getIdIncrement() {
		return idIncrement;
	}

	// Setter
	public static void setIdIncrement(int id) {
		idIncrement = id;
	}

	// Product methods

	public void addProduct(Product product, Integer quantity) {
		/*
		 * Add a product to the products map
		 * 
		 * @param product - product to add
		 * 
		 * @param quantity - quantity to add
		 */
		this.products.put(product, quantity);
	}

	public void removeProduct(Product product, int quantity) {
		/*
		 * Remove a product from the products map.
		 * 
		 * @param product - product to remove
		 * 
		 * @param quantity - quantity to remove
		 */
		int newQuantity = this.products.get(product) - quantity;

		if (newQuantity == 0) {
			this.products.remove(product);
		} else {
			this.products.put(product, this.products.get(product));
		}
	}

	public Product findProductId(Integer id) {
		/*
		 * Find a product inside the TreeMap by an id
		 * 
		 * @param id - id of the product to find
		 * 
		 */
		Product p = null;
		boolean found = false;
		
		Iterator<Product> prods = products.keySet().iterator();
		
		while (!found && prods.hasNext()) {
			
			Product _p = prods.next();
			if (_p.getId() == this.id) {
				p = _p;
				found = true;
			}
		}
		return p;
	}

	// Generic methods
	public void closeTicket() {
		/*
		 * Method to compute last attributes of the ticket
		 */

		for (Product p : products.keySet()) {
			p.setQuantity(p.getQuantity() - products.get(p));
		}

		computePrice(); // Compute the total price of the ticket
		this.date = LocalDateTime.now(); // Assigns the closing date
		System.out.println(this); // Print total result

	}

	public void computePrice() {
		/*
		 * Method to compute the total price of the ticket
		 */
		this.price = 0;

		// Go through all products
		for (Product p : products.keySet()) {
			// Get each price by multiplying the price for one product by the quantity of
			// products bought
			this.price += p.getPrice() * products.get(p);
		}
		System.out.println("The total price is " + price + " ???.");
	}

	@Override
	public String toString() {

		String txt = "Ticket id: " + id + ", date:" + date;

		txt += "\n    Products:\n";

		for (Product p : products.keySet()) {
			txt += "\n      - " + p.toString() + " - " + products.get(p) + " units";
		}

		txt += "\n    Price: " + price;

		return txt;
	}

	public String toStringTxt() {
		String field = this.id + ";" + this.date.toString() + ";" + this.price + "_";

		Iterator<Product> it = this.products.keySet().iterator();

		while (it.hasNext()) {
			Product key = it.next();
			field += key.getId() + ";";
			int quantity = this.products.get(key);
			field += quantity;
			if (it.hasNext()) {
				field += "_";
			}

		}
		return field;
	}

}
