package lab03.cw01a;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Jacek Żyła on 20.03.14.
 */
public class Consumer implements Runnable {


    private static final Logger LOGGER = Logger.getLogger(Consumer.class.getName());
    private final ClassicBlockedBuffer classicBlockedBuffer;

    public Consumer(ClassicBlockedBuffer classicBlockedBuffer) {
        this.classicBlockedBuffer = classicBlockedBuffer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                LOGGER.log(Level.INFO, "Task started: popping from classicBlockedBuffer.");
                int popValue = classicBlockedBuffer.pop();
                LOGGER.log(Level.INFO, "Task ended: POP done with <<<<<<<<<<<" + popValue);
            }

        } catch (InterruptedException e) {
            LOGGER.log(Level.SEVERE, "InterruptedException: ", e);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Exception: !!!!", e);
        }
    }
}
