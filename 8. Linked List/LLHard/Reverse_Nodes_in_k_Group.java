public class Reverse_Nodes_in_k_Group {

    /*
     * Approach: Iterative Segment Reversal
     * Pattern: Linked List In-place Manipulation
     * Time Complexity: O(2N) ~ O(N) - We traverse once to find Kth node, once to reverse.
     * Space Complexity: O(1) - In-place pointer manipulation.
     */
    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode temp = head;
        ListNode nextNode = null, prevNode = null;

        while (temp != null) {
            // Step 1: Identify the K-th node to define the current segment range
            ListNode kthNode = findKthNode(temp, k);

            // Edge Case: Incomplete group (fewer than K nodes remaining)
            if (kthNode == null) {
                // If there was a previous group, link its tail to the remaining unreversed segment
                if (prevNode != null) prevNode.next = temp;
                break;
            }

            // Step 2: Isolate the segment
            nextNode = kthNode.next; // Save the starting point of the NEXT group
            kthNode.next = null;     // Sever connection to treat this segment as a standalone list

            // Step 3: Reverse the isolated segment
            reverseList(temp);

            // Step 4: Reconnection Logic
            if (temp == head) {
                // Special Case: If this is the first group, the Kth node becomes the new global HEAD
                head = kthNode;
            } else {
                // General Case: Link the tail of the PREVIOUS group to the new head of CURRENT group
                prevNode.next = kthNode;
            }

            // Step 5: Update Pointers for next iteration
            // 'temp' was the head, now it is the tail of the reversed group.
            prevNode = temp;
            temp = nextNode; // Move to the start of the next group
        }
        return head;
    }

    // Helper: Standard Iterative Reversal
    private static void reverseList(ListNode head) {
        ListNode currentNode = head;
        ListNode prev = null;
        while (currentNode != null) {
            ListNode nextNode = currentNode.next;
            currentNode.next = prev;
            prev = currentNode;
            currentNode = nextNode;
        }
    }

    // Helper: Returns the Kth node from a given start point
    private static ListNode findKthNode(ListNode head, int k) {
        ListNode kthNode = head;
        while (kthNode != null) {
            k--;
            if (k == 0) break;
            kthNode = kthNode.next;
        }
        return kthNode;
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