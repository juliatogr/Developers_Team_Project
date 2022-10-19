package application;

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
		this.date = new Date();
		this.price = 0;
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

	public void removeProduct(Product product, int quantity) {
		
		int newQuantity = this.products.get(product)-quantity;
		
		if (newQuantity == 0) {
			this.products.remove(product);
		} else {
			this.products.put(product, this.products.get(product));
		}	
	}

	public HashMap<Product, Integer> getProducts(){
		return products;
	}
	
	public void closeTicket() {
		
		computePrice();
		date = new Date();
		
		System.out.println(this);
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


	public int getProductQuantity(Integer id){
		Product p = findProductId(id);
		return products.get(p);
	}
}
