package lab05;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Jacek Żyła on 16.04.14.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {

        ActiveLogger activeLogger = new ActiveLogger(new StringBuilder("LOGGER:\n"));

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(new LogRunner(activeLogger));
        executorService.submit(new ReadRunner(activeLogger));

    }
}
