package lab05;

/**
 * Created by Jacek Żyła on 16.04.14.
 */
public class LogMethodRequest implements MethodRequest {

    private final String textToLog;
    private final Logger logger;

    public LogMethodRequest(String text, Logger logger) {
        this.textToLog = text;
        this.logger = logger;
    }

    @Override
    public void call(Logger logger) {
        logger.log(textToLog);
    }

    @Override
    public boolean guard(Logger logger) {
        return true;
    }
}
