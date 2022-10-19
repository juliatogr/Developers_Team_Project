package application;

public class Tree extends Product{
    private double height;

    public Tree(String name, double price, int quantity, float height) {
    	super( name,  price,  quantity, ProductType.TREE);
        this.height = height;
	}

	public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
    
	@Override
	public String toString() {
		return "Product id: " + this.getId() + ", name: " + this.getName() + 
				", type: " + this.getType() + ", height: " + height + 
				", price: " + this.getPrice();
	}
}