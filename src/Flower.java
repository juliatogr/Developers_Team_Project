public class Flower extends Product{
    private String colour;

    public Flower(String name, float price, int quantity,String colour) {
        super( name,  price,  quantity);
        this.colour = colour;

    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }
}