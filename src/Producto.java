public class Producto {
    private int id = 1;

    static private int nextId = 1;
    private String name;
    private float price;
    private int quantity;

    public Producto(String name, float price, int quantity) {

        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.nextId++;
        this.id = this.nextId;
    }
}
