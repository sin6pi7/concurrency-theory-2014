package lab03.cw01a;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Jacek Żyła on 20.03.14.
 */
public class Producer implements Runnable {


    private static final Logger LOGGER = Logger.getLogger(Producer.class.getName());
    private final ClassicBlockedBuffer classicBlockedBuffer;
    private static final Random random = new Random();

    public Producer(ClassicBlockedBuffer classicBlockedBuffer) {
        this.classicBlockedBuffer = classicBlockedBuffer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int newElement = random.nextInt(30);
                LOGGER.log(Level.INFO, "Task started: putting into classicBlockedBuffer " + newElement);
                classicBlockedBuffer.put(newElement);
                LOGGER.log(Level.INFO, "Task ended: PUT done with >>>>>>>>>>>" + newElement);
            }

        } catch (InterruptedException e) {
            LOGGER.log(Level.SEVERE, "InterruptedException: ", e);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Exception!!!!!: ", e);
        }
    }
}
