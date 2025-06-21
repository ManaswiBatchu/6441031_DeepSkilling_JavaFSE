package javaaa;
public class SortingCustomerOrders {
    public static void main(String[] args) {
    	
        Order[] orders = {
            new Order(101, "John Smith", 125.50),
            new Order(102, "Alice Johnson", 89.99),
            new Order(103, "Bob Williams", 220.00),
            new Order(104, "Emma Brown", 45.75),
            new Order(105, "Michael Davis", 350.25)
        };

        System.out.println("Original Orders:");
        printOrders(orders);

        // Sort using Bubble Sort
        Order[] bubbleSorted = bubbleSortOrders(orders.clone());
        System.out.println("\nOrders sorted by Bubble Sort (ascending by price):");
        printOrders(bubbleSorted);

        // Sort using Quick Sort
        Order[] quickSorted = quickSortOrders(orders.clone());
        System.out.println("\nOrders sorted by Quick Sort (ascending by price):");
        printOrders(quickSorted);
    }

    // Order class
    static class Order {
        int orderId;
        String customerName;
        double totalPrice;

        public Order(int orderId, String customerName, double totalPrice) {
            this.orderId = orderId;
            this.customerName = customerName;
            this.totalPrice = totalPrice;
        }

        @Override
        public String toString() {
            return String.format("Order #%d: %-15s $%.2f", orderId, customerName, totalPrice);
        }
    }

    // Helper method to print orders
    public static void printOrders(Order[] orders) {
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    public static Order[] bubbleSortOrders(Order[] orders) {
        int n = orders.length;
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if (orders[j].totalPrice > orders[j+1].totalPrice) {
                    // Swap orders[j] and orders[j+1]
                    Order temp = orders[j];
                    orders[j] = orders[j+1];
                    orders[j+1] = temp;
                }
            }
        }
        return orders;
    }

    public static Order[] quickSortOrders(Order[] orders) {
        quickSort(orders, 0, orders.length - 1);
        return orders;
    }

    private static void quickSort(Order[] orders, int low, int high) {
        if (low < high) {
            int pi = partition(orders, low, high);
            quickSort(orders, low, pi - 1);
            quickSort(orders, pi + 1, high);
        }
    }

    private static int partition(Order[] orders, int low, int high) {
        double pivot = orders[high].totalPrice;
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            if (orders[j].totalPrice < pivot) {
                i++;
                // Swap orders[i] and orders[j]
                Order temp = orders[i];
                orders[i] = orders[j];
                orders[j] = temp;
            }
        }
        
        // Swap orders[i+1] and orders[high] (pivot)
        Order temp = orders[i+1];
        orders[i+1] = orders[high];
        orders[high] = temp;
        
        return i + 1;
    }
}