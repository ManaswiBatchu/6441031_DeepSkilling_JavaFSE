public class Logger {
    // Static private instance of Logger (eager initialization or lazy if needed)
    private static Logger instance;

    // Private constructor to prevent instantiation
    private Logger() {
        System.out.println("Logger instance created.");
    }

    // Public method to provide access to the instance
    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger(); // instance created only once
        }
        return instance;
    }

    // A sample logging method
    public void log(String message) {
        System.out.println("[LOG]: " + message);
    }
}
