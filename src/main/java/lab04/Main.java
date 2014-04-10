package lab04;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Jacek Żyła on 10.04.14.
 */
public class Main {
    public static void main(String[] args) {

        MyLinkedList myLinkedList = new MyLinkedList();

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        for (int i = 0; i < 10; i++) {
            executorService.submit(new ListRunner(myLinkedList));
        }

        executorService.shutdown();

        myLinkedList.print();
    }
}
