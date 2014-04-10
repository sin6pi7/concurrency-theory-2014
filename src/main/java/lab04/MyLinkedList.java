package lab04;

/**
 * Created by Jacek Żyła on 10.04.14.
 */
public class MyLinkedList {

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

        prev.lockObject.lock();

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

        prev.lockObject.unlock();

        return result;
    }

    public boolean add (Object o) {

        ListNode current = guard;

        current.lockObject.lock();

        while (current.next != null) {
            current.next.lockObject.lock();
            current.lockObject.unlock();
            current = current.next;
        }

        ListNode newNode = new ListNode(o);
        current.next = newNode;

        System.out.println("Added " + newNode);
        current.lockObject.unlock();

        return true;
    }

    public void print () {

        ListNode current = guard;

        current.lockObject.lock();

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

    }

}
