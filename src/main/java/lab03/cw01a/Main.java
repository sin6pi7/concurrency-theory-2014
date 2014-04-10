package lab03.cw01a;

/**
 * Created by Jacek Żyła on 10.04.14.
 */
public class Main {
    public static void main(String[] args) {

        BinarySemaphore binarySemaphore = new BinarySemaphore();
        ClassicBlockedBuffer classicBlockedBuffer = new ClassicBlockedBuffer(2, binarySemaphore);
//        JBlockedBuffer jBlockedBuffer = new JBlockedBuffer(2);
//        ExecutorService executorService = Executors.newFixedThreadPool(2);
//
//        executorService.submit(new Consumer(classicBlockedBuffer));
//        executorService.submit(new Producer(classicBlockedBuffer));

        Thread consumer = new Thread(new Consumer(classicBlockedBuffer));
        Thread producer = new Thread(new Producer(classicBlockedBuffer));
//        Thread consumer = new Thread(new Consumer(jBlockedBuffer));
//        Thread producer = new Thread(new Producer(jBlockedBuffer));

        consumer.start();
        producer.start();

        try {
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            producer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        for (int i = 0; i < 10; i++) {
//            executorService.submit(new Consumer(classicBlockedBuffer));
//        }
//        for (int i = 0; i < 10; i++) {
//            executorService.submit(new Producer(classicBlockedBuffer));
//        }
    }
}
