package application;
public class Tree extends Product{
    private int height;

    public Tree(String name, float price, int quantity,int height) {
        super( name,  price,  quantity, "Tree");
        this.height = height;

    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
	@Override
	public String toString() {
		return "Product id: " + this.getId() + ", name: " + this.getName() + 
				", type: " + this.getType() + ", height: " + height + 
				", price: " + this.getPrice();
	}
}