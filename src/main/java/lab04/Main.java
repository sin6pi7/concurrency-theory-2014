package lab04;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Jacek Żyła on 10.04.14.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {

//        MyLinkedList myLinkedList = new MyLinkedList();
        MyDumbLinkedList myLinkedList = new MyDumbLinkedList();

        double startTime;
        double endTime;
        int ITERATIONS = 5000;
        int[] object = new int[1000];
        int[] object2 = new int[1000];

        for (int i = 0; i < 1000; i++) {
            object[i] = Integer.MAX_VALUE;
            object2[i] = Integer.MAX_VALUE;
        }

        System.out.println(object.equals(object2));
//
//        startTime = System.nanoTime();
//
//        ExecutorService executorService = Executors.newFixedThreadPool(4);
//
//        for (int i = 0; i < 10; i++) {
//            executorService.submit(new TestListRunner(myLinkedList, ITERATIONS, object));
//        }
//
//        executorService.shutdown();
//
//        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
//
//        endTime = System.nanoTime();
//
//
//
//        System.out.println("FINISHED TEST. IT TOOK " + (endTime - startTime)/1000000000 + "s");

    }
}
