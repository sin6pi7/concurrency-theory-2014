package lab01;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Jacek Żyła on 18.03.14.
 */
public class AtomicCounter implements ICounter {

    private AtomicInteger counter;

    public AtomicCounter(int counter) {
        this.counter = new AtomicInteger(counter);
    }

    @Override
    public void inc() {
        this.counter.getAndIncrement();
    }

    @Override
    public void dec() {
        this.counter.getAndDecrement();
    }

    @Override
    public String toString() {
        return String.valueOf(counter);
    }
}
