package lab03.cw01;

/**
 * Created by Jacek Żyła on 26.03.14.
 */
public class BoundedSemaphore {

    private int users;
    private int bound;
    private Object object = new Object();

    public BoundedSemaphore(int bound, int startingValue) {
        this.bound = bound;
        this.users = startingValue;
    }

    public void acquire() throws InterruptedException {

        synchronized (object) {

            while (users == 0) {
                object.wait();
            }
            users--;
            object.notifyAll();

        }

    }

    public void release() throws InterruptedException {

        synchronized (object) {

            while (users == bound) {
                object.wait();
            }
            users++;
            object.notifyAll();

        }

    }
}
