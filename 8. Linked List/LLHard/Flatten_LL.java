public class Flatten_LL {

    /*
     * Approach: Recursion with Merge Sort Logic
     * Pattern: Divide and Conquer / Linked List Manipulation
     * Time Complexity: O(N) - N is the total number of nodes. Each node is processed in the merge steps.
     * Space Complexity: O(K) - K is the length of the horizontal list (Recursion Stack depth).
     */
    public static Node flatten(Node root) {
        // Base Case: If we reached the last vertical list or the list is empty.
        if (root == null || root.next == null) return root;

        // Recursion: Move to the very end of the horizontal list first.
        // "Leap of Faith": Trust that this returns the fully flattened list from the right.
        Node mergedHead = flatten(root.next);

        // Logic: Merge the current vertical list (root) with the flattened list from the right.
        return merge(root, mergedHead);
    }

    // Helper: Merges two sorted linked lists using the 'bottom' pointer
    public static Node merge(Node head1, Node head2) {
        Node dummyNode = new Node(-1);
        Node res = dummyNode;
        Node temp1 = head1;
        Node temp2 = head2;

        // Standard Merge Two Sorted Lists logic
        while (temp1 != null && temp2 != null) {
            if (temp1.data > temp2.data) {
                res.bottom = temp2;
                res = temp2;
                temp2 = temp2.bottom;
            } else {
                res.bottom = temp1;
                res = temp1;
                temp1 = temp1.bottom;
            }
            // Key Detail: Break the 'next' (horizontal) link since the result must be a single vertical line.
            res.next = null;
        }

        // Attach remaining nodes
        if (temp1 != null) res.bottom = temp1;
        else res.bottom = temp2;

        return dummyNode.bottom;
    }

    public static class Node {
        int data;
        Node next;
        Node bottom;

        public Node(int data) {
            this.data = data;
            this.next = null;
            this.bottom = null;
        }
    }
}