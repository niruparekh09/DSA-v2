import java.util.HashMap;

public class Find_Starting_Point_Of_Loop_In_LL {

    // TC:O(2*N)  SC: O(N)
    public static ListNode detectCycle1(ListNode head) {
        HashMap<ListNode, Integer> store = new HashMap<>();
        ListNode temp = head;
        while (temp != null) {
            if (store.containsKey(temp)) return temp;
            store.put(temp, 1);
            temp = temp.next;
        }
        return null;
    }

    // TC: O(N)  SC: O(1)
    public static ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow.equals(fast)) {
                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
