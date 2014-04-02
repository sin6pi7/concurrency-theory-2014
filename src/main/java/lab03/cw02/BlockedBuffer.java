package lab03.cw02;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Jacek Żyła on 26.03.14.
 */

public class BlockedBuffer {

    private int[] buffer;
    private int limit;
    private volatile int lastElement = 0;
    private ReentrantLock lock;
    private Condition notFullCondition;
    private Condition putFirstCondition;
    private Condition notEmptyCondition;
    private Condition popFirstCondition;


    public BlockedBuffer(int limit) {

        this.limit = limit;
        this.buffer = new int[limit];
        this.lock = new ReentrantLock();
        this.notFullCondition = lock.newCondition();
        this.notEmptyCondition = lock.newCondition();
        this.putFirstCondition = lock.newCondition();
        this.popFirstCondition = lock.newCondition();
    }

    public void put (int[] elements) throws InterruptedException {

        lock.lock();
        try {

            while (lock.hasWaiters(putFirstCondition)) {
                notFullCondition.await();
            }

            while (lastElement + elements.length >= limit) {
                putFirstCondition.await();
            }


            for (int i = 0; i < elements.length; i++) {
                buffer[lastElement++] = elements[i];
            }

            notFullCondition.signal();
            popFirstCondition.signal();


        }
        finally{
            lock.unlock();
        }

    }

    public int[] pop (int numberOfElements) throws InterruptedException {

        int[] elements;

        lock.lock();
        try {

            while (lock.hasWaiters(popFirstCondition)) {
                notEmptyCondition.await();
            }

            while (lastElement - numberOfElements < 0) {
                popFirstCondition.await();
            }

            elements = new int[numberOfElements];
            for (int i = 0; i < numberOfElements; i++) {
                elements[i] = buffer[--lastElement];
            }

            putFirstCondition.signal();
            notEmptyCondition.signal();


        }
        finally {
            lock.unlock();
        }

        return elements;


    }

}

