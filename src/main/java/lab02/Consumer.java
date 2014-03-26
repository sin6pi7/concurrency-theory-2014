package lab02;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Jacek Żyła on 20.03.14.
 */
public class Consumer implements Runnable {


    private static final Logger LOGGER = Logger.getLogger(Consumer.class.getName());
    private final BlockedBuffer blockedBuffer;

    public Consumer(BlockedBuffer blockedBuffer) {
        this.blockedBuffer = blockedBuffer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                LOGGER.log(Level.INFO, "Task started: popping from blockedBuffer.");
                int popValue = blockedBuffer.pop();
                LOGGER.log(Level.INFO, "Task ended: POP done with <<<<<<<<<<<" + popValue);
            }

        } catch (InterruptedException e) {
            LOGGER.log(Level.SEVERE, "InterruptedException: ", e);
        }
    }
}
