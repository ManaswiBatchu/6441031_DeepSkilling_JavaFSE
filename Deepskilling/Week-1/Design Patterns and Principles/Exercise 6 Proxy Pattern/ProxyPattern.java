package javaaa;

public class ProxyPattern {

    public interface Image {
        void display();
    }

    public static class RealImage implements Image {
        private final String filename;

        public RealImage(String filename) {
            this.filename = filename;
            loadFromDisk();
        }

        private void loadFromDisk() {
            System.out.println("Loading image from remote server: " + filename);
            // Simulate expensive loading operation
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void display() {
            System.out.println("Displaying image: " + filename);
        }
    }

    public static class ProxyImage implements Image {
        private final String filename;
        private RealImage realImage;
        private boolean loaded = false;

        public ProxyImage(String filename) {
            this.filename = filename;
        }

        @Override
        public void display() {
            if (!loaded) {
                realImage = new RealImage(filename);
                loaded = true;
            }
            realImage.display();
        }
    }

    public static class ProxyTest {
        public static void main(String[] args) {
            System.out.println("Image Viewer using Proxy Pattern\n");

            // Images will be loaded only when displayed
            Image image1 = new ProxyImage("nature.jpg");
            Image image2 = new ProxyImage("cityscape.jpg");
            Image image3 = new ProxyImage("portrait.jpg");

            System.out.println("Images created but not loaded yet\n");

            // First display - will load the image
            System.out.println("First display:");
            image1.display();
            System.out.println();

            // Second display - will use cached version
            System.out.println("Second display:");
            image1.display();
            System.out.println();

            // Display other images
            System.out.println("Displaying other images:");
            image2.display();
            image3.display();
            System.out.println();

            // Display cached images again
            System.out.println("Displaying cached images:");
            image2.display();
            image3.display();
        }
    }
}