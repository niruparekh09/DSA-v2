import java.util.HashMap;

public class Find_length_of_Loop {

    /*
     * Approach: Hashing (Timer Map)
     * Pattern: Hashing
     * Time Complexity: O(N) - We traverse the list once.
     * Space Complexity: O(N) - Map stores every visited node and its timestamp.
     */
    public static int countNodesInLoop1(Node head) {
        HashMap<Node, Integer> store = new HashMap<>();
        int timer = 0;
        Node temp = head;

        while (temp != null) {
            timer++;
            // Key Logic: If node is already in map, we found the cycle start.
            // Loop Length = Current Time - Time when we first saw this node.
            if (store.containsKey(temp)) return timer - store.get(temp);

            store.put(temp, timer);
            temp = temp.next;
        }
        return 0; // No loop found
    }

    /*
     * Approach: Floyd's Cycle Detection (Optimal)
     * Pattern: Fast & Slow Pointers
     * Time Complexity: O(N) - Fast pointer moves 2x speed, meeting occurs within linear time.
     * Space Complexity: O(1) - In-place pointer manipulation.
     */
    public static int countNodesInLoop(Node head) {
        Node slow = head;
        Node fast = head;

        // Step 1: Detect if a loop exists
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            // Collision Point: Cycle detected.
            if (slow.equals(fast)) {
                // Step 2: Calculate length of the loop starting from the collision point.
                return findLength(slow, fast);
            }
        }
        return 0; // No loop
    }

    // Helper: Counts nodes in the cycle
    private static int findLength(Node slow, Node fast) {
        int cnt = 1;
        slow = slow.next;

        // Logic: Keep one pointer fixed (fast) and move the other (slow)
        // around the loop until it returns to the start point.
        while (slow != fast) {
            slow = slow.next;
            cnt++;
        }
        return cnt;
    }

    public static class Node {
        int data;
        Node next;

        Node(int d) {
            data = d;
            next = null;
        }
    }
}