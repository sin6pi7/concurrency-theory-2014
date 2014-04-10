package lab03.cw01a;

/**
 * Created by Jacek Żyła on 26.03.14.
 */

public class ClassicBlockedBuffer {

    private int[] buffer;
    private int limit;
    private volatile int lastElement = 0;
    private final ClassicSempahore producerSem;
    private final ClassicSempahore consumerSem;
    private final BinarySemaphore binarySemaphore;

    public ClassicBlockedBuffer(int limit, BinarySemaphore binarySemaphore) {

        this.limit = limit;
        this.binarySemaphore = binarySemaphore;
        this.buffer = new int[limit];
        this.producerSem = new ClassicSempahore(limit);
        this.consumerSem = new ClassicSempahore(0);

    }

    public void put (int newElement) throws InterruptedException {

        producerSem.P();
        binarySemaphore.P();
        buffer[lastElement++] = newElement;
        binarySemaphore.V();
        consumerSem.V();

    }

    public int pop () throws InterruptedException {

        consumerSem.P();
        binarySemaphore.P();
        int element = buffer[--lastElement];
        binarySemaphore.V();
        producerSem.V();
        return element;
    }

}

