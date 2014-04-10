package lab03.cw01a;

/**
 * Created by Jacek Żyła on 10.04.14.
 */
public class ClassicSempahore {

    private Object object = new Object();
    private int counter;

    public ClassicSempahore(int counter) {
        this.counter = counter;
    }

    public void V() {

        synchronized (object) {

            this.counter++;
            object.notify();

        }

    }

    public void P() throws InterruptedException {

        synchronized (object) {

            while (counter == 0) {
                object.wait();
            }
            this.counter--;

        }

    }
}
