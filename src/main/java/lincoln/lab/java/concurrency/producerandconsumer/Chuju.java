package lincoln.lab.java.concurrency.producerandconsumer;

public class Chuju {

    private final int id;

    private boolean dirty;

    private boolean wet;

    public Chuju(int id) {
	this.id = id;
    }

    public synchronized void use() {
	dirty = true;
    }

    public synchronized void clean() {
	System.out.println(id + " cleaning.");
	dirty = false;
	wet = true;
    }

    public synchronized void burn() {
	System.out.println(id + " burning.");
	wet = false;
    }

    public boolean isOk() {
	return !(dirty || wet);
    }

    public int getId() {
	return id;
    }
}
