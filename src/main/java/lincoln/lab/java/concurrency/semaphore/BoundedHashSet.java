package lincoln.lab.java.concurrency.semaphore;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

public class BoundedHashSet<T> {

    private final Set<T> set;

    private final Semaphore sem;

    public BoundedHashSet(int bound) {
	set = Collections.synchronizedSet(new HashSet<T>());
	sem = new Semaphore(bound);
    }

    public boolean add(T t) throws InterruptedException {
	sem.acquire();
	System.out.println(t + "acquire success.");
	boolean wasAdded = false;
	try {
	    wasAdded = set.add(t);
	    return wasAdded;
	} finally {
	    if (!wasAdded)
		sem.release();
	}
    }

    public boolean remove(T t) {
	boolean wasRemoved = set.remove(t);
	if (wasRemoved) {
	    sem.release();
	    System.out.println(t + "release success.");
	}
	return wasRemoved;
    }

    /**
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
	final BoundedHashSet<Integer> set = new BoundedHashSet<Integer>(10);
	for (int i = 0; i < 100; i++) {
	    set.add(i);
	    set.remove(i - 1);
	}
	System.out.println(set.set.size());

    }
}
