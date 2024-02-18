import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class WestminsterShoppingManager implements ShoppingManager{
    private ArrayList<Product> products = new ArrayList<>();
    @Override          //add product method
    public void add_product(Product newProduct){
        if (products.size() <= 50){
            products.add(newProduct);
            System.out.println("Product added successfully");
        }
        else {
            System.out.println("Maximum is reached out");
        }
    }

    @Override         //delete product method
    public void delete_product(String product_ID) {
        for (Product i : products) {
            if (i.product_ID.equals(product_ID)) {
                products.remove(i);
                System.out.println(i.toString());
                System.out.println("Product deleted successfully");
                return;
            }
        }
        System.out.println("Product with ID " + product_ID + " not found");
    }
    @Override           //print product method
    public void print_products() {
        Collections.sort(products, Comparator.comparing(Product::getProduct_name));
        for (Product i : products){
            System.out.println(i.toString() + "\n");
        }
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

                       // save to files
    public void saveInventoryToFile() {
        try {
            FileWriter fileWriter = new FileWriter("ProductList.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);

            for (Product product : products) {
                if (product instanceof Electronics) {
                    printWriter.println("Electronic|" + "ID:" + product.getProduct_ID() + "|" +"Name:" + product.getProduct_name() + "|" +
                            "Available Items:" + product.getAvailable_items() + "|" +"Price:" + product.getPrice() + "|" +
                            "Brand:" +((Electronics) product).getBrand() + "|" +"Warranty period:" + ((Electronics) product).getWarranty_period());
                } else if (product instanceof Clothing) {
                    printWriter.println("Clothing|" +"ID:" + product.getProduct_ID() + "|" +"Name:" + product.getProduct_name() + "|" +
                            "Available Items:" + product.getAvailable_items() + "|" +"Price:" + product.getPrice() + "|" +
                            "Size:" +((Clothing) product).getSize() + "|" + "Color:" +((Clothing) product).getColor());
                }
            }

            printWriter.close();
            System.out.println("Products saved to file: ProductList.txt");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error saving inventory to file.");
        }
    }







}
