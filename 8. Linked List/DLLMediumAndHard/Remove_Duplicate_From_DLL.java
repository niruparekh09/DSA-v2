public class Remove_Duplicate_From_DLL {

    /*
     * Approach: Iterative Skip (Two Pointers)
     * Note: Assumes the Linked List is SORTED.
     * Pattern: Linked List In-place Manipulation
     * Time Complexity: O(N) - Each node is visited at most twice (by temp and nextNode).
     * Space Complexity: O(1) - In-place modification.
     */
    public static Node removeDuplicates(Node head) {
        Node temp = head;

        while (temp != null && temp.next != null) {
            Node nextNode = temp.next;

            // Key Logic: Fast-forward 'nextNode' to skip ALL consecutive duplicates.
            while (nextNode != null && nextNode.data == temp.data) {
                nextNode = nextNode.next;
            }

            // Rewiring: Link current node 'temp' directly to the next UNIQUE node.
            // This effectively garbage collects the intermediate duplicate nodes.
            temp.next = nextNode;

            // Edge Case: Check if we reached null (tail) before accessing .prev
            if (nextNode != null) nextNode.prev = temp;

            // Move main pointer to the next unique node
            temp = temp.next;
        }
        return head;
    }

    public static class Node {
        int data;
        Node next, prev;

        Node(int x) {
            data = x;
            next = null;
            prev = null;
        }
    }
}