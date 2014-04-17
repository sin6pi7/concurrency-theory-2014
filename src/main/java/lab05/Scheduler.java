package lab05;

import java.lang.reflect.Method;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;

/**
 * Created by Jacek Żyła on 16.04.14.
 */
public class Scheduler extends Thread {

    private final Logger logger;
    private final LinkedBlockingQueue<MethodRequest> activationQueue;
    private final Lock lock;
    private volatile Thread blinker;

    public Scheduler(Logger logger, Lock lock, LinkedBlockingQueue<MethodRequest> activationQueue) {
        this.logger = logger;
        this.lock = lock;
        this.activationQueue = activationQueue;
    }

    public void enqueue(MethodRequest methodRequest) throws InterruptedException {
        this.activationQueue.put(methodRequest);
    }

    private void dispatch() throws InterruptedException {

        while (this.blinker == Thread.currentThread()) {
            if (!activationQueue.isEmpty()) {
                MethodRequest methodRequest = activationQueue.poll();
                if (methodRequest.guard(logger)) {
                    methodRequest.call(logger);
                }
                else {
                    activationQueue.put(methodRequest);
                }
            }
        }
    }

    public void finish() {
        this.blinker = null;
    }

    @Override
    public void run() {
        this.blinker = Thread.currentThread();
        try {
            this.dispatch();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
