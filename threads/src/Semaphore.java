public class Semaphore {
    protected int value = 0 ;
    protected Semaphore(int initial) { value = initial-1 ; }
    public synchronized void wait(Device device) {
        value-- ;
        if (value < 0)
            try {
                System.out.println(device.getname() + " (" + device.getType() + ")" + " arrived and waiting");                wait(500) ;
            } catch( InterruptedException e ) { }
        else System.out.println( device.getname() +" (" + device.getType() + ")" +" arrived");
    }
    public synchronized void signal() {

        value++ ; if (value <= 0) notify() ;
    }
}
