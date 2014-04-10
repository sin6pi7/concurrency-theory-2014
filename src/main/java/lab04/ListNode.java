package lab04;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Jacek Żyła on 10.04.14.
 */
public class ListNode {

    Object object;
    ListNode next;
    ReentrantLock lockObject = new ReentrantLock();

    public ListNode(Object object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "object=" + object +
                '}';
    }
}
