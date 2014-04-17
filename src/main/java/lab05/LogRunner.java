package lab05;

/**
 * Created by Jacek Żyła on 17.04.14.
 */
public class LogRunner implements Runnable {
    private final ActiveLogger activeLogger;
    private int i = 0;

    public LogRunner(ActiveLogger activeLogger) {
        this.activeLogger = activeLogger;
    }

    @Override
    public void run() {

        while (true) {
            try {
                this.activeLogger.log(String.valueOf(i++));
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
