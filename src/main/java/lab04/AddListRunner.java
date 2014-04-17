package lab04;

/**
 * Created by Jacek Żyła on 10.04.14.
 */
public class AddListRunner implements Runnable {

    private final IMyLinkedList myList;
    private final int iterations;

    public AddListRunner(IMyLinkedList myList, int iterations) {
        this.myList = myList;
        this.iterations = iterations;
    }

    @Override
    public void run() {


    }
}
