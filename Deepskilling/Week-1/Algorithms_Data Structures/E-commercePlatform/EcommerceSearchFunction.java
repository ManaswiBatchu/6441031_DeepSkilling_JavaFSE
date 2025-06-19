package javaaa;
class Product {
    int productId;
    String productName;
    String category;

    public Product(int productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product ID: " + productId + ", Name: " + productName + ", Category: " + category;
    }
}

public class EcommerceSearchFunction {

    // Linear Search: searches for a product by productId in an unsorted array
    public static int linearSearch(Product[] products, int targetId) {
        for (int i = 0; i < products.length; i++) {
            if (products[i].productId == targetId) {
                return i; // Return index if found
            }
        }
        return -1; // Return -1 if not found
    }

    // Binary Search: searches for a product by productId in a sorted array
    public static int binarySearch(Product[] products, int targetId) {
        int left = 0;
        int right = products.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (products[mid].productId == targetId) {
                return mid; // Found
            } else if (products[mid].productId < targetId) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1; // Not found
    }

    public static void main(String[] args) {
        // Sample products
        Product[] products = {
            new Product(101, "Laptop", "Electronics"),
            new Product(102, "Smartphone", "Electronics"),
            new Product(103, "Book", "Books"),
            new Product(104, "Pen", "Stationery"),
            new Product(105, "Headphones", "Electronics")
        };

        // Linear Search Example
        int targetId = 103;
        int indexLinear = linearSearch(products, targetId);
        if (indexLinear != -1) {
            System.out.println("Linear Search: Found " + products[indexLinear]);
        } else {
            System.out.println("Linear Search: Product not found");
        }

        // For Binary Search, the array must be sorted by productId
        Product[] sortedProducts = {
            new Product(101, "Laptop", "Electronics"),
            new Product(102, "Smartphone", "Electronics"),
            new Product(103, "Book", "Books"),
            new Product(104, "Pen", "Stationery"),
            new Product(105, "Headphones", "Electronics")
        };

        // Binary Search Example
        int indexBinary = binarySearch(sortedProducts, targetId);
        if (indexBinary != -1) {
            System.out.println("Binary Search: Found " + sortedProducts[indexBinary]);
        } else {
            System.out.println("Binary Search: Product not found");
        }

        // Analysis comments
        System.out.println("\nAnalysis:");
        System.out.println("Linear Search Time Complexity: O(n)");
        System.out.println("Binary Search Time Complexity: O(log n)");
        System.out.println("Binary search is more efficient for large, sorted datasets.");
    }
}
