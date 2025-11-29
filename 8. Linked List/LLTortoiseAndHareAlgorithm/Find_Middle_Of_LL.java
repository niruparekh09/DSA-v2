public class Find_Middle_Of_LL {

    /*
     * Approach: Tortoise and Hare Algorithm
     * Pattern: Fast & Slow Pointers
     * Time Complexity: O(N) - Traverses N/2 nodes.
     * Space Complexity: O(1) - Uses constant extra space.
     */
    public static ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        // Logic: Fast pointer moves 2 steps, Slow pointer moves 1 step.
        // Condition 1 (fast != null): Handles Even length (Fast eventually jumps to null).
        // Condition 2 (fast.next != null): Handles Odd length (Fast eventually stops at last node).
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // When Fast reaches the end, Slow is exactly at the middle node.
        // Note: For even lengths [1,2,3,4], slow returns 3 (the second middle).
        return slow;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}