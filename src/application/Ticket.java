package application;

import java.util.Date;
import java.util.HashMap;

/*
 * Class Ticket to implement all functionalities of a ticket of the florist.
 */
public class Ticket {
	private static int idIncrement = 0;	   			// saves the last used id. It increments each time an id is assigned
	private final int id; 							// Identifier of the object. Unique and non modificable
	private Date date; 								// Closing date
	private double price; 							// need to compute price first
	private HashMap<Product, Integer> products;		//Product added, corresponding quantity

	// Constructors
	
	public Ticket() {
		this.products = new HashMap<Product, Integer>();
		this.date = new Date();
		this.price = 0;
		Ticket.idIncrement++;
		this.id = idIncrement;
	}

	// Getters
	
	public int getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	public double getPrice() {
		return price;
	}

	public HashMap<Product, Integer> getProducts(){
		return products;
	}
	
	public int getProductQuantity(Integer id){
		Product p = findProductId(id);
		return products.get(p);
	}
	
	// Product methods
	
	public void addProduct(Product product, Integer quantity) {
		/*
		 * Add a product to the products map
		 * 
		 * @param product - product to add
		 * @param quantity - quantity to add
		 */
		this.products.put(product, quantity);
	}

	public void removeProduct(Product product, int quantity) {
		/*
		 * Remove a product from the products map.
		 * 
		 * @param product - product to remove
		 * @param quantity - quantity to remove
		 */
		int newQuantity = this.products.get(product)-quantity;
		
		if (newQuantity == 0) {
			this.products.remove(product);
		} else {
			this.products.put(product, this.products.get(product));
		}	
	}
	
	public Product findProductId(Integer id){
		Product p = null;
		int prodsSize = products.size();
		int counter = 0;
		boolean found = false;
		//todo intentar fer que acabi en quant ho trobi
		for (Product _p : products.keySet()) {
			if (_p.getId() == id) {
				p = _p;
			}
		}
		return p;
	}
	
	// Generic methods
	public void closeTicket() {
		/*
		 * Method to compute last attributes of the ticket
		 */
		computePrice(); 			// Compute the total price of the ticket
		date = new Date();			// Assigns the closing date
		System.out.println(this);	// Print total result
	}

	public void computePrice() {
		/*
		 * Method to compute the total price of the ticket
		 */
		this.price = 0;
		
		//Go through all products
		for (Product p : products.keySet()) {
			//Get each price by multiplying the price for one product by the quantity of products bought
			this.price += p.getPrice() * products.get(p);	
		}
		System.out.println("The total price is " + price + " €.");
	}

	@Override
	public String toString() {
		
		String txt = "Ticket id: " + id + ", date:" + date;
		
		txt += "\n    Products:\n";
		
		for (Product p : products.keySet()) {
			txt += "\n      - " + p.toString();
		}
		
		txt += "\n    Price: " + price;
				
				
		return  txt;
	}

}
