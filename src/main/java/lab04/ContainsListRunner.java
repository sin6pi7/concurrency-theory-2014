package lab04;

/**
 * Created by Jacek Żyła on 10.04.14.
 */
public class ContainsListRunner implements Runnable {

    private final IMyLinkedList myList;
    private final int iterations;

    public ContainsListRunner(IMyLinkedList myList, int iterations) {
        this.myList = myList;
        this.iterations = iterations;
    }

    @Override
    public void run() {

        for (int i = 0; i < iterations; i++) {
            myList.contains(new BigObject(i));
        }

    }
}
