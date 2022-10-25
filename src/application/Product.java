package application;

/*
 * Class Product to implement all functionalities of a product of the florist.
 */
public class Product {
	private static int idIncrement = 0; // saves the last used id. It increments each time an id is assigned
	private final int id; // Identifier of the object. Unique and non modificable
	private String name; // Name of the product
	private double price; // Price of the product
	private int quantity; // Current quantity of products in stock
	private ProductType type; // Type of product (TREE, FLOWER, DECORATION)

	// Constructors
	public Product(String name, double price, int quantity, ProductType type) {

		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.type = type;
		this.id = Product.idIncrement;
		Product.idIncrement++;
	}

	// Constructor for readTxt
	public Product(int id, String name, double price, int quantity, ProductType type) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.type = type;
	}

	// Getters

	public int getId() {
		return id;
	}

	public ProductType getType() {
		return this.type;
	}

	public String getName() {
		return name;
	}

	public int getQuantity() {
		return quantity;
	}

	public double getPrice() {
		return price;
	}

	// Setters

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public void setIdIncrement(int id) {
		idIncrement = id;
	}

	// Enumeration for the product type

	public enum ProductType {
		TREE, FLOWER, DECORATION
	}

	// toString

	@Override
	public String toString() { // Doesn't print quantity because we need to differentiate prints on Florist
								// methods.
		return "Product id: " + id + ", name: " + name + ", type: " + type + ", price: " + price;
	}
	//toString for writeStock
	public String toStringTxt() {
		return "";
	}

}
