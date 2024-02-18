public class Electronics extends Product {
    protected String brand;
    protected int warranty_period;

    public Electronics() {
        super();
    }
    public Electronics(String brand, int warranty_period){
        super();
        this.brand = brand;
        this.warranty_period = warranty_period;
    }
    public Electronics(String product_ID, String product_name, int available_items,
                       double price, String brand, int warranty_period){
        super(product_ID, product_name, available_items, price);
        this.brand = brand;
        this.warranty_period = warranty_period;
    }

    public String getBrand(){

        return this.brand;
    }
    public void setBrand(String brand){

        this.brand = brand;
    }

    public int getWarranty_period(){

        return this.warranty_period;
    }
    public void setWarranty_period(int warrantyPeriod){

        this.warranty_period = warrantyPeriod;
    }

    public String toString(){

        return "Product is Electronic \n" +
                "Product ID: " + getProduct_ID() +
                ",  Product Name: " + getProduct_name() +
                ",  Product Brand: " + getBrand() +
                ",  Product Warranty period: " + getWarranty_period() +
                ",  Product price: " + getPrice();
    }

}
