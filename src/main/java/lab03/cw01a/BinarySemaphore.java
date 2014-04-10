package lab03.cw01a;

/**
 * Created by Jacek Żyła on 10.04.14.
 */
public class BinarySemaphore {

    private int permissions = 1;
    private Object object = new Object();

    public void V() throws InterruptedException {

        synchronized (object) {

            this.permissions = 1;
            object.notifyAll();

        }

    }

    public void P() throws InterruptedException {

        synchronized (object) {

            while (permissions == 0) {
                object.wait();
            }
            this.permissions = 0;

        }

    }
}
