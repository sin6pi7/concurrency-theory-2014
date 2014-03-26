package lab03.cw01;

/**
 * Created by Jacek Żyła on 26.03.14.
 */

public class BlockedBuffer {

    private int[] buffer;
    private int limit;
    private volatile int lastElement = 0;
    private final BoundedSemaphore producerSem;
    private final BoundedSemaphore consumerSem;
    private final BoundedSemaphore binarySemaphore;

    public BlockedBuffer(int limit) {

        this.limit = limit;
        this.buffer = new int[limit];
        this.producerSem = new BoundedSemaphore(limit, limit);
        this.consumerSem = new BoundedSemaphore(limit, 0);
        binarySemaphore = new BoundedSemaphore(1, 1);

    }

    public void put (int newElement) throws InterruptedException {

        producerSem.acquire();
        binarySemaphore.acquire();
        buffer[lastElement++] = newElement;
        binarySemaphore.release();
        consumerSem.release();

    }

    public int pop () throws InterruptedException {

        consumerSem.acquire();
        binarySemaphore.acquire();
        int element = buffer[--lastElement];
        binarySemaphore.release();
        producerSem.release();
        return element;
    }

}

