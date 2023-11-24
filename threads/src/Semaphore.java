public class Semaphore {
    protected int value = 0 ;
    protected Semaphore(int initial) { value = initial ; }
    public synchronized void wait(Device device) {
        value-- ;
        if (value < 0)
            try {
                System.out.println(device.getname() + " (" + device.getType() + ")" + " arrived and waiting");
                wait() ;
            } catch( InterruptedException e ) { }
        else System.out.println( device.getname() +" (" + device.getType() + ")" +" arrived");
    }
    public synchronized void signal() {

        value++ ; if (value <= 0) notify() ;
    }
}
