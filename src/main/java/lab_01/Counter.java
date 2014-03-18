package lab_01;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Jacek Żyła on 18.03.14.
 */
public class Counter implements ICounter {

    private int counter;

    public Counter(int counter) {
        this.counter = counter;
    }

    @Override
    public void inc() {
        this.counter++;
    }

    @Override
    public void dec() {
        this.counter--;
    }

    @Override
    public String toString() {
        return String.valueOf(counter);
    }
}
