package lab03.cw02;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Jacek Żyła on 20.03.14.
 */
public class Main {
    public static void main(String[] args) {

        BlockedBuffer blockedBuffer = new BlockedBuffer(10);
        ExecutorService executorService = Executors.newFixedThreadPool(8);

        for (int i = 0; i < 40; i++) {
            executorService.submit(new Consumer(blockedBuffer));
            executorService.submit(new Producer(blockedBuffer));
        }

        executorService.shutdown();

    }
}
