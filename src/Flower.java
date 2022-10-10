public class Flower extends Producto{
    private String colour;

    public Tree(String name, float price, int quantity,String colour) {
        super( name,  price,  quantity)
        this.colour = colour;

    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }
}