import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Network class to manage our WiFi network
class Network {
    // Store the list of devices on the network
    private List<Device> devices = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    // Add a new device to the list of devices
    public void addDevice() {
        // Take the input from the user as (device name) (device type)
        String input = scanner.nextLine();
        // Split the input into two strings according to the space
        String[] parts = input.split(" ", 2);
        // Create the device object with the desired name & type
        Device newDevice = new Device(parts[0], parts[1]);
        // Add the device to the list of devices wishing to connect
        devices.add(newDevice);
    }

    // Print the list of devices on the network
    public void printDevices() {
        for (Device device : devices) {
            System.out.println(device.getName() + " (" + device.getType() + ")");
        }
    }

    // Main method to start the program
    public static void main(String[] args) {
        Network net = new Network();
        Scanner sc = net.scanner;
        System.out.println("\tWelcome to the WiFi Router Simulator!");
        try {
            System.out.print("What is the MAX number of Wi-Fi connections? ");
            int maxConnections = sc.nextInt();
            System.out.print("What is the total number of Client devices want to connect? ");
            int totalDevices = sc.nextInt();
            // Router router = new Router(maxConnections, totalDevices);
            sc.nextLine(); // Clean the scanner buffer
            System.out.println("\n\tEnter device NAME followed by its TYPE separated by a space!");
            // Get devices data from the user one by one
            while (totalDevices > 0) {
                net.addDevice();
                totalDevices--;
            }
            System.out.println("\tDevices Added Successfully");
            net.printDevices();
            System.out.println("\tThanks for using our WiFi Router Simulator!");
        } catch (Exception e) {
            System.err.println("\n\tError: " + e.getMessage());
        }
        sc.close();
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

    // Getters
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
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

    // Getters
    public int getConnsNumber() {
        return maxConnections;
    }

    public int getDevicesNumber() {
        return nDevices;
    }
}
