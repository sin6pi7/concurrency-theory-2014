package lab05;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Jacek Żyła on 16.04.14.
 */
public class ReadMethodRequest implements MethodRequest {

    private final ReentrantLock lock;
    private final Logger logger;
    private final Future future;

    public ReadMethodRequest(Future future, Logger logger, ReentrantLock lock) {
        this.future = future;
        this.logger = logger;
        this.lock = lock;
    }

    @Override
    public void call(Logger logger) {
        lock.lock();
        try {
            String text = logger.read();
            future.setResource(text);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean guard(Logger logger) {
        if (!lock.isLocked()) {
            return true;
        }
        return false;
    }
}
