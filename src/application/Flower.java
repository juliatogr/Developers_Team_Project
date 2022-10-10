package application;
public class Flower extends Product{
    private String colour;

    public Flower(String name, float price, int quantity,String colour) {
        super( name,  price,  quantity, "Flower");
        this.colour = colour;

    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }
}