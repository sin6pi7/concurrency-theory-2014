package lab05;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Jacek Żyła on 16.04.14.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {

        ActiveLogger activeLogger = new ActiveLogger(new StringBuilder("LOGGER:\n"));

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 10; i++) {
            executorService.submit(new LogRunner(activeLogger));
        }

//        for (int i = 0; i < 10; i++) {
//            executorService.submit(new ReadRunner(activeLogger));
//        }

        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
//
//        try {
//            activeLogger.finish();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }
}
