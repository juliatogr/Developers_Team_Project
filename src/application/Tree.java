package application;

public class Tree extends Product{
    private float height;

    public Tree(String name, float price, int quantity, float height) {
    	super( name,  price,  quantity, ProductType.TREE);
        this.height = height;
	}

	public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }
    
	@Override
	public String toString() {
		return "Product id: " + this.getId() + ", name: " + this.getName() + 
				", type: " + this.getType() + ", height: " + height + 
				", price: " + this.getPrice();
	}
}