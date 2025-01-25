import java.util.HashMap;

public class Linked_List_Cycle {

    // TC: O(N*2*1) SC: O(N)
    public boolean hasCycle1(ListNode head) {
        HashMap<ListNode, Integer> store = new HashMap<>();
        ListNode temp = head;
        while (temp != null) {
            if (store.containsKey(temp)) return true;
            store.put(temp, 1);
            temp = temp.next;
        }
        return false;
    }

    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow.equals(fast)) return true;
        }
        return false;
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
