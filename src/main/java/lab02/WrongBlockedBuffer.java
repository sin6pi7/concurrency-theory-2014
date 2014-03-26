package lab02;

/**
 * Created by Jacek Żyła on 20.03.14.
 */
public class WrongBlockedBuffer extends BlockedBuffer {
    private int[] buffer = new int[1];
    private int lastElement = 0;
    private final Object lock = new Object();

    public void put (int newElement) throws InterruptedException {

        synchronized (lock) {
            if ( lastElement == buffer.length ) {
                lock.wait();
            }

            buffer[lastElement++] = newElement;
            lock.notifyAll();
        }

    }

    public int pop () throws InterruptedException {

        synchronized (lock) {
            if ( lastElement <= 0 ) {
                lock.wait();
            }

            lock.notifyAll();
            return buffer[--lastElement];
        }

    }
}
