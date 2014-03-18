package lab_01;

/**
 * Created by Jacek Żyła on 18.03.14.
 */
public class Incrementer implements Runnable {

    private ICounter iCounter;

    public Incrementer(ICounter iCounter) {
        this.iCounter = iCounter;
    }

    @Override
    public void run() {
        for (int i = 0; i < Parameters.NUMBER_OF_ITERATIONS; ++i) iCounter.inc();
    }
}
