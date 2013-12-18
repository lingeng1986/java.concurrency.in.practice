package lincoln.lab.java.concurrency.listhelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author geng.lin
 * 
 * @param <E>
 */
public class ListHelper<E> {

    public List<E> list = Collections.synchronizedList(new ArrayList<E>());

    public synchronized boolean putIfAbsent(E e) {
	boolean absent = !list.contains(e);
	if (absent) {
	    list.add(e);
	} else {
	    System.out.println(e + " is existed.");
	}
	return absent;
    }

    public String toString() {
	return this.list.toString();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
	final ListHelper<Integer> helper = new ListHelper<Integer>();
	for (int index = 0; index < 10; index++) {
	    final int temp = index;
	    doInThread(new Execute() {
		public void execute() {
		    helper.putIfAbsent(temp);
		}
	    });

	    doInThread(new Execute() {
		public void execute() {
		    helper.list.add(temp);
		}
	    });
	}
	System.out.println(helper);
    }

    private static void doInThread(final Execute execute) {
	(new Thread(new Runnable() {

	    public void run() {
		execute.execute();
	    }
	})).start();
    }

    interface Execute {
	void execute();
    }
}
