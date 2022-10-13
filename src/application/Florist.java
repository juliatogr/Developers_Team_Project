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
		
		int stockSize = stock.size();
		
		for (int i=0; i<stockSize; i++) {
			System.out.println(stock.get(i));
		}
	};
	
	public void printStockQuantities() {
		
		int stockSize = stock.size();
		
		for (int i=0; i<stockSize; i++) {
			Product p = stock.get(i) ;
			System.out.println(p + " - " + p.getQuantity() + " units");
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
		
		for (Ticket t : tickets) {
			
			totalValue += t.getPrice();
		}
		
		System.out.println("Total value of the florist: " + totalValue + " €");
	}


	@Override
	public String toString() {
		return "Florist " + name;
	};

	
	
	
	
	
	
	
	
	

	
}
