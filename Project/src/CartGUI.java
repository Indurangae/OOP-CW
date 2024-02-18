import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.border.EmptyBorder;

public class CartGUI extends JFrame {

    private WestminsterShoppingManager shoppingManager;
    private DefaultTableModel tableModel;
    private JLabel totalLabel;

    public CartGUI(WestminsterShoppingManager shoppingManager) {
        this.shoppingManager = shoppingManager;

        setTitle("Shopping Cart");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Create components
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Product");
        tableModel.addColumn("Quantity");
        tableModel.addColumn("Price");

        JTable cartTable = new JTable(tableModel);
        totalLabel = new JLabel("Total: $0.00");

        // Add products from the shopping manager to the table
        ArrayList<Product> cartProducts = shoppingManager.getProducts();
        for (Product product : cartProducts) {
            Object[] rowData = {getCartItemText(product), 1, product.getPrice()};
            tableModel.addRow(rowData);
        }

        setLayout(new BorderLayout());
        add(new JScrollPane(cartTable), BorderLayout.CENTER);
        add(totalLabel, BorderLayout.SOUTH);


        tableModel.addTableModelListener(e -> updateTotal());

        // Initialize total
        updateTotal();
    }

    private String getCartItemText(Product product) {
        if (product instanceof Electronics) {
            Electronics electronicsProduct = (Electronics) product;
            return String.format("Electronics - %s, %s", electronicsProduct.getProduct_name(), electronicsProduct.getBrand());
        } else if (product instanceof Clothing) {
            Clothing clothingProduct = (Clothing) product;
            return String.format("Clothing - %s, %s", clothingProduct.getProduct_name(), clothingProduct.getColor());
        } else {
            return "N/A";
        }
    }


    private void updateTotal() {
        // Calculate total price
        double totalPrice = 0;
        for (int row = 0; row < tableModel.getRowCount(); row++) {
            int quantity = (int) tableModel.getValueAt(row, 1);
            double price = (double) tableModel.getValueAt(row, 2);
            totalPrice += quantity * price;
        }

        // Display total price
        totalLabel.setText("Total: $" + totalPrice);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();

            CartGUI cartGUI = new CartGUI(shoppingManager);
            cartGUI.setVisible(true);
        });
    }
}




