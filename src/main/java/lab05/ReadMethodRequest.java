package lab05;

/**
 * Created by Jacek Żyła on 16.04.14.
 */
public class ReadMethodRequest implements MethodRequest {

    private final Logger logger;
    private final Future future;

    public ReadMethodRequest(Future future, Logger logger) {
        this.future = future;
        this.logger = logger;
    }

    @Override
    public void call(Logger logger) {
        future.setResource(logger.read());
    }

    @Override
    public boolean guard(Logger logger) {
        return true;
    }
}
