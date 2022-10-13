package application;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Ticket {
	private static int idIncrement = 0;
	private final int id; // need auto increment
	private Date date; // creation time
	private double price; // need to compute price first
	private HashMap<Product, Integer> products;		//Product, quantity

	public Ticket() {
		this.products = new HashMap<Product, Integer>();
		Ticket.idIncrement++;
		this.id = idIncrement;
	}

	
	public int getId() {
		return id;
	}


	public Date getDate() {
		return date;
	}


	public double getPrice() {
		return price;
	}


	public void addProduct(Product product, Integer quantity) {
		this.products.put(product, quantity);
	}

	public void removeProduct(Product product) {
		this.products.remove(product);
	}

	public HashMap<Product, Integer> getProducts(){
		return products;
	}
	
	public void closeTicket() {
		computePrice();
		date = new Date();
		// Write ticket in file
	}

	public void computePrice() {
		this.price = 0;
		
		//Go through all products
		for (Product p : products.keySet()) {
			//Get each price by multiplying the price for one product by the quantity of products bought
			this.price += p.getPrice() * products.get(p);	
		}
		System.out.println("The total price is " + price + " €.");
	}
}
