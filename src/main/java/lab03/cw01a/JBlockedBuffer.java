package lab03.cw01a;

import java.util.concurrent.Semaphore;

/**
 * Created by Jacek Żyła on 26.03.14.
 */

public class JBlockedBuffer {

    private int[] buffer;
    private int limit;
    private volatile int lastElement = 0;
    private final Semaphore producerSem;
    private final Semaphore consumerSem;

    public JBlockedBuffer(int limit) {

        this.limit = limit;
        this.buffer = new int[limit];
        this.producerSem = new Semaphore(limit);
        this.consumerSem = new Semaphore(0);

    }

    public void put (int newElement) throws InterruptedException {

        producerSem.acquire();
        buffer[lastElement++] = newElement;
        consumerSem.release();

    }

    public int pop () throws InterruptedException {

        consumerSem.acquire();
        int element = buffer[--lastElement];
        producerSem.release();
        return element;
    }

}

