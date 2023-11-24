import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Network {
    // Store the list of devices on the network
    //max number of connections a router can accept
    public static void main(String[] args) throws InterruptedException {
        int  numberOfConnections, numberOfDecives;
        ArrayList<Device> devices = new ArrayList<>();

        Scanner input = new Scanner(System.in);

        System.out.println("What is number of WI-FI Connections?");
        numberOfConnections = input.nextInt();
        Router router = new Router(numberOfConnections);

        System.out.println("What is number of devices Clients want to connect?");
        numberOfDecives = input.nextInt();


        for (int i = 0; i < numberOfDecives; i++) {
            Device newDevice = new Device(input.next(),input.next(), router);
            devices.add(newDevice);
        }

        for(Device device:devices){
            device.start();
        }

    }
}
