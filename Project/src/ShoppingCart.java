import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    boolean remove = false;
    double total = 0;

    List<Product> productList = new ArrayList<>();

    public void add_product(Product product1){

        productList.add(product1);
    }
    public void remove_product(Product product2){
        for (Product i : productList){
            if (i==product2){
                productList.remove(i);
                remove=true;
                break;
            }
        }
        if (remove){
            System.out.println("Product is removed from the cart");
        }
        else {
            System.out.println("Product is not available in the cart");
        }
    }

    public double total_cost(){
        for (Product i : productList){
            this.total = i.getPrice() + this.total;
        }
        return this.total;
    }

    public Product findProductById(String productId) {
        for (Product product : productList) {
            if (product.getProduct_ID().equals(productId)) {
                return product;
            }
        }
        return null;
    }

    public List<Product> getProductList() {
        return productList;
    }


}





