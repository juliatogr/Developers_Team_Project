package application;
public class Product {
    private static int idIncrement = 0;
    
    private final int id;
    private String name;
    private double price;
    private int quantity;
    private ProductType type;

    public Product(String name, double price, int quantity, ProductType type) {

        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.type = type;
        Product.idIncrement++;
        this.id = Product.idIncrement;
    }

    public int getId() {
        return id;
    }

    public ProductType getType() {
    	return this.type;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
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
	
    public enum ProductType{
        TREE,
        FLOWER,
        DECORATION
    }
    
    
}
