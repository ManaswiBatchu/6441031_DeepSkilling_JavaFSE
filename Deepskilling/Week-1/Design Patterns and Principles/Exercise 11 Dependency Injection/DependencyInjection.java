package javaaa;

public class DependencyInjection {

    public interface CustomerRepository {
        String findCustomerById(int id);
    }

    public static class CustomerRepositoryImpl implements CustomerRepository {
        @Override
        public String findCustomerById(int id) {
            // In a real application, this would query a database
            return "Customer Details: ID=" + id + ", Name=John Doe, Email=john@example.com";
        }
    }

    public static class CustomerService {
        private final CustomerRepository customerRepository;

        // Constructor injection
        public CustomerService(CustomerRepository customerRepository) {
            this.customerRepository = customerRepository;
        }

        public String getCustomerDetails(int customerId) {
            return customerRepository.findCustomerById(customerId);
        }
    }

    public static class DITest {
        public static void main(String[] args) {
            System.out.println("Customer Management with Dependency Injection\n");

            // Create the repository
            CustomerRepository repository = new CustomerRepositoryImpl();

            // Inject the repository into the service (constructor injection)
            CustomerService customerService = new CustomerService(repository);

            // Use the service to find a customer
            System.out.println("Looking up customer with ID 123...");
            String customerDetails = customerService.getCustomerDetails(123);
            System.out.println(customerDetails);

            // Another customer lookup
            System.out.println("\nLooking up customer with ID 456...");
            System.out.println(customerService.getCustomerDetails(456));

            // Demonstrate flexibility by creating a different repository implementation
            System.out.println("\nCreating service with test repository...");
            CustomerRepository testRepository = new CustomerRepository() {
                @Override
                public String findCustomerById(int id) {
                    return "[TEST] Customer ID=" + id;
                }
            };
            CustomerService testService = new CustomerService(testRepository);
            System.out.println(testService.getCustomerDetails(999));
        }
    }
}