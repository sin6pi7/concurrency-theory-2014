package lab04;

/**
 * Created by Jacek Żyła on 10.04.14.
 */
public class MyLinkedList implements IMyLinkedList{

    private ListNode guard;

    public MyLinkedList() {
        this.guard = new ListNode("Guard");
    }


    public boolean contains (Object o) {

        ListNode current = guard;
        boolean result = false;

        current.lockObject.lock();

        while ((current.next != null) && !result) {

            current.next.lockObject.lock();

            if (current.object.equals(o)) {
                result = true;
            }

            current.lockObject.unlock();
            current = current.next;

        }

        current.lockObject.unlock();

        return result;
    }

    public boolean remove (Object o) {

        ListNode prev = guard;

        System.out.println("Trying to remove " + o);
        prev.lockObject.lock();

        System.out.println("Removing " + o);

        ListNode current = prev.next;
        boolean result = false;

        while (current != null) {

            current.lockObject.lock();

            if (current.object.equals(o)) {
                result = true;
                break;
            }

            prev.lockObject.unlock();
            prev = current;
            current = current.next;
        }


        if(result == true){
            prev.next = current.next;
            System.out.println("Removed " + current.object);
            current.lockObject.unlock();
        }
        else {
            System.out.println("Failed to remove " + o);
        }

        prev.lockObject.unlock();

        return result;
    }

    public boolean add (Object o) {

        ListNode current = guard;

        System.out.println("Trying to add " + o);
        current.lockObject.lock();

        System.out.println("Adding " + o);

        while (current.next != null) {
            current.next.lockObject.lock();
            current.lockObject.unlock();
            current = current.next;
        }

        ListNode newNode = new ListNode(o);
        current.next = newNode;

        current.lockObject.unlock();
        System.out.println("Added " + newNode);

        return true;
    }

    public void print () {

        ListNode current = guard;

        System.out.println("Trying to print list");
        current.lockObject.lock();

        System.out.println("Printing list");
        while (current.next != null) {

            current.next.lockObject.lock();
            System.out.println(current.next);
            current.lockObject.unlock();
            current = current.next;

        }

        if (current == guard) {
            System.out.println(current);
        }

        current.lockObject.unlock();
        System.out.println("Finished printing list");
    }

}
