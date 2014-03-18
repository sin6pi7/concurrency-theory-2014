package lab_01;

/**
 * Created by Jacek Żyła on 18.03.14.
 */
public class SynchronizedCounter implements ICounter{
    private int counter;

    public SynchronizedCounter(int counter) {
        this.counter = counter;
    }

    @Override
    public synchronized void inc() {
        this.counter++;
    }

    @Override
    public synchronized void dec() {
        this.counter--;
    }

    @Override
    public String toString() {
        return String.valueOf(counter);
    }
}
