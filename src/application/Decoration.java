package application;

public class Decoration extends Product{
	
    private MaterialType material;

    public Decoration(String name, double price, int quantity, MaterialType material) {
        super( name,  price,  quantity, ProductType.DECORATION);
        this.material = material;

    }

    public MaterialType getMaterial() {
        return material;
    }

    public void setMaterial(MaterialType material) {
        this.material = material;
    }

    public enum MaterialType{
        WOOD,
        PLASTIC
    }
    
	@Override
	public String toString() {
		return "Product id: " + this.getId() + ", name: " + this.getName() + 
				", type: " + this.getType() + ", material: " + material + 
				", price: " + this.getPrice();		
	}
}