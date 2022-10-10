import java.util.ArrayList;
import java.util.Date;

public class Ticket {
	private static int id_increment = 0;
	private int id; // need auto increment
	private Date date; // creation time
	private double price; // need to compute price first
	private ArrayList<Product> products;

	public Ticket() {
		this.products = new ArrayList<Product>();
	}

	public void addProduct(Product product) {
		this.products.add(product);
	}

	public void removeProduct(Product product) {
		this.products.remove(product);

	}

	public void closeTicket() {

	}

	public void computePrice() {

	}
}
