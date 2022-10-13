package application;
public class Product {
    private static int idIncrement = 0;
    
    private final int id;
    private String name;
    private float price;
    private int quantity;
    private String type;

    public Product(String name, float price, int quantity, String type) {

        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.type = type;
        this.idIncrement++;
        this.id = this.idIncrement;
    }

    public int getId() {
        return id;
    }

    public String getType() {
    	return this.type;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

	@Override
	public String toString() { // Doesn't print quantity because we need to differentiate prints on Florist methods.
		return "Product id: " + id + ", name: " + name + ", type: " + type + ", price: " + price;
	}
    
    
}
