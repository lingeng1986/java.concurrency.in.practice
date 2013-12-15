package lincoln.lab.java.concurrency;

import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class SynSymble {

    private int value;

    private Object o;

    public synchronized int getNext() {

	return value++;
    }

    public Object getInstance() {
	if (o == null) {
	    synchronized (o) {
		if (o == null) {
		    o = new Object();
		}
	    }
	}

	return o;
    }

}