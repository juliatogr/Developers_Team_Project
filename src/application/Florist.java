package application;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;

public class Florist {
	private String name;
	private ArrayList<Product> stock; 
	//HashMap<Integer, Integer> stock; //product_id, quantity
	private ArrayList<Ticket> tickets;
	
	public Florist(String name) {
		this.name = name;
		this.stock = new ArrayList<Product>();
		this.tickets = new ArrayList<Ticket>();
		
	}
	
	
	public String getName() {
		return name;
	}

	public void addTree(Tree t) {
		this.stock.add(t);
	};
	
	public void addFlower(Flower f) {
		this.stock.add(f);
	};
	
	public void addDecoration(Decoration d) {
		this.stock.add(d);
	};
	
	public void removeTree(Tree t) {
		this.stock.remove(t);
	};
	
	public void removeFlower(Flower f) {
		this.stock.remove(f);
	};
	
	public void removeDecoration(Decoration d) {
		this.stock.remove(d);
	};
	
	
	public void printStock() {
		
		//Create a copy of the original stock array to remove the elements 
		//printed without affecting the original one.
		ArrayList<Product> stockCopy = (ArrayList<Product>) stock.clone(); 
		
		//Use an iterator to avoid problems removing objects
		ListIterator<Product> stockIt = stockCopy.listIterator();
		
		while (stockIt.hasNext()) {
			Product p = stockIt.next();
						
			String name = p.getName();
			
			while (stockIt.hasNext()) {
				
				String name2 = stockIt.next().getName();
				
				//look for duplicated products and delete them
				if (name.equalsIgnoreCase(name2)) {
					stockIt.remove();
				}
			}
			System.out.println("    - " + name + " - " + p.getPrice() + " €");
			stockIt.remove();
			
		}
	};
	
	public void printStockQuantities() {
		//Create a copy of the original stock array to remove the elements 
		//printed without affecting the original one.
		ArrayList<Product> stockCopy = (ArrayList<Product>) stock.clone(); 
		
		//Use an iterator to avoid problems removing objects
		ListIterator<Product> stockIt = stockCopy.listIterator();
		
		while (stockIt.hasNext()) {
			
			Product p = stockIt.next();
			
			String name = p.getName();
			int quantity = 1; // Count the number of products with the same name
			
			while (stockIt.hasNext()) {
				
				String name2 = stockIt.next().getName();
				
				//look for duplicated products and delete them
				if (name.equalsIgnoreCase(name2)) {
					quantity++;	//Sum a product with same name
					stockIt.remove();
				}
				
				
			}
			System.out.println("    - " + name + " - " + p.getPrice() + " €" + ": " + quantity);
			stockIt.remove();
			
		}
	};
	
	public void createTicket() {
		this.tickets.add(new Ticket());
		
	};
	
	public void printTickets() {
		
		for (Ticket t : tickets) {
			System.out.println(t);
		}
	};
	
	public void printTotalGain() {
		int totalValue = 0;
		
		for (Product p : stock) {
			totalValue += p.getQuantity();
		}
		
		System.out.println("Total value of the florist: " + totalValue + " €");
	}


	@Override
	public String toString() {
		return "Florist " + name;
	};

	
	
	
	
	
	
	
	
	

	
}
