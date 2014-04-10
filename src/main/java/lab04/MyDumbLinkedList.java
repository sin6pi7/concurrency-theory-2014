package lab04;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Jacek Żyła on 10.04.14.
 */
public class MyDumbLinkedList {

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

        lockObject.lock();

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
            }
        } finally {
            lockObject.unlock();
        }

        return result;
    }

    public boolean add (Object o) {

        ListNode current = guard;

        lockObject.lock();

        try {
            while (current.next != null) {
                current = current.next;
            }

            current.next = new ListNode(o);
        } finally {
            lockObject.unlock();
        }

        return true;
    }

    public void print () {

        ListNode current = guard;

        lockObject.lock();

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

    }

}
