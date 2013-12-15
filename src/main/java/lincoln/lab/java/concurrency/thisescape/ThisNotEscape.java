package lincoln.lab.java.concurrency.thisescape;

/**
 * 
 * ThisNotEscape对象启动后，要启动一个线程，并在里面打印该对象；用这个方式就不会导致逸出；
 * 
 * 对比ThisEscape
 * 
 * @author geng.lin
 * 
 */
public class ThisNotEscape {

    private String status = "I";

    private static void runSomething(final ThisNotEscape e) {
	(new Runnable() {

	    public void run() {
		System.out.println(e);
	    }
	}).run();
    }

    private ThisNotEscape() {
    }

    public static ThisNotEscape getInstance() {
	ThisNotEscape e = new ThisNotEscape();
	runSomething(e);
	return e;

    }

    public String toString() {
	return status;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
	ThisNotEscape instance = ThisNotEscape.getInstance();
	System.out.println(instance);

    }
}
