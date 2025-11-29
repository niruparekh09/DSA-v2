import java.util.HashMap;

public class Find_Starting_Point_Of_Loop_In_LL {

    /*
     * Approach: Hashing (Brute Force)
     * Pattern: Hashing
     * Time Complexity: O(N) - Iterate list once.
     * Space Complexity: O(N) - Map stores all visited nodes.
     */
    public static ListNode detectCycle1(ListNode head) {
        HashMap<ListNode, Integer> store = new HashMap<>();
        ListNode temp = head;

        while (temp != null) {
            // Logic: The first node we encounter that is ALREADY in the map is the start of the loop.
            if (store.containsKey(temp)) return temp;

            store.put(temp, 1);
            temp = temp.next;
        }
        return null;
    }

    /*
     * Approach: Floyd's Cycle Finding Algorithm (Optimal)
     * Pattern: Fast & Slow Pointers / Math
     * Time Complexity: O(N) - Cycle detection + finding entry point.
     * Space Complexity: O(1) - In-place pointer manipulation.
     */
    public static ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        // Step 1: Detect if a cycle exists using Tortoise & Hare
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            // Collision detected inside the loop
            if (slow.equals(fast)) {
                // Step 2: Find the entry point.
                // Mathematical Proof: Distance from Head to Entry == Distance from Collision to Entry.
                // Reset slow to head, keep fast at collision point.
                slow = head;

                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next; // Critical: Fast now moves 1 step at a time
                }
                return slow; // The point where they meet is the loop start
            }
        }
        return null; // No cycle found
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