public abstract class Product {
    protected String product_ID;
    protected String product_name;
    protected int available_items;
    protected double price;

    public Product() {

    }
    public Product(String product_ID, String product_name, int available_items, double price){
        this.product_ID = product_ID;
        this.product_name = product_name;
        this.available_items = available_items;
        this.price = price;
    }

    public String getProduct_ID(){

        return this.product_ID;
    }
    public void setProduct_ID(String product_ID){

        this.product_ID = product_ID;
    }

    public String getProduct_name(){

        return this.product_name;
    }
    public void setProduct_name(String product_name){

        this.product_name = product_name;
    }

    public int getAvailable_items(){

        return this.available_items;
    }
    public void setAvailable_items(int available_items){

        this.available_items = available_items;
    }

    public double getPrice() {

        return  this.price;
    }
    public void setPrice(double price) {

        this.price = price;
    }


}
