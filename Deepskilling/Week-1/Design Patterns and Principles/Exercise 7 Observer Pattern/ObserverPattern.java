package javaaa;

import java.util.ArrayList;
import java.util.List;

public class ObserverPattern {

    public interface Stock {
        void registerObserver(Observer o);
        void removeObserver(Observer o);
        void notifyObservers();
    }

    public static class StockMarket implements Stock {
        private List<Observer> observers = new ArrayList<>();
        private String stockName;
        private double price;

        public StockMarket(String stockName, double initialPrice) {
            this.stockName = stockName;
            this.price = initialPrice;
        }

        public void setPrice(double newPrice) {
            if (this.price != newPrice) {
                this.price = newPrice;
                notifyObservers();
            }
        }

        @Override
        public void registerObserver(Observer o) {
            observers.add(o);
        }

        @Override
        public void removeObserver(Observer o) {
            observers.remove(o);
        }

        @Override
        public void notifyObservers() {
            for (Observer observer : observers) {
                observer.update(stockName, price);
            }
        }
    }

    // 3. Observer Interface
    public interface Observer {
        void update(String stockName, double price);
    }

    // 4. Concrete Observers
    public static class MobileApp implements Observer {
        private String appName;

        public MobileApp(String appName) {
            this.appName = appName;
        }

        @Override
        public void update(String stockName, double price) {
            System.out.println(appName + " (Mobile) - " + stockName + 
                             " price updated: $" + price);
        }
    }

    public static class WebApp implements Observer {
        private String appName;

        public WebApp(String appName) {
            this.appName = appName;
        }

        @Override
        public void update(String stockName, double price) {
            System.out.println(appName + " (Web) - " + stockName + 
                             " price updated: $" + price);
        }
    }

    public static class EmailService implements Observer {
        @Override
        public void update(String stockName, double price) {
            System.out.println("Email Alert - " + stockName + 
                             " price changed to: $" + price);
        }
    }

    // 5. Test Class
    public static class ObserverTest {
        public static void main(String[] args) {
            System.out.println("Stock Market Monitoring using Observer Pattern\n");

            // Create stock market for Apple
            StockMarket appleStock = new StockMarket("AAPL", 150.25);

            // Create observers
            Observer mobileApp = new MobileApp("StockTracker");
            Observer webApp = new WebApp("MarketWatch");
            Observer emailService = new EmailService();

            // Register observers
            appleStock.registerObserver(mobileApp);
            appleStock.registerObserver(webApp);
            appleStock.registerObserver(emailService);

            // First price change
            System.out.println("Apple stock price changes to $152.50");
            appleStock.setPrice(152.50);

            // Remove one observer
            appleStock.removeObserver(emailService);

            // Second price change
            System.out.println("\nApple stock price changes to $153.75");
            appleStock.setPrice(153.75);

            // Register new observer
            Observer newMobileApp = new MobileApp("FinancePro");
            appleStock.registerObserver(newMobileApp);

            // Third price change
            System.out.println("\nApple stock price changes to $151.80");
            appleStock.setPrice(151.80);
        }
    }
}