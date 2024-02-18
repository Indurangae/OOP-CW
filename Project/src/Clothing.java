public class Clothing extends Product{
    protected int size;
    protected String color;

    public Clothing() {
        super();
    }
    public Clothing (int size, String color){
        super();
        this.size = size;
        this.color = color;
    }
    public Clothing (String product_ID, String product_name, int available_items,
                     double price, int size, String color){
        super(product_ID, product_name, available_items, price);
        this.size = size;
        this.color  = color;
    }

    public int getSize(){

        return this.size;
    }
    public void setSize(int size) {

        this.size = size;
    }

    public String getColor(){

        return this.color;
    }
    public void setColor(String color) {

        this.color = color;
    }

    public String toString(){

        return "Product is Clothing \n" +
                "Product ID: " + getProduct_ID() +
                ",  Product Name: " + getProduct_name() +
                ",  Product Size: " + getSize() +
                ",  Product color: " + getColor() +
                ",  Product price: " + getPrice();
    }



}
