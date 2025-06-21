package javaaa;

public class AdapterPattern {

    public interface PaymentProcessor {
        void processPayment(double amount);
        boolean verifyPayment(String transactionId);
    }

    public static class PayPalGateway {
        public void sendPayment(double amount) {
            System.out.println("Processing PayPal payment of $" + amount);
        }
        
        public boolean checkTransaction(String paypalId) {
            System.out.println("Verifying PayPal transaction: " + paypalId);
            return true; // Simplified for example
        }
    }
    
    public static class StripeGateway {
        public void makeStripePayment(double dollars) {
            System.out.println("Processing Stripe payment of $" + dollars);
        }
        
        public boolean verifyStripePayment(String stripeTransactionId) {
            System.out.println("Verifying Stripe transaction: " + stripeTransactionId);
            return true; // Simplified for example
        }
    }
    
    public static class CryptoPaymentService {
        public void sendCrypto(double amount, String currency) {
            System.out.println("Processing crypto payment of " + amount + " " + currency);
        }
        
        public boolean validateCryptoTransaction(String txHash) {
            System.out.println("Validating crypto transaction: " + txHash);
            return true; // Simplified for example
        }
    }

    // PayPal Adapter
    public static class PayPalAdapter implements PaymentProcessor {
        private PayPalGateway payPal;
        
        public PayPalAdapter(PayPalGateway payPal) {
            this.payPal = payPal;
        }
        
        @Override
        public void processPayment(double amount) {
            payPal.sendPayment(amount);
        }
        
        @Override
        public boolean verifyPayment(String transactionId) {
            return payPal.checkTransaction(transactionId);
        }
    }
    
    // Stripe Adapter
    public static class StripeAdapter implements PaymentProcessor {
        private StripeGateway stripe;
        
        public StripeAdapter(StripeGateway stripe) {
            this.stripe = stripe;
        }
        
        @Override
        public void processPayment(double amount) {
            stripe.makeStripePayment(amount);
        }
        
        @Override
        public boolean verifyPayment(String transactionId) {
            return stripe.verifyStripePayment(transactionId);
        }
    }
    
    // Crypto Adapter
    public static class CryptoAdapter implements PaymentProcessor {
        private CryptoPaymentService cryptoService;
        private String cryptoCurrency;
        
        public CryptoAdapter(CryptoPaymentService cryptoService, String cryptoCurrency) {
            this.cryptoService = cryptoService;
            this.cryptoCurrency = cryptoCurrency;
        }
        
        @Override
        public void processPayment(double amount) {
            cryptoService.sendCrypto(amount, cryptoCurrency);
        }
        
        @Override
        public boolean verifyPayment(String transactionId) {
            return cryptoService.validateCryptoTransaction(transactionId);
        }
    }

    public static class AdapterTest {
        public static void main(String[] args) {
            System.out.println("Payment Processing System using Adapter Pattern\n");
            
            // Create instances of different payment gateways
            PayPalGateway payPal = new PayPalGateway();
            StripeGateway stripe = new StripeGateway();
            CryptoPaymentService crypto = new CryptoPaymentService();
            
            // Create adapters for each gateway
            PaymentProcessor payPalProcessor = new PayPalAdapter(payPal);
            PaymentProcessor stripeProcessor = new StripeAdapter(stripe);
            PaymentProcessor cryptoProcessor = new CryptoAdapter(crypto, "BTC");
            
            // Process payments using the common interface
            System.out.println("Processing payments through adapters:");
            payPalProcessor.processPayment(100.00);
            stripeProcessor.processPayment(75.50);
            cryptoProcessor.processPayment(0.05);
            
            // Verify payments using the common interface
            System.out.println("\nVerifying payments through adapters:");
            boolean payPalVerified = payPalProcessor.verifyPayment("PAYPAL12345");
            boolean stripeVerified = stripeProcessor.verifyPayment("STRIPE67890");
            boolean cryptoVerified = cryptoProcessor.verifyPayment("BTCXYZ98765");
            
            System.out.println("\nVerification results:");
            System.out.println("PayPal: " + (payPalVerified ? "Success" : "Failed"));
            System.out.println("Stripe: " + (stripeVerified ? "Success" : "Failed"));
            System.out.println("Crypto: " + (cryptoVerified ? "Success" : "Failed"));
        }
    }
}