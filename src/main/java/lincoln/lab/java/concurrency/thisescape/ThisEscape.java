package lincoln.lab.java.concurrency.thisescape;

/**
 * ThisEscape对象未构造完成时，构造函数中启动的对象已经可以获取并使用this。
 * 
 * 比如新起一个线程，这个线程就可以通过this使用该对象，这时候对象还没构造完成，则导致引用逸出。
 * 
 * @author geng.lin
 * 
 */
public class ThisEscape {

    private String status = "I";

    public ThisEscape() {
	(new Runnable() {

	    public void run() {
		System.out.println(this.toString());
	    }
	}).run();
    }

    public String getStatus() {
	return status;
    }

    public void setStatus(String status) {
	this.status = status;
    }

    public String toString() {
	return status;
    }

    public static void main(String[] args) {
	ThisEscape escape = new ThisEscape();
	System.out.println(escape);
    }
}
