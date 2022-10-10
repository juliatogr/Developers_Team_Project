package application;
public class Decoration extends Product{
	
    private MaterialType material;

    public Decoration(String name, float price, int quantity, MaterialType material) {
        super( name,  price,  quantity, "Decoration");
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
}