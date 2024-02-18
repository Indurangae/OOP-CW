import java.util.Scanner;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();
        ShoppingCart shoppingCart = new ShoppingCart();

        System.out.print("Are you a Manager (M) or a Customer (C)? ");
        char userType = scanner.next().toUpperCase().charAt(0);

        if (userType == 'M') {
            // Manager Menu
            while (true) {
                System.out.println("<<<<<<  Manager Menu  >>>>>>");
                System.out.println("1. Add a new product");
                System.out.println("2. Delete a product");
                System.out.println("3. Print the list of the products");
                System.out.println("4. Save in a File");
                System.out.println("5. Exit");

                System.out.print("Enter your choice (1-5): ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        // Add Product to Inventory
                        System.out.println("Enter product details:");
                        System.out.print("Is the product Electronic (E) or Clothing (C)? ");
                        char productType = scanner.next().toUpperCase().charAt(0);

                        if (productType == 'E') {
                            // Electronic product
                            System.out.print("Product ID: ");
                            String productId = scanner.next();
                            System.out.print("Product Name: ");
                            String productName = scanner.next();
                            System.out.print("Available Items: ");
                            int availableItems = scanner.nextInt();
                            System.out.print("Price: ");
                            double price = scanner.nextDouble();
                            System.out.print("Brand: ");
                            String brand = scanner.next();
                            System.out.print("Warranty Period: ");
                            int warrantyPeriod = scanner.nextInt();

                            Electronics newElectronicProduct = new Electronics(productId, productName, availableItems, price, brand, warrantyPeriod);
                            shoppingManager.add_product(newElectronicProduct);
                        } else if (productType == 'C') {
                            // Clothing product
                            System.out.print("Product ID: ");
                            String productId = scanner.next();
                            System.out.print("Product Name: ");
                            String productName = scanner.next();
                            System.out.print("Available Items: ");
                            int availableItems = scanner.nextInt();
                            System.out.print("Price: ");
                            double price = scanner.nextDouble();
                            System.out.print("Size: ");
                            int size = scanner.nextInt();
                            System.out.print("Color: ");
                            String color = scanner.next();

                            Clothing newClothingProduct = new Clothing(productId, productName, availableItems, price, size, color);
                            shoppingManager.add_product(newClothingProduct);
                        } else {
                            System.out.println("Invalid product type. Please enter 'E' for Electronic or 'C' for Clothing.");
                        }
                        break;
                    case 2:
                        System.out.print("Enter product ID to delete: ");
                        String productIDToDelete = scanner.next();
                        shoppingManager.delete_product(productIDToDelete);
                        break;
                    case 3:
                        shoppingManager.print_products();
                        break;
                    case 4:
                        shoppingManager.saveInventoryToFile();
                        break;
                    case 5:
                        System.out.println("Exiting the Manager Menu. Goodbye!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                }
            }
        } else if (userType == 'C') {
            // Customer Menu
            while (true) {
                System.out.println("<<<<<<  Customer Menu  >>>>>>");
                System.out.println("1. Add Product to Shopping Cart");
                System.out.println("2. Remove Product from Shopping Cart");
                System.out.println("3. View Shopping Cart");
                System.out.println("4. Checkout and Calculate Total");
                System.out.println("5. Open GUI");
                System.out.println("6. Exit");

                System.out.print("Enter your choice (1-6): ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("Enter product details to add to the shopping cart:");
                        System.out.print("Product ID: ");
                        String productIdToAdd = scanner.next();
                        // Search for the product in the inventory
                        Product productToAdd = shoppingCart.findProductById(productIdToAdd);
                        if (productToAdd != null) {
                            shoppingCart.add_product(productToAdd);
                            System.out.println("Product added to the shopping cart.");
                        } else {
                            System.out.println("Product not found in the inventory.");
                        }
                        break;

                    case 2:
                        System.out.println("Enter product details to remove from the shopping cart:");
                        System.out.print("Product ID: ");
                        String productIdToRemove = scanner.next();
                        // Search for the product in the shopping cart
                        Product productToRemove = shoppingCart.findProductById(productIdToRemove);
                        if (productToRemove != null) {
                            shoppingCart.remove_product(productToRemove);
                            System.out.println("Product removed from the shopping cart.");
                        } else {
                            System.out.println("Product not found in the shopping cart.");
                        }
                        break;

                    case 3:
                        System.out.println("===== Shopping Cart Contents =====");
                        for (Product product : shoppingCart.getProductList()) {
                            System.out.println(product.toString());
                        }
                        break;

                    case 4:
                        double totalCost = shoppingCart.total_cost();
                        System.out.println("Total Cost: $" + totalCost);
                        break;

                    case 5:
                        // Open GUI
                        SwingUtilities.invokeLater(() -> {

                            // Adding sample products to the shopping manager
                            shoppingManager.add_product(new Electronics("A1000", "TV", 1, 299.99, "Samsung", 12));
                            shoppingManager.add_product(new Electronics("A203", "Dishwasher", 1, 500.00, "Bosch", 36));
                            shoppingManager.add_product(new Clothing("B001", "Shirt", 1, 39.99, 0, "White"));
                            shoppingManager.add_product(new Clothing("B201", "Leggings", 1, 22.90, 0, "Black"));


                            ShoppingGUI shoppingGUI = new ShoppingGUI(shoppingManager);
                            shoppingGUI.setVisible(true);
                        });
                        break;

                    case 6:
                        System.out.println("Exiting the Customer Menu. Have a Nice Day!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                }
            }
        } else {
            System.out.println("Invalid user type. Please restart the program and enter 'M' for Manager or 'C' for Customer.");
        }
    }
}
