package javaaa;

public class StrategyPattern {

    public interface PaymentStrategy {
        void pay(double amount);
    }

    public static class CreditCardPayment implements PaymentStrategy {
        private String cardNumber;
        private String name;
        private String cvv;
        
        public CreditCardPayment(String cardNumber, String name, String cvv) {
            this.cardNumber = cardNumber;
            this.name = name;
            this.cvv = cvv;
        }

        @Override
        public void pay(double amount) {
            System.out.println("Processing credit card payment of $" + amount);
            System.out.println("Card: " + cardNumber.substring(cardNumber.length()-4));
            System.out.println("Name: " + name);
        }
    }

    public static class PayPalPayment implements PaymentStrategy {
        private String email;
        
        public PayPalPayment(String email) {
            this.email = email;
        }

        @Override
        public void pay(double amount) {
            System.out.println("Processing PayPal payment of $" + amount);
            System.out.println("Email: " + email);
        }
    }

    public static class CryptoPayment implements PaymentStrategy {
        private String walletAddress;
        
        public CryptoPayment(String walletAddress) {
            this.walletAddress = walletAddress;
        }

        @Override
        public void pay(double amount) {
            System.out.println("Processing crypto payment of $" + amount);
            System.out.println("Wallet: " + walletAddress.substring(0, 6) + "...");
        }
    }

    // 3. Context Class
    public static class PaymentContext {
        private PaymentStrategy strategy;
        
        public void setPaymentStrategy(PaymentStrategy strategy) {
            this.strategy = strategy;
        }
        
        public void executePayment(double amount) {
            if (strategy != null) {
                strategy.pay(amount);
            } else {
                System.out.println("No payment method selected!");
            }
        }
    }
    
    public static class StrategyTest {
        public static void main(String[] args) {
            System.out.println("Payment System using Strategy Pattern\n");
            
            PaymentContext context = new PaymentContext();
            
            // Credit Card Payment
            System.out.println("--- Credit Card Payment ---");
            context.setPaymentStrategy(new CreditCardPayment("4111111111111111", "John Doe", "123"));
            context.executePayment(100.50);
            
            // PayPal Payment
            System.out.println("\n--- PayPal Payment ---");
            context.setPaymentStrategy(new PayPalPayment("john.doe@example.com"));
            context.executePayment(75.25);
            
            // Crypto Payment
            System.out.println("\n--- Crypto Payment ---");
            context.setPaymentStrategy(new CryptoPayment("1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa"));
            context.executePayment(250.00);
            
            // No strategy set
            System.out.println("\n--- No Payment Method ---");
            context.setPaymentStrategy(null);
            context.executePayment(50.00);
        }
    }
}