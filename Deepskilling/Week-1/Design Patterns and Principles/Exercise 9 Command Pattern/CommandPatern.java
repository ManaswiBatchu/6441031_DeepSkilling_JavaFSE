package javaaa;

public class CommandPattern {

    public interface Command {
        void execute();
    }

    public static class Light {
        private String location;

        public Light(String location) {
            this.location = location;
        }

        public void on() {
            System.out.println(location + " light is ON");
        }

        public void off() {
            System.out.println(location + " light is OFF");
        }
    }

    // 3. Concrete Commands
    public static class LightOnCommand implements Command {
        private Light light;

        public LightOnCommand(Light light) {
            this.light = light;
        }

        @Override
        public void execute() {
            light.on();
        }
    }

    public static class LightOffCommand implements Command {
        private Light light;

        public LightOffCommand(Light light) {
            this.light = light;
        }

        @Override
        public void execute() {
            light.off();
        }
    }

    // 4. Invoker Class
    public static class RemoteControl {
        private Command command;

        public void setCommand(Command command) {
            this.command = command;
        }

        public void pressButton() {
            command.execute();
        }
    }

    // 5. Test Class
    public static class CommandTest {
        public static void main(String[] args) {
            System.out.println("Home Automation using Command Pattern\n");

            // Create receivers
            Light livingRoomLight = new Light("Living Room");
            Light kitchenLight = new Light("Kitchen");

            // Create commands
            Command livingRoomLightOn = new LightOnCommand(livingRoomLight);
            Command livingRoomLightOff = new LightOffCommand(livingRoomLight);
            Command kitchenLightOn = new LightOnCommand(kitchenLight);
            Command kitchenLightOff = new LightOffCommand(kitchenLight);

            // Create invoker
            RemoteControl remote = new RemoteControl();

            // Control living room light
            System.out.println("--- Living Room Light Control ---");
            remote.setCommand(livingRoomLightOn);
            remote.pressButton();
            remote.setCommand(livingRoomLightOff);
            remote.pressButton();

            // Control kitchen light
            System.out.println("\n--- Kitchen Light Control ---");
            remote.setCommand(kitchenLightOn);
            remote.pressButton();
            remote.setCommand(kitchenLightOff);
            remote.pressButton();

            // Macro command (turn all lights on)
            System.out.println("\n--- Party Mode (All Lights On) ---");
            Command[] partyOn = {livingRoomLightOn, kitchenLightOn};
            for (Command cmd : partyOn) {
                remote.setCommand(cmd);
                remote.pressButton();
            }
        }
    }
}