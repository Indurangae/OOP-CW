import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.border.EmptyBorder;

public class ShoppingGUI extends JFrame {

    private WestminsterShoppingManager shoppingManager;

    public ShoppingGUI(WestminsterShoppingManager shoppingManager) {
        this.shoppingManager = shoppingManager;

        setTitle("Westminster Shopping Centre");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 400);
        setLocationRelativeTo(null);


        JLabel label = new JLabel("Select product Category");

        JButton viewCartButton = new JButton("Shopping Cart");

        String[] columnNames = {"Product ID", "Name", "Category", "Price", "Info"};
        Object[][] data = {};

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(model);
        table.setAutoCreateRowSorter(true); // Enable sorting

        String[] productTypes = {"All", "Electronics", "Clothes"};
        JComboBox<String> productTypeComboBox = new JComboBox<>(productTypes);
        productTypeComboBox.setPreferredSize(new Dimension(100, productTypeComboBox.getPreferredSize().height));

        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));

        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(10, 50, 10, 50));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JPanel dropDownPanel = new JPanel();
        dropDownPanel.setBorder(new EmptyBorder(20, 10, 10, 10));
        dropDownPanel.add(label);
        dropDownPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Add some space between label and combo box
        dropDownPanel.add(productTypeComboBox);
        dropDownPanel.add(viewCartButton);
        panel.add(dropDownPanel);
        panel.add(new JScrollPane(table));
        table.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(detailsPanel);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);

        // Set event listener for the view cart button
        viewCartButton.addActionListener(e -> {
            // Create and display the CartGUI with the current shopping manager
            CartGUI cartGUI = new CartGUI(shoppingManager);
            cartGUI.setVisible(true);
        });



        // Set event listener for table row selection
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    updateDetailsPanel(detailsPanel, model, selectedRow);
                }
            }
        });

        // Initially update table data
        updateTableData(model, "All");
    }

    private void updateTableData(DefaultTableModel model, String selectedProductType) {
        // Clear existing data
        model.setRowCount(0);

        // Populate table with product information based on the selected product type
        ArrayList<Product> products = shoppingManager.getProducts();
        for (Product product : products) {
            if (selectedProductType.equals("All") ||
                    (selectedProductType.equals("Electronics") && product instanceof Electronics) ||
                    (selectedProductType.equals("Clothes") && product instanceof Clothing)) {
                String[] rowData = {
                        product.getProduct_ID(),
                        product.getProduct_name(),
                        (product instanceof Electronics) ? "Electronics" : "Clothing",
                        String.valueOf(product.getPrice()),
                        getProductInfo(product)
                };
                model.addRow(rowData);
            }
        }
    }

    private String getProductInfo(Product product) {
        if (product instanceof Electronics) {
            Electronics electronicsProduct = (Electronics) product;
            return String.format("%s, %d weeks warranty", electronicsProduct.getBrand(), electronicsProduct.getWarranty_period());
        } else if (product instanceof Clothing) {
            Clothing clothingProduct = (Clothing) product;
            return String.format("%s, %s", clothingProduct.getSize(), clothingProduct.getColor());
        } else {
            return "N/A";
        }
    }

    private void updateDetailsPanel(JPanel detailsPanel, DefaultTableModel model, int selectedRow) {
        // Clear existing components
        detailsPanel.removeAll();

        // Get details of the selected product
        String productID = (String) model.getValueAt(selectedRow, 0);
        String productName = (String) model.getValueAt(selectedRow, 1);
        String category = (String) model.getValueAt(selectedRow, 2);
        String price = (String) model.getValueAt(selectedRow, 3);
        String info = (String) model.getValueAt(selectedRow, 4);

        // Display details in a label
        JLabel detailsLabel = new JLabel("Selected Product Details:");
        JLabel productDetailsLabel = new JLabel("<html>" +
                "Product ID: " + productID + "<br>" +
                "Name: " + productName + "<br>" +
                "Category: " + category + "<br>" +
                "Price: " + price + "<br>" +
                "Info: " + info +
                "</html>");

        JButton addToCartButton = new JButton("Add to Shopping Cart");
        addToCartButton.addActionListener(e -> {

            shoppingManager.add_product(createSampleProduct(productID, productName, category, Double.parseDouble(price), info));
            JOptionPane.showMessageDialog(this, "Product added to the cart!");
        });

        detailsPanel.add(detailsLabel);
        detailsPanel.add(productDetailsLabel);
        detailsPanel.add(addToCartButton);

        detailsPanel.revalidate();
        detailsPanel.repaint();
    }

    // Method to create a sample product for the cart
    private Product createSampleProduct(String productID, String productName, String category, double price, String info) {
        if ("Electronics".equals(category)) {
            return new Electronics(productID, productName, 1, price, "Sample Brand", 6);
        } else if ("Clothing".equals(category)) {
            return new Clothing(productID, productName, 1, price, 0, "Sample Color");
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();

            // Adding sample products to the shopping manager
            shoppingManager.add_product(new Electronics("A1000", "TV", 1, 299.99, "Samsung", 12));
            shoppingManager.add_product(new Electronics("A203", "Dishwasher", 1, 500.00, "Bosch", 36));
            shoppingManager.add_product(new Clothing("B001", "Shirt", 1, 39.99, 0, "White"));
            shoppingManager.add_product(new Clothing("B201", "Leggings", 1, 22.90, 0, "Black"));



            ShoppingGUI shoppingGUI = new ShoppingGUI(shoppingManager);
            shoppingGUI.setVisible(true);
        });
    }
}


