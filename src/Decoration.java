public class Decoration extends Producto{
    private MaterialType material;

    public Tree(String name, float price, int quantity,MaterialType material) {
        super( name,  price,  quantity)
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