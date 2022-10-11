package application;
public class Flower extends Product{
    private String colour;

    public Flower(String name, float price, int quantity,String colour) {
        super( name,  price,  quantity, "Flower");
        this.colour = colour;

    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }
    
	@Override
	public String toString() {
		return "Product id: " + this.getId() + ", name: " + this.getName() + 
				", price: " + this.getPrice() + ", type: " + this.getType() + 
				", colour: " + colour;
	}
}