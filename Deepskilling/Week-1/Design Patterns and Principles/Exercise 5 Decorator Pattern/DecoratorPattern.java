package javaaa;

public class DecoratorPattern {

    // Component Interface
    public interface Notifier {
        void send(String message);
    }

    //Concrete Component
    public static class EmailNotifier implements Notifier {
        @Override
        public void send(String message) {
            System.out.println("Sending Email notification: " + message);
        }
    }

    //Base Decorator
    public static abstract class NotifierDecorator implements Notifier {
        protected Notifier wrappedNotifier;

        public NotifierDecorator(Notifier notifier) {
            this.wrappedNotifier = notifier;
        }

        @Override
        public void send(String message) {
            wrappedNotifier.send(message);
        }
    }

    //Concrete Decorators
    public static class SMSNotifierDecorator extends NotifierDecorator {
        public SMSNotifierDecorator(Notifier notifier) {
            super(notifier);
        }

        @Override
        public void send(String message) {
            super.send(message);
            System.out.println("Sending SMS notification: " + message);
        }
    }

    public static class SlackNotifierDecorator extends NotifierDecorator {
        public SlackNotifierDecorator(Notifier notifier) {
            super(notifier);
        }

        @Override
        public void send(String message) {
            super.send(message);
            System.out.println("Sending Slack notification: " + message);
        }
    }

    public static class FacebookNotifierDecorator extends NotifierDecorator {
        public FacebookNotifierDecorator(Notifier notifier) {
            super(notifier);
        }

        @Override
        public void send(String message) {
            super.send(message);
            System.out.println("Sending Facebook notification: " + message);
        }
    }

    // Test Class
    public static class DecoratorTest {
        public static void main(String[] args) {
            System.out.println("Notification System using Decorator Pattern\n");
            
            String alertMessage = "Server is down! Immediate action required!";
            
            // Simple email notification
            System.out.println("=== Email Only ===");
            Notifier emailNotifier = new EmailNotifier();
            emailNotifier.send(alertMessage);
            
            // Email + SMS
            System.out.println("\n=== Email + SMS ===");
            Notifier smsNotifier = new SMSNotifierDecorator(new EmailNotifier());
            smsNotifier.send(alertMessage);
            
            // Email + SMS + Slack
            System.out.println("\n=== Email + SMS + Slack ===");
            Notifier slackNotifier = new SlackNotifierDecorator(
                                      new SMSNotifierDecorator(
                                          new EmailNotifier()));
            slackNotifier.send(alertMessage);
            
            // All notification channels
            System.out.println("\n=== All Notification Channels ===");
            Notifier allNotifier = new FacebookNotifierDecorator(
                                     new SlackNotifierDecorator(
                                         new SMSNotifierDecorator(
                                             new EmailNotifier())));
            allNotifier.send(alertMessage);
        }
    }
}