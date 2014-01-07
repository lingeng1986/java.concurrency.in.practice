package lincoln.lab.java.concurrency.completionservice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 * User: geng.lin
 * Date: 14-1-4
 * Time: ÏÂÎç10:10
 * To change this template use File | Settings | File Templates.
 */
public class Renderer {
    private final ExecutorService executorService;

    Renderer(ExecutorService executorService) {
        this.executorService = executorService;
    }

    void renderPage(CharSequence source) {
        List<Integer> info = scanForImageInfo(source);
        CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(executorService);
        for (final Integer i : info) {
            completionService.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    Thread.sleep(i);
                    System.out.println("rendering image:i="+i);
                    return i;
                }
            });
        }
        //renderText(source);

        try {
            for (int t = 0; t < info.size(); t++) {
                Future<Integer> f = completionService.take();
                int result = f.get();
                System.out.println(result);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private List<Integer> scanForImageInfo(CharSequence source) {
        List<Integer> integers = new ArrayList<Integer>();
        return integers;

    }
}
