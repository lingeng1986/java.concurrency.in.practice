package lincoln.lab.java.concurrency.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * javac 会将for代码块编译为使用迭代器Iterator迭代容器；
 * 
 * 在迭代过程中，如果容器的计数器被修改，则调用next、hasNext时会抛出java.util.
 * ConcurrentModificationException
 * 
 * @author geng.lin
 * 
 */
public class ChangeCollectionWhenIterating {

    private static List<Integer> list = new ArrayList<Integer>();

    /**
     * @param args
     */
    public static void main(String[] args) {

	list.add(1);
	list.add(2);
	list.add(3);

	for (int tem : list) {
	    doInThread(new Executor() {
		public void execute() {
		    list.add(8);
		}
	    });
	    System.out.println(tem);
	}

    }

    private static void doInThread(final Executor executor) {
	(new Thread(new Runnable() {

	    public void run() {
		executor.execute();
	    }
	})).start();
    }

    interface Executor {
	void execute();
    }

}
