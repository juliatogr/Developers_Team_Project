public class Tree extends Product{
    private int height;

    public Tree(String name, float price, int quantity,int height) {
        super( name,  price,  quantity);
        this.height = height;

    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}