import java.util.HashMap;

public class Linked_List_Cycle {

    /*
     * Approach: Hashing (Brute Force)
     * Pattern: Hashing
     * Time Complexity: O(N) - We visit every node once.
     * Space Complexity: O(N) - Map stores references to all visited nodes.
     */
    public boolean hasCycle1(ListNode head) {
        HashMap<ListNode, Integer> store = new HashMap<>();
        ListNode temp = head;

        while (temp != null) {
            // Logic: If we encounter a node that is already in the map,
            // it means we have looped back to a previously visited node.
            if (store.containsKey(temp)) return true;

            store.put(temp, 1);
            temp = temp.next;
        }
        return false;
    }

    /*
     * Approach: Floyd's Cycle Finding Algorithm (Optimal)
     * Pattern: Fast & Slow Pointers
     * Time Complexity: O(N) - If cycle exists, pointers meet in linear time relative to list length.
     * Space Complexity: O(1) - Uses only two pointers.
     */
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        // Logic: Fast moves 2x speed. Slow moves 1x speed.
        // Condition: Check fast.next to avoid NullPointerException since fast moves 2 steps.
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            // Collision Logic: If there is a cycle, the fast pointer will eventually
            // "lap" the slow pointer from behind.
            if (slow.equals(fast)) return true;
        }

        // If fast reaches the end (null), the list is linear (no cycle).
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