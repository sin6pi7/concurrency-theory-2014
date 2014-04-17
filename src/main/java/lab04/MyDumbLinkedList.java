package lab04;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Jacek Żyła on 10.04.14.
 */
public class MyDumbLinkedList implements IMyLinkedList {

    private ListNode guard;
    private ReentrantLock lockObject;

    public MyDumbLinkedList() {
        this.guard = new ListNode("Guard");
        this.lockObject = new ReentrantLock();
    }


    public boolean contains (Object o) {

        ListNode current = guard;
        boolean result = false;

        lockObject.lock();

        while ((current.next != null) && !result) {

            if (current.object.equals(o)) {
                result = true;
            }

            current = current.next;

        }

        lockObject.unlock();

        return result;
    }

    public boolean remove (Object o) {

        ListNode prev = guard;

        System.out.println("Trying to remove " + o);
        lockObject.lock();

        System.out.println("Removing " + o);
        boolean result;

        try {
            ListNode current = prev.next;
            result = false;

            while (current != null) {

                if (current.object.equals(o)) {
                    result = true;
                    break;
                }

                prev = current;
                current = current.next;
            }


            if(result == true){
                prev.next = current.next;
                System.out.println("Removed " + o);
            }
            else {
                System.out.println("Failed to remove " + o);
            }
        } finally {
            lockObject.unlock();
        }

        System.out.println("Removed "+ o);
        return result;
    }

    public boolean add (Object o) {

        ListNode current = guard;

        System.out.println("Trying to add " + o);
        lockObject.lock();

        System.out.println("Adding " + o);
        try {
            while (current.next != null) {
                current = current.next;
            }

            current.next = new ListNode(o);
        } finally {
            lockObject.unlock();
        }

        System.out.println("Added " + o);

        return true;
    }

    public void print () {

        ListNode current = guard;

        System.out.println("Trying to print list");
        lockObject.lock();

        System.out.println("Printing list");

        try {
            while (current.next != null) {

                System.out.println(current.next);
                current = current.next;

            }

            if (current == guard) {
                System.out.println(current);
            }
        }
        finally {
            lockObject.unlock();
        }

        System.out.println("Finished printing list");

    }

}
