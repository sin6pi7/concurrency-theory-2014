package lab_01;

/**
 * Created by Jacek Żyła on 18.03.14.
 */
public class Main {
    private static long startTime;
    private static long endTime;

    public static void main(String[] args) {

        Counter counter = new Counter(0);
        AtomicCounter atomicCounter = new AtomicCounter(0);
        SynchronizedCounter synchronizedCounter = new SynchronizedCounter(0);

        Thread incCounter = new Thread(new Incrementer(counter));
        Thread decCounter = new Thread(new Decrementer(counter));

        Thread incAtomicCounter = new Thread(new Incrementer(atomicCounter));
        Thread decAtomicCounter = new Thread(new Decrementer(atomicCounter));

        Thread incSynchronizedCounter = new Thread(new Incrementer(synchronizedCounter));
        Thread decSynchronizedCounter = new Thread(new Decrementer(synchronizedCounter));

        /*
            Regular counter without synchronization
         */

        startTime = System.nanoTime();

        incCounter.start();
        decCounter.start();

        try {
            incCounter.join();
            decCounter.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        endTime = System.nanoTime();
        System.out.println("Czas pracy: " + (endTime - startTime) + ", wynik: " + counter.toString());

        /*
            Counter with atomic fields
         */
        startTime = System.nanoTime();

        incAtomicCounter.start();
        decAtomicCounter.start();

        try {
            incAtomicCounter.join();
            decAtomicCounter.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        endTime = System.nanoTime();
        System.out.println("Czas pracy: " + (endTime - startTime) + ", wynik: " + atomicCounter.toString());

        /*
            Counter with synchronized methods
         */
        startTime = System.nanoTime();

        incSynchronizedCounter.start();
        decSynchronizedCounter.start();

        try {
            incSynchronizedCounter.join();
            decSynchronizedCounter.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        endTime = System.nanoTime();
        System.out.println("Czas pracy: " + (endTime - startTime) + ", wynik: " + synchronizedCounter.toString());

    }
}
