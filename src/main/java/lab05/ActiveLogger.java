package lab05;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Jacek Żyła on 16.04.14.
 */
public class ActiveLogger {

    private final Scheduler scheduler;
    private final Logger logger;
    private final LinkedBlockingQueue<MethodRequest> activationQueue;

    public ActiveLogger(StringBuilder stringBuilder) {
        this.logger = new Logger(stringBuilder);
        this.activationQueue = new LinkedBlockingQueue<MethodRequest>();
        this.scheduler = new Scheduler(logger, activationQueue);
        this.scheduler.start();
    }

    public void log(String text) throws InterruptedException {
        scheduler.enqueue(new LogMethodRequest(text, logger));
    }

    public Future read() throws InterruptedException {
        Future future = new Future<String>();
        scheduler.enqueue(new ReadMethodRequest(future, logger));
        return future;
    }

    public void finish() throws InterruptedException {
        this.scheduler.finish();
    }
}
