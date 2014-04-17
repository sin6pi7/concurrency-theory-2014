package lab05;

/**
 * Created by Jacek Żyła on 16.04.14.
 */
public class Logger implements ILogger {

    private final StringBuilder stringBuilder;

    public Logger(StringBuilder stringBuilder) {
        this.stringBuilder = stringBuilder;
    }

    @Override
    public void log(String text) {
        this.stringBuilder.append(text);
    }

    @Override
    public String read() {
        return this.stringBuilder.toString();
    }
}
