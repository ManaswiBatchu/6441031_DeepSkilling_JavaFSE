public class Main {
    public static void main(String[] args) {
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        logger1.log("Starting application...");
        logger2.log("Application running...");

        // Test whether both loggers are the same
        if (logger1 == logger2) {
            System.out.println("Both logger1 and logger2 refer to the same instance.");
        } else {
            System.out.println("Different instances found. Singleton not working.");
        }
    }
}

