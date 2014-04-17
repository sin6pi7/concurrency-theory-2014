package lab04;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Jacek Żyła on 10.04.14.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {

        MyLinkedList myLinkedList = new MyLinkedList();
//        MyDumbLinkedList myLinkedList = new MyDumbLinkedList();

        double startTime;
        double endTime;
        int ITERATIONS = 5000;

        System.out.println("TESTING PERFORMANCE OF " + myLinkedList.getClass().getCanonicalName());

        // ADD TEST
        startTime = System.nanoTime();
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        for (int i = 0; i < 10; i++) {
            executorService.submit(new AddListRunner(myLinkedList, ITERATIONS));
        }

        executorService.shutdown();
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

        endTime = System.nanoTime();
        System.out.println("FINISHED ADD TEST. IT TOOK " + (endTime - startTime)/1000000000 + "s");

        // CONTAINS TEST
        startTime = System.nanoTime();
        ExecutorService executorService1 = Executors.newFixedThreadPool(4);

        for (int i = 0; i < 10; i++) {
            executorService1.submit(new ContainsListRunner(myLinkedList, ITERATIONS));
        }

        executorService1.shutdown();

        executorService1.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

        endTime = System.nanoTime();

        System.out.println("FINISHED CONTAINS TEST. IT TOOK " + (endTime - startTime)/1000000000 + "s");

        // REMOVE TEST
        startTime = System.nanoTime();
        ExecutorService executorService2 = Executors.newFixedThreadPool(4);

        for (int i = 0; i < 10; i++) {
            executorService2.submit(new RemoveListRunner(myLinkedList, ITERATIONS));
        }

        executorService2.shutdown();

        executorService2.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

        endTime = System.nanoTime();

        System.out.println("FINISHED REMOVE TEST. IT TOOK " + (endTime - startTime)/1000000000 + "s");


    }
}
