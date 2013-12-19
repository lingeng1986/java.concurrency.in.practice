package lincoln.lab.java.concurrency.producerandconsumer;

/**
 * Çå½à»úÆ÷
 * 
 * @author geng.lin
 * 
 */
public class Cleaner {

    public void clean(Chuju chuju) {
	chuju.clean();
	try {
	    Thread.sleep(100);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
    }
}
