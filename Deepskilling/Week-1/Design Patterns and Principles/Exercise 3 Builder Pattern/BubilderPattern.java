package javaaa;
public class BuilderPattern {

    public static class Computer {
        // Required parameters
        private final String cpu;
        private final String ram;
        private final String storage;
        private final String gpu;
        private final String os;
        private final boolean hasBluetooth;
        private final boolean hasWiFi;

        // Private constructor using Builder
        private Computer(ComputerBuilder builder) {
            this.cpu = builder.cpu;
            this.ram = builder.ram;
            this.storage = builder.storage;
            this.gpu = builder.gpu;
            this.os = builder.os;
            this.hasBluetooth = builder.hasBluetooth;
            this.hasWiFi = builder.hasWiFi;
        }

        // Getters for all fields
        public String getCpu() { return cpu; }
        public String getRam() { return ram; }
        public String getStorage() { return storage; }
        public String getGpu() { return gpu; }
        public String getOs() { return os; }
        public boolean hasBluetooth() { return hasBluetooth; }
        public boolean hasWiFi() { return hasWiFi; }

        @Override
        public String toString() {
            return "Computer Configuration:\n" +
                   "CPU: " + cpu + "\n" +
                   "RAM: " + ram + "\n" +
                   "Storage: " + storage + "\n" +
                   "GPU: " + (gpu != null ? gpu : "None") + "\n" +
                   "OS: " + (os != null ? os : "None") + "\n" +
                   "Bluetooth: " + (hasBluetooth ? "Yes" : "No") + "\n" +
                   "WiFi: " + (hasWiFi ? "Yes" : "No");
        }

        public static class ComputerBuilder {
            // Required parameters
            private final String cpu;
            private final String ram;
            
            // Optional parameters with defaults
            private String storage = "1TB HDD";
            private String gpu;
            private String os;
            private boolean hasBluetooth;
            private boolean hasWiFi;

            // Builder constructor with required fields
            public ComputerBuilder(String cpu, String ram) {
                this.cpu = cpu;
                this.ram = ram;
            }

            // Setter methods for optional parameters
            public ComputerBuilder storage(String storage) {
                this.storage = storage;
                return this;
            }

            public ComputerBuilder gpu(String gpu) {
                this.gpu = gpu;
                return this;
            }

            public ComputerBuilder os(String os) {
                this.os = os;
                return this;
            }

            public ComputerBuilder hasBluetooth(boolean hasBluetooth) {
                this.hasBluetooth = hasBluetooth;
                return this;
            }

            public ComputerBuilder hasWiFi(boolean hasWiFi) {
                this.hasWiFi = hasWiFi;
                return this;
            }

            // Build the Computer object
            public Computer build() {
                return new Computer(this);
            }
        }
    }

    public static class BuilderPatternTest {
        public static void main(String[] args) {
            System.out.println("Builder Pattern Implementation for Computer Configuration\n");
            
            // Create a basic computer
            Computer basicComputer = new Computer.ComputerBuilder("Intel i5", "8GB DDR4")
                .build();
            
            System.out.println("Basic Computer:");
            System.out.println(basicComputer);
            System.out.println("---------------------");
            
            // Create a gaming computer
            Computer gamingComputer = new Computer.ComputerBuilder("AMD Ryzen 9", "32GB DDR4")
                .storage("1TB NVMe SSD")
                .gpu("NVIDIA RTX 3080")
                .os("Windows 11 Pro")
                .hasBluetooth(true)
                .hasWiFi(true)
                .build();
                
            System.out.println("Gaming Computer:");
            System.out.println(gamingComputer);
            System.out.println("---------------------");
            
            // Create a developer computer
            Computer devComputer = new Computer.ComputerBuilder("Apple M1 Pro", "16GB")
                .storage("512GB SSD")
                .os("macOS Monterey")
                .hasWiFi(true)
                .build();
                
            System.out.println("Developer Computer:");
            System.out.println(devComputer);
            System.out.println("---------------------");
            
            // Create a server computer
            Computer serverComputer = new Computer.ComputerBuilder("Dual Xeon Gold", "128GB ECC")
                .storage("4TB SSD RAID")
                .os("Ubuntu Server 22.04")
                .build();
                
            System.out.println("Server Computer:");
            System.out.println(serverComputer);
        }
    }
}