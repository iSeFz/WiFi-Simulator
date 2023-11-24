import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Network class to manage our WiFi network
class Network {
    public static void main(String[] args) {
        // Store the list of devices on the network
        Scanner scanner = new Scanner(System.in);
        int maxConnections, nDevices;
        ArrayList<Device> devices = new ArrayList<>();
        System.out.println("\tWelcome to the WiFi Router Simulator!");
        try {
            System.out.print("What is the number of WI-FI connections? ");
            maxConnections = scanner.nextInt();

            // Create a router object with the max number of connections
            Router router = new Router(maxConnections);

            System.out.print("What is the number of devices Clients want to connect? ");
            nDevices = scanner.nextInt();

            System.out.println("\n\tEnter device NAME followed by its TYPE separated by a space!");
            // Get devices name & type from the user
            for (int i = 0; i < nDevices; i++) {
                // Take the input from the user as (device name) (device type)
                Device newDevice = new Device(scanner.next(), scanner.next(), router);
                // Add the device to the list of devices
                devices.add(newDevice);
            }

            // Get the current directory & redirect to the log file to write in
            String outputFile = Path.of(Paths.get("").toAbsolutePath().toString()).resolve("logfile.txt").toString();

            FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
            PrintStream printStream = new PrintStream(fileOutputStream);

            // Change system output stream to be the log file
            System.setOut(printStream);

            // Start a connection for each device in the list
            for (Device device : devices) {
                device.start();
            }

            // Join threads until each thread completes its work
            for (Device device : devices) {
                device.join();
            }
        } catch (Exception e) {
            System.err.println("\n\tError: " + e.getMessage());
        }

        // Reset the output stream back again to the standard output
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        System.out.println("\tThanks for using our Wi-Fi Router Simulator!");
        scanner.close(); // Close the scanner after finishing the program
    }
}

// Device class to store device data & methods
class Device extends Thread {
    private String name;
    private String type;
    private final Router router;

    // Constructors
    public Device(String newName, String newType, Router newRouter) {
        name = newName;
        type = newType;
        router = newRouter;
    }

    // Getters
    public String getDeviceName() {
        return name;
    }

    public String getType() {
        return type;
    }

    // Override the run method in the Thread class to do specific functions
    // Related to our purposes and our program domain
    @Override
    public void run() {
        try {
            router.getSemaphore().wait(this);
            router.connect(this);
            router.performOnlineActivity(this);
            router.disconnect(this);
            router.getSemaphore().signal();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
        }
    }
}

// Router class to route devices through the network
class Router {
    private int maxConnections;
    private List<Device> devices;
    public Semaphore semaphore;

    // Constructors
    public Router(int maxConns) {
        maxConnections = maxConns;
        semaphore = new Semaphore(maxConnections);
        devices = new ArrayList<>();
    }

    // Getter for semaphore object
    public Semaphore getSemaphore() { return semaphore; }

    // Connect method to conncet device to the network
    public synchronized void connect(Device device) {
        devices.add(device);
        System.out.println("Connection " + ((Thread.currentThread().threadId() % maxConnections) + 1)
                + ": " + device.getDeviceName() + " Occupied");
        System.out.println("Connection " + ((Thread.currentThread().threadId() % maxConnections) + 1) + ": "
                + device.getDeviceName() + " Logged in");
    }

    // Dummy function to emphasize the online activity done by the connected device
    public void performOnlineActivity(Device device) {
        System.out.println("Connection " + ((Thread.currentThread().threadId() % maxConnections) + 1) + ": "
                + device.getDeviceName() + " is performing online activity");
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
        }
    }

    // Disconnect / Logout from the network
    public synchronized void disconnect(Device device) {
        devices.remove(device);
        System.out.println("Connection " + ((Thread.currentThread().threadId() % maxConnections) + 1) +
                ": " + device.getDeviceName() + " Logged out");
    }
}

// Semaphore class to manage the synchronization
class Semaphore {
    protected int value = 0;

    public Semaphore(int initial) {
        value = initial;
    }

    // Wait method to wait the device if there are
    // No connections available on the network
    public synchronized void wait(Device device) {
        value--;
        if (value < 0) {
            System.out.println(device.getDeviceName() + " (" + device.getType() + ") arrived & waiting");
            try {
                wait();
            } catch (Exception e) {
                System.err.println("ERROR: " + e.getMessage());
            }
        } else
            System.out.println(device.getDeviceName() + " (" + device.getType() + ") arrived");
    }

    // Signal method to notify the device if a place
    // Became available on the network
    public synchronized void signal() {
        value++;
        if (value <= 0)
            notify();
    }
}
