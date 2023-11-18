import java.util.ArrayList;
import java.util.List;

// Network class to manage our WiFi network
class Network {
    // Store the list of connected devices
    private List<Device> devices = new ArrayList<>();

    // Main method to start the program
    public static void main(String[] args) {
        Network net = new Network();
        System.out.println("\tWelcome to the WiFi Router Simulator!");
    }
}

// Semaphore class to manage the synchronization
class Semaphore {
    // To be implemented
}

// Device class to store device data & methods
class Device {
    private String name;
    private String type;

    // Constructors
    public Device() {
        name = "";
        type = "";
    }

    public Device(String newName, String newType) {
        name = newName;
        type = newType;
    }

    // Connect method to conncet device to the network
    public void connect() {
        System.out.println("\n\tDevice  is now connected!");
    }

    // Dummy function to emphasize the online activity done by the connected device
    public void performActivity() {
        System.out.println("\n\tClient is performing online activity!");
    }

    // Disconnect from the network
    public void disconnect() {
        System.out.println("\n\tClient is now disconnected!");
    }
}

// Router class to route devices through the network
class Router {
    private int maxConnections;
    private int nDevices;

    // Constructors
    public Router() {
        maxConnections = 0;
        nDevices = 0;
    }

    public Router(int maxConns, int nDevs) {
        maxConnections = maxConns;
        nDevices = nDevs;
    }
}
