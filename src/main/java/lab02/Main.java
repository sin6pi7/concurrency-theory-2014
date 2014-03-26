package lab02;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Jacek Żyła on 20.03.14.
 */
public class Main {
    public static void main(String[] args) {
//        BlockedBuffer blockedBuffer = new BlockedBuffer();
//        ExecutorService executorService = Executors.newFixedThreadPool(8);
//
//        for (int i = 0; i < 40; i++) {
//            executorService.submit(new Consumer(blockedBuffer));
//            executorService.submit(new Producent(blockedBuffer));
//        }
//
//        executorService.shutdown();

        WrongBlockedBuffer wrongBlockedBuffer = new WrongBlockedBuffer();
        ExecutorService wrongExecutorService = Executors.newFixedThreadPool(4);

        for (int i = 0; i < 2; i++) {
            wrongExecutorService.submit(new Consumer(wrongBlockedBuffer));
        }
        for (int i = 0; i < 2; i++) {
            wrongExecutorService.submit(new Producent(wrongBlockedBuffer));
        }

//        wrongExecutorService.shutdown();



    }
}
