package lab03.cw01;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Jacek Żyła on 20.03.14.
 */
public class Producer implements Runnable {


    private static final Logger LOGGER = Logger.getLogger(Producer.class.getName());
    private final BlockedBuffer blockedBuffer;
    private static final Random random = new Random();

    public Producer(BlockedBuffer blockedBuffer) {
        this.blockedBuffer = blockedBuffer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int newElement = random.nextInt(30);
                LOGGER.log(Level.INFO, "Task started: putting into blockedBuffer " + newElement);
                blockedBuffer.put(newElement);
                LOGGER.log(Level.INFO, "Task ended: PUT done with >>>>>>>>>>>" + newElement);
            }

        } catch (InterruptedException e) {
            LOGGER.log(Level.SEVERE, "InterruptedException: ", e);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Exception!!!!!: ", e);
        }
    }
}
