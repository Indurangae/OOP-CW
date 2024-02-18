/*


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WestminsterShoppingManagerTest {

    private WestminsterShoppingManager manager;

    @BeforeEach
    public void setUp() {
        manager = new WestminsterShoppingManager();
    }

    @Test
    public void testAddProductToSystem() {
        // Test adding a product to the system
        Product testProduct = new Electronics("E001", "Test Electronics", 10, 99.99, "TestBrand", 12);
        manager.add_product(testProduct);
        assertNotNull(manager.getProductByID("E001"));
    }

    @Test
    public void testDeleteProductFromSystem() {
        // Test deleting a product from the system
        Product testProduct = new Clothing("C001", "Test Clothing", 5, 29.99, 30, "Red");
        manager.add_product(testProduct);
        int initialSize = manager.getAllProducts().size();
        manager.delete_product("C001");
        assertEquals(initialSize - 1, manager.getAllProducts().size());
    }

    @Test
    public void testPrintProducts() {
        // Test printing the list of products
        Product product1 = new Electronics("E002", "Electronics 1", 8, 149.99, "AnotherBrand", 24);
        Product product2 = new Clothing("C002", "Clothing 1", 3, 19.99, 28, "Blue");

        manager.add_product(product1);
        manager.add_product(product2);

        String printedProducts = manager.print_products();

        assertTrue(printedProducts.contains("E002"));
        assertTrue(printedProducts.contains("Clothing 1"));
    }

}

 */