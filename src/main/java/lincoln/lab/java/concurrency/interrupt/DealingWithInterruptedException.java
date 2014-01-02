package lincoln.lab.java.concurrency.interrupt;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 外围系统调用线程的interrupt()方法后，当线程使用wait、sleep等操作时，将会进入InterruptedException处理阶段。
 * 
 * @author geng.lin
 * 
 */
public class DealingWithInterruptedException implements Runnable {

    BlockingQueue<String> queue;

    /**
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
	DealingWithInterruptedException e = new DealingWithInterruptedException();
	e.queue = new ArrayBlockingQueue<String>(100);

	Thread thread = new Thread(e);

	e.queue.offer("a");

	thread.start();
	Thread.sleep(100);
	thread.interrupt();
	Thread.sleep(100);
	e.queue.offer("b");

    }

    public void run() {
	boolean goOn = true;
	while (goOn) {
	    try {
		System.out.println(queue.take());
	    } catch (InterruptedException e) {
		int random = (new Random()).nextInt();
		System.out.println("Oops:" + random);
		if (random % 2 == 0) {
		    System.out.println("it's over:" + random);
		    goOn = false;
		}

	    }
	}
    }
}
