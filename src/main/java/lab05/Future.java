package lab05;

/**
 * Created by Jacek Żyła on 17.04.14.
 */
public class Future<T> {
    private boolean isDone;
    private T resource;
    private final Object LOCK = new Object();

    public Future() {
    }

    public T getResource() throws InterruptedException {

        synchronized (LOCK) {
            while (!isDone) {
                LOCK.wait();
            }
            return resource;
        }
    }

    public void setResource(T resource) {
        synchronized (LOCK) {
            this.resource = resource;
            this.isDone = true;
            LOCK.notify();
        }
    }
}
