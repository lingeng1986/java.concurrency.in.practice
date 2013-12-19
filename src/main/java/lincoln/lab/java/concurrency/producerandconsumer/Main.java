package lincoln.lab.java.concurrency.producerandconsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {

    public interface Execute {
	void execute();
    }

    private static final int CHUJU_COUNT = 10;

    private static BlockingQueue<Chuju> toClean = new LinkedBlockingQueue<Chuju>(
	    10);

    private static BlockingQueue<Chuju> toBurn = new LinkedBlockingQueue<Chuju>(
	    10);

    private static BlockingQueue<Chuju> okChuju = new LinkedBlockingQueue<Chuju>(
	    10);

    private static Cleaner cleaner = new Cleaner();

    private static Burner burner = new Burner();

    /**
     * @param args
     */
    public static void main(String[] args) {

	initOkChuju();

	for (int i = 0; i < 10; i++) {
	    Chuju poll = okChuju.poll();
	    System.out.println(poll.getId() + " using.");
	    try {
		toClean.put(poll);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}

	cleaning();

	burning();

    }

    private static void burning() {
	doInThread(new Execute() {
	    public void execute() {
		for (;;) {
		    try {
			Chuju take = toBurn.take();
			burner.burn(take);
			okChuju.add(take);
		    } catch (InterruptedException e) {
			e.printStackTrace();
		    }
		}
	    }
	});
    }

    private static void cleaning() {
	doInThread(new Execute() {
	    public void execute() {
		for (;;) {
		    try {
			Chuju take = toClean.take();
			cleaner.clean(take);
			toBurn.put(take);
		    } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    }
		}
	    }
	});
    }

    private static void initOkChuju() {
	for (int i = 0; i < CHUJU_COUNT; i++) {
	    okChuju.add(new Chuju(i));
	}
    }

    private static void doInThread(final Execute execute) {
	new Thread(new Runnable() {

	    public void run() {
		execute.execute();
	    }
	}).start();
    }
}
