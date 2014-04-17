package lab05;

/**
 * Created by Jacek Żyła on 17.04.14.
 */
public class ReadRunner implements Runnable {
    private final ActiveLogger activeLogger;

    public ReadRunner(ActiveLogger activeLogger) {
        this.activeLogger = activeLogger;
    }

    @Override
    public void run() {

        while (true) {
            try {
                System.out.println(this.activeLogger.read().getResource());
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
