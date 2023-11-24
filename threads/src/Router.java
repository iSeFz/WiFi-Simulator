import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Router {
    public int maxConnections;
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

    public synchronized void Connect(Device value) {
        connectionID++;
        Connections.add(value);
        System.out.println("Connection "+((Thread.currentThread().threadId()%maxConnections)+1)+": "+value.getname()+" Occupied");
    }
    public void PerformOnlineActivity(Device deviceName) throws InterruptedException {
        System.out.println("Connection "+((Thread.currentThread().threadId()%maxConnections)+1)+": "+deviceName.getname()+ " login");
        System.out.println("Connection "+((Thread.currentThread().threadId()%maxConnections)+1)+": "+deviceName.getname() + " is performing online activity.");
        Thread.sleep(1000);
    }
    public synchronized void disconnect(Device deviceName) {
        connectionID--;
        Connections.remove(deviceName);
        notify();
        System.out.println("Connection "+((Thread.currentThread().threadId()%maxConnections)+1)+": "+deviceName.getname() + " Logged out.");
    }
}
