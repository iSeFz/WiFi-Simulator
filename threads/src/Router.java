import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Router {
    private int maxConnections;
    private int nDevices;
    private List<Device> Connections;
    public Semaphore Sem;
    public int connectionID;
    public Router() {
        maxConnections = 0;
    }

    public Router(int maxConns) {
        maxConnections = maxConns;
        Sem=new Semaphore(maxConnections);
        Connections=new ArrayList<>();
        connectionID=1;
    }

    public void Connect(Device value) {
        Sem.wait(value);
        connectionID++;
        Connections.add(value);
        System.out.println("Connection "+((connectionID%maxConnections)+1)+": "+value.getname()+" Occupied");
    }
    public void PerformOnlineActivity(Device deviceName) throws InterruptedException {
        System.out.println("Connection "+((connectionID%maxConnections)+1)+": "+deviceName.getname()+ " login");
        System.out.println("Connection "+((connectionID%maxConnections)+1)+": "+deviceName.getname() + " is performing online activity.");
        Thread.sleep(500);
    }
    public void disconnect(Device deviceName) {
        connectionID--;
        Connections.remove(deviceName);
        System.out.println("Connection "+((connectionID%maxConnections)+1)+": "+deviceName.getname() + " Logged out.");
        Sem.signal();
    }
}
