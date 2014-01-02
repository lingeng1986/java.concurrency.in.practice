package lincoln.lab.java.concurrency.interrupt;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 处理中断的方法：
 * 
 * 1.将中断异常IterruptedException捕获，清理现场（释放资源等）然后抛给调用方处理；
 * 2.（本类情况）在新线程中捕获IterruptedException
 * ，如果要中断，则通过Thread.currentThread().interrupt()中断掉。
 * 
 * @author geng.lin
 * 
 */
public class DeliverInterruptedExceptionToCurrentThread implements Runnable {

    private BlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);

    /**
     * @param args
     */
    public static void main(String[] args) {
	Thread thread = new Thread(
		new DeliverInterruptedExceptionToCurrentThread());

	System.out.println("thread id in main:"
		+ Thread.currentThread().getId());
	thread.start();

	thread.interrupt();

	try {
	    Thread.sleep(100);
	} catch (InterruptedException e) {
	    System.out.println("e when sleep:" + e);
	    e.printStackTrace();
	}

	System.out.println(Thread.currentThread().isInterrupted());
    }

    public void run() {
	System.out
		.println("thread id in run:" + Thread.currentThread().getId());
	try {
	    for (;;) {
		System.out.println(queue.take());
	    }
	} catch (InterruptedException e) {
	    System.out.println("e in run:" + e);
	    Thread.currentThread().interrupt();
	}
    }
}
