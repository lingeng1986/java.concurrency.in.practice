package lincoln.lab.java.concurrency.future;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TestFutureTask {

    private FutureTask<Integer> future = new FutureTask<Integer>(
	    new Callable<Integer>() {
		public Integer call() throws Exception {
		    Random random = new Random();
		    int result = Math.abs(random.nextInt(10));
		    Thread.sleep(result * 100);
		    System.out.println(Thread.currentThread().getId() + ":"
			    + result);
		    return result;
		}
	    });

    public void start() {
	Thread thread = new Thread(future);
	thread.start();
    }

    public int getResult() throws InterruptedException, ExecutionException {
	return future.get();
    }

    /**
     * @param args
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException,
	    ExecutionException {
	TestFutureTask task = new TestFutureTask();
	task.start();
	System.out.println(task.getResult());

    }

}
