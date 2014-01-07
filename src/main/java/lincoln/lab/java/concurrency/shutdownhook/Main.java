package lincoln.lab.java.concurrency.shutdownhook;

/**
 * Created with IntelliJ IDEA.
 * User: geng.lin
 * Date: 14-1-5
 * Time: ионГ9:56
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                System.out.print("stop sth.");
            }
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        System.out.print("after hook.");
    }
}
