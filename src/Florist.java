import java.util.ArrayList;
import java.util.HashMap;

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
	
	public void addFlower(Flower f) {
		this.stock.remove(f);
	};
	
	public void addDecoration(Decoration d) {
		this.stock.remove(d);
	};
	
	
	public void printStock() {
	};
	
	public void printStockQuantities() {
	};
	
	public void printTotalValue() {
	};
	
	public void createTicket() {
	};
	
	public void printTickets() {
	};
	
	public void orintTotalGain() {
	};

	
	
	
	
	
	
	
	
	

	
}
