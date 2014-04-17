package lab04;

import java.util.Random;
import java.util.logging.Logger;

import static java.lang.Thread.sleep;

/**
 * Created by Jacek Żyła on 10.04.14.
 */
public class ListRunner implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(ListRunner.class.getName());
    private static final Random random = new Random();
    private final IMyLinkedList myList;

    public ListRunner(IMyLinkedList myList) {
        this.myList = myList;
    }

    @Override
    public void run() {

        int element;

        while (true) {
            element = random.nextInt();
            switch (random.nextInt(1000) % 3) {
                case 0:  {
                    System.out.println("RUNNER IS ADDING");
                    myList.add(element);
                    break;
                }
                case 1:  {
                    System.out.println("RUNNER IS REMOVING");
                    myList.remove(element);
                    break;
                }
                case 2:  {
                    System.out.println("RUNNER IS PRINTING");
                    myList.print();
                    break;
                }
            }
        }
    }
}
