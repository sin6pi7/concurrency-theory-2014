package lab05;

/**
 * Created by Jacek Żyła on 16.04.14.
 */
public interface MethodRequest {
    public void call(Logger logger);
    public boolean guard(Logger logger);
}
