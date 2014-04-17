package lab04;

import java.util.Random;

/**
 * Created by Jacek Żyła on 10.04.14.
 */
public class TestListRunner implements Runnable {

    private static final Random random = new Random();
    private final IMyLinkedList myList;
    private final int iterations;
    private Object object;

    public TestListRunner(IMyLinkedList myList, int iterations, Object object) {
        this.myList = myList;
        this.iterations = iterations;
        this.object = object;
    }

    @Override
    public void run() {

        for (int i = 0; i < iterations; i++) {
            myList.add(object);
        }
//
//        for (int i = 0; i < iterations; ++i) {
//            switch (i % 2) {
//                case 0:  {
//                    System.out.println("RUNNER IS CHECKING CONTAINS");
//                    myList.contains(new int[100]);
//                    break;
//                }
//                case 1:  {
//                    System.out.println("RUNNER IS REMOVING");
//                    myList.remove(new int[100]);
//                    break;
//                }
//            }
//        }

    }
}
