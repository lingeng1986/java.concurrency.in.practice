package lincoln.lab.java.concurrency;

//import net.jcip.annotations.ThreadSafe;
//
//@ThreadSafe
public class SynSymble {

    private int value;

    private static SynSymble instance;

    /**作为synchronize的对象，应该设置成final，避免被修改导致多次锁到的是不同的实例*/
    private final static Object o =  new Object();

    public synchronized int getNext() {

	return value++;
    }

    public static SynSymble getInstance() {
	if (instance == null) {
	    synchronized (o) {
		if (instance == null) {
            instance= new SynSymble();
		}
	    }
	}

	return instance;
    }

    public static void main(String[] args) {
        SynSymble synSymble1 = SynSymble.getInstance();
        SynSymble synSymble2 = SynSymble.getInstance();
        System.out.println(synSymble1==synSymble2);
        System.out.println(synSymble1.getNext());
        System.out.println(synSymble2.getNext());
    }

}