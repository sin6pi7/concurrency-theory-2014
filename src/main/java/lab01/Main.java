package lab01;

import java.io.*;

/**
 * Created by Jacek Żyła on 18.03.14.
 */
public class Main {
    private static long startTime;
    private static long endTime;
    private static int threadNumber;
    private static Object s = new Object();

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

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(
                        new BufferedWriter(
                            new FileWriter("output.txt", true)
                        )
                    );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        // JVM version
        writer.println("JVM version: " + System.getProperty("java.version"));

        // Number of iterations
        writer.println("Liczba iteracji: " + Parameters.NUMBER_OF_ITERATIONS + "\n");
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
        writer.println("COUNTER\nCzas pracy: " + (endTime - startTime) + " ns, wynik: " + counter.toString() + "\n");

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
        writer.println("ATOMIC COUNTER\nCzas pracy: " + (endTime - startTime) + " ns, wynik: " + atomicCounter.toString() + "\n");

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
        writer.println("SYNCHRONIZED COUNTER:\nCzas pracy: " + (endTime - startTime) + " ns, wynik: " + synchronizedCounter.toString() + "\n");

        writer.close();
//        threadNumber = 1;
//
//        // Check max number of threads
//        while (true) {
//            new Thread () {
//                @Override
//                public void run() {
//                    synchronized (s) {
//                        System.out.println("Thread " + threadNumber++);
//                    }
//                    while(true) {
//                        try {
//                            sleep(Long.MAX_VALUE);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                }
//            }.start();
//
//        }

    }
}
