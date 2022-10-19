package application;

public class Flower extends Product{
    private String colour;

    public Flower(String name, double price, int quantity,String colour) {
        super( name,  price,  quantity, ProductType.FLOWER);
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
				", type: " + this.getType() + ", colour: " + colour + 
				", price: " + this.getPrice();
				
	}
}