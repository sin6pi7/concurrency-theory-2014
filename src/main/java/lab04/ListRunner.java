package lab04;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Thread.sleep;

/**
 * Created by Jacek Żyła on 10.04.14.
 */
public class ListRunner implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(ListRunner.class.getName());
    private static final Random random = new Random();
    private final MyLinkedList myLinkedList;

    public ListRunner(MyLinkedList myLinkedList) {
        this.myLinkedList = myLinkedList;
    }

    @Override
    public void run() {

        int newElement = random.nextInt(1000);
        switch (random.nextInt(1)) {
            case 0:  {
                myLinkedList.add(newElement);
            }
            case 1:  {
                myLinkedList.remove(random.nextInt(1000));
            }
        }

    }
}
