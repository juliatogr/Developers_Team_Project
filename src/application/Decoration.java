package application;

/*
 * Class Decoration to implement all functionalities of a decoration of the florist.
 * It inherits from Product since a decoration is a type of product. Therefore, it
 * has all properties of a Product + the defined below.
 */
public class Decoration extends Product{
	
    private MaterialType material;			// Material of the decoration (WOOD, PLASTIC)

    // Constructor
    
    public Decoration(String name, double price, int quantity, MaterialType material) {
        super( name,  price,  quantity, ProductType.DECORATION);
        this.material = material;
    }
    
    public Decoration(int id, String name, double price, int quantity, Decoration.MaterialType material) {
		super(id, name, price, quantity, ProductType.DECORATION);
		this.material = material;
	}

    // Material Getter & Setter
    
    public MaterialType getMaterial() {
        return material;
    }

    public void setMaterial(MaterialType material) {
        this.material = material;
    }

    // Enumeration for the material of the decoration
    
    public enum MaterialType{
        WOOD,
        PLASTIC
    }
    
    // toString
    
	@Override
	public String toString() {
		return "Product id: " + this.getId() + ", name: " + this.getName() + 
				", type: " + this.getType() + ", material: " + material + 
				", price: " + this.getPrice();		
	}
	@Override
	public String toStringTxt() {
		return "D" + ";" + this.getId() +";"+ this.getName() + ";" + this.getPrice() + ";" + this.getQuantity() +";"+this.material.toString();
	}
}