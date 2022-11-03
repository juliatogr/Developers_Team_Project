package application;

import java.util.Objects;

/*
 * Class Tree to implement all functionalities of a tree of the florist.
 * It inherits from Product since a tree is a type of product. Therefore, it
 * has all properties of a Product + the defined below.
 */
public class Tree extends Product{

	private double height;				// Height of the tree

    // Constructor
    
    public Tree(String name, double price, int quantity, double height) {
    	super( name,  price,  quantity, ProductType.TREE);
        this.height = height;
	}
    
    // Constructor for readTxt
    public Tree(int id, String name, double price, int quantity, double height) {
		super(id, name, price, quantity, ProductType.TREE);
		this.height = height;
	}

    // Height Getter & Setter
	public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
    
    // toString
    
	@Override
	public String toString() {
		return "Product id: " + this.getId() + ", name: " + this.getName() + 
				", type: " + this.getType() + ", height: " + height + 
				", price: " + this.getPrice();
	}
	
	//toString for writeStock
	@Override
	public String toStringTxt() {
		return "T" + ";" + this.getId() +";"+ this.getName() + ";" + this.getPrice() + ";" + this.getQuantity() +";"+this.height;
	}
	
	//hashCode methods
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(height);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tree other = (Tree) obj;
		return Double.doubleToLongBits(height) == Double.doubleToLongBits(other.height);
	}
}