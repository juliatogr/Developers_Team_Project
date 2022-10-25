package application;

/*
 * Class Flower to implement all functionalities of a flower of the florist.
 * It inherits from Product since a flower is a type of product. Therefore, it
 * has all properties of a Product + the defined below.
 */
public class Flower extends Product {

	private String colour; // Colour of the flower

	// Constructor

	public Flower(String name, double price, int quantity, String colour) {
		super(name, price, quantity, ProductType.FLOWER);
		this.colour = colour;
	}

	// Constructor for readTxt
	public Flower(int id, String name, double price, int quantity, String colour) {
		super(id, name, price, quantity, ProductType.FLOWER);
		this.colour = colour;

	}

	// Colour Getter & Setter

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	// toString

	@Override
	public String toString() {
		return "Product id: " + this.getId() + ", name: " + this.getName() + ", type: " + this.getType() + ", colour: "
				+ colour + ", price: " + this.getPrice();
	}

	// toString for writeStock
	@Override
	public String toStringTxt() {
		return "F" + ";" + this.getId() + ";" + this.getName() + ";" + this.getPrice() + ";" + this.getQuantity() + ";"
				+ this.colour;
	}
}