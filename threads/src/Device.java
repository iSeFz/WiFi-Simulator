import java.util.List;

public class Device extends Thread {
    private String name;
    private String type;
    private final Router router;
    public Device(String name,String type,Router router) {
        this.name=name;
        this.type=type;
        this.router = router;
    }

    // Getters
    public String getname() {
        return name;
    }

    public String getType() {
        return type;
    }


    @Override
    public void run() {
            try {
                router.Sem.wait(this);
                router.Connect(this);
                router.PerformOnlineActivity(this);
                router.disconnect(this);
                router.Sem.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

}
