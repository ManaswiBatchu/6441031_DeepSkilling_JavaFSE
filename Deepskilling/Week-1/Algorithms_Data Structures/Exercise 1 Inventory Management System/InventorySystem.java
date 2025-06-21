package javaaa;
import java.util.HashMap;
import java.util.Map;

public class InventorySystem {

    public static class Product {
        private String productId;
        private String productName;
        private int quantity;
        private double price;

        public Product(String productId, String productName, int quantity, double price) {
            this.productId = productId;
            this.productName = productName;
            this.quantity = quantity;
            this.price = price;
        }

        // Getters & Setters
        public String getProductId() { return productId; }
        public String getProductName() { return productName; }
        public int getQuantity() { return quantity; }
        public double getPrice() { return price; }

        public void setQuantity(int quantity) { this.quantity = quantity; }
        public void setPrice(double price) { this.price = price; }

        @Override
        public String toString() {
            return String.format(
                "ID: %s | Name: %s | Qty: %d | Price: $%.2f",
                productId, productName, quantity, price
            );
        }
    }

    public static class InventoryManager {
        private Map<String, Product> inventory = new HashMap<>();

        // Add a product (O(1))
        public void addProduct(Product product) {
            inventory.put(product.getProductId(), product);
            System.out.println("✅ Added: " + product);
        }

        // Update product (O(1))
        public void updateProduct(String productId, int newQty, double newPrice) {
            if (inventory.containsKey(productId)) {
                Product product = inventory.get(productId);
                product.setQuantity(newQty);
                product.setPrice(newPrice);
                System.out.println("🔄 Updated: " + product);
            } else {
                System.out.println("❌ Product not found!");
            }
        }

        // Delete product (O(1))
        public void deleteProduct(String productId) {
            if (inventory.containsKey(productId)) {
                Product removed = inventory.remove(productId);
                System.out.println("❌ Deleted: " + removed);
            } else {
                System.out.println("❌ Product not found!");
            }
        }

        // Display all products (O(n))
        public void displayInventory() {
            System.out.println("\n📦 Current Inventory:");
            if (inventory.isEmpty()) {
                System.out.println("(Empty)");
            } else {
                inventory.values().forEach(System.out::println);
            }
        }
    }

    public static void main(String[] args) {
        InventoryManager manager = new InventoryManager();

        // Adding products
        manager.addProduct(new Product("P100", "Laptop", 50, 999.99));
        manager.addProduct(new Product("P200", "Smartphone", 100, 699.99));
        manager.addProduct(new Product("P300", "Headphones", 200, 99.99));

        // Display inventory
        manager.displayInventory();

        // Update a product
        manager.updateProduct("P100", 45, 899.99);

        // Delete a product
        manager.deleteProduct("P200");

        // Final inventory
        manager.displayInventory();
    }
}