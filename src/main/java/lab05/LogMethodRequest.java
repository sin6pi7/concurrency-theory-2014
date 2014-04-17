package lab05;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Jacek Żyła on 16.04.14.
 */
public class LogMethodRequest implements MethodRequest {

    private final String textToLog;
    private final ReentrantLock lock;
    private final Logger logger;

    public LogMethodRequest(String text, Logger logger, ReentrantLock lock) {
        this.textToLog = text;
        this.logger = logger;
        this.lock = lock;
    }

    @Override
    public void call(Logger logger) {
        lock.lock();
        try {
            logger.log(textToLog);
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
