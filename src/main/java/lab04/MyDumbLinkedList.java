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

        // system.out.println("Trying to check " + o);
        lockObject.lock();

        // system.out.println("Checking " + o);

        while ((current.next != null) && !result) {

            if (current.object.equals(o)) {
                result = true;
            }

            current = current.next;

        }

        if (result) {
            // system.out.println("List contains " + o);
        }
        else {
            // system.out.println("List does not conatin " + o);
        }

        lockObject.unlock();

        return result;
    }

    public boolean remove (Object o) {

        ListNode prev = guard;

        // system.out.println("Trying to remove " + o);
        lockObject.lock();

        // system.out.println("Removing " + o);
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
                // system.out.println("Removed " + o);
            }
            else {
                // system.out.println("Failed to remove " + o);
            }
        } finally {
            lockObject.unlock();
        }

        // system.out.println("Removed "+ o);
        return result;
    }

    public boolean add (Object o) {

        ListNode current = guard;

        // system.out.println("Trying to add " + o);
        lockObject.lock();

        // system.out.println("Adding " + o);
        try {
            while (current.next != null) {
                current = current.next;
            }

            current.next = new ListNode(o);
        } finally {
            lockObject.unlock();
        }

        // system.out.println("Added " + o);

        return true;
    }

    public void print () {

        ListNode current = guard;

        // system.out.println("Trying to print list");
        lockObject.lock();

        // system.out.println("Printing list");

        try {
            while (current.next != null) {

                // system.out.println(current.next);
                current = current.next;

            }

            if (current == guard) {
                // system.out.println(current);
            }
        }
        finally {
            lockObject.unlock();
        }

        // system.out.println("Finished printing list");

    }

}
