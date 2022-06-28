package problems;

import datastructures.LinkedIntList;
// Checkstyle will complain that this is an unused import until you use it in your code.
import datastructures.LinkedIntList.ListNode;

/**
 * See the spec on the website for example behavior.
 *
 * REMEMBER THE FOLLOWING RESTRICTIONS:
 * - do not call any methods on the `LinkedIntList` objects.
 * - do not construct new `ListNode` objects for `reverse3` or `firstToLast`
 *      (though you may have as many `ListNode` variables as you like).
 * - do not construct any external data structures such as arrays, queues, lists, etc.
 * - do not mutate the `data` field of any node; instead, change the list only by modifying
 *      links between nodes.
 */

public class LinkedIntListProblems {

    /**
     * Reverses the 3 elements in the `LinkedIntList` (assume there are exactly 3 elements).
     */
    public static void reverse3(LinkedIntList list) {
        ListNode prev = null;
        ListNode curr = list.front;
        ListNode following = list.front;

        while (curr != null) {
            following = following.next;
            curr.next = prev;
            prev = curr;
            curr = following;
        }
        list.front = prev;
        System.out.println(list.front);
    }

    /**
     * Moves the first element of the input list to the back of the list.
     */
    public static void firstToLast(LinkedIntList list) {
        if (list.front != null && list.front.next != null) {
            ListNode temp = list.front.next;
            ListNode head = list.front.next;
            list.front.next = null;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = list.front;
            list.front = head;
        }
    }

    /**
     * Returns a list consisting of the integers of a followed by the integers
     * of n. Does not modify items of A or B.
     */
    public static LinkedIntList concatenate(LinkedIntList a, LinkedIntList b) {
        // Hint: you'll need to use the 'new' keyword to construct new objects.
        LinkedIntList c = new LinkedIntList();
        if (a.front != null || b.front != null) {
            if (a.front == null) {
                return b;
            } else if (b.front == null) {
                return a;
            }

            ListNode temp = a.front;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = b.front;

            ListNode temp2 = a.front;
            ListNode node = new ListNode(temp2.data);
            temp2 = temp2.next;
            ListNode curr = node;
            while (temp2 != null) {
                curr.next = new ListNode(temp2.data);
                curr = curr.next;
                temp2 = temp2.next;
            }

            b.front = temp.next;
            temp.next = null;
            c = new LinkedIntList(node);

        }
        return c;
    }
}
