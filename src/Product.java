public class Product {
    private int id = 1;

    static private int nextId = 1;
    private String name;
    private float price;
    private int quantity;

    public Product(String name, float price, int quantity) {

        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.nextId++;
        this.id = this.nextId;
    }

    public int getId() {
        return id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
