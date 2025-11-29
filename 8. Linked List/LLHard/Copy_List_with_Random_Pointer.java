import java.util.HashMap;

public class Copy_List_with_Random_Pointer {

    /*
     * Approach: HashMap Mapping
     * Pattern: Hashing
     * Time Complexity: O(2N) - Two passes (Creation + Connection).
     * Space Complexity: O(N) - Map stores mapping from Original Node -> Copy Node.
     */
    public static Node copyRandomListBrute(Node head) {
        Node temp = head;
        HashMap<Node, Node> map = new HashMap<>();

        // Step 1: Create deep copies of all nodes and store mapping
        while (temp != null) {
            Node newNode = new Node(temp.val);
            map.put(temp, newNode);
            temp = temp.next;
        }

        temp = head;
        // Step 2: Wire the 'next' and 'random' connections using the map
        while (temp != null) {
            Node node = map.get(temp);
            // Key Logic: map.get(temp.next) retrieves the COPY of the next node
            node.next = map.get(temp.next);
            node.random = map.get(temp.random);
            temp = temp.next;
        }
        return map.get(head);
    }

    /*
     * Approach: Interweaving Nodes (Optimal)
     * Pattern: Linked List Manipulation (3-Step Process)
     * Time Complexity: O(3N) - Three linear passes (Insert, Connect, Separate).
     * Space Complexity: O(1) - No external data structure used.
     */
    public static Node copyRandomList(Node head) {
        if (head == null) return null;

        insertCopyInBetween(head);    // Step 1: A -> A' -> B -> B'
        connectRandomPointers(head);  // Step 2: Set randoms for A', B'
        return getDeepCopyList(head); // Step 3: Extract A' -> B' and restore A -> B
    }

    // Step 1: Insert copy nodes directly after original nodes
    public static void insertCopyInBetween(Node head) {
        Node temp = head;
        while (temp != null) {
            Node nextElement = temp.next;

            // Create copy and insert: temp -> copy -> nextElement
            Node copyNode = new Node(temp.val);
            copyNode.next = nextElement;
            temp.next = copyNode;

            temp = nextElement;
        }
    }

    // Step 2: Set random pointers for the copies
    public static void connectRandomPointers(Node head) {
        Node temp = head;
        while (temp != null) {
            Node copyNode = temp.next;

            // Key Logic: temp.random is the original target.
            // temp.random.next is the COPY of that target (created in Step 1).
            if (temp.random != null)
                copyNode.random = temp.random.next;
            else
                copyNode.random = null;

            // Move to next original node (skip the copy)
            temp = temp.next.next;
        }
    }

    // Step 3: Unweave the lists (Restore original and extract copy)
    public static Node getDeepCopyList(Node head) {
        Node temp = head;
        Node dummyNode = new Node(-1);
        Node res = dummyNode;

        while (temp != null) {
            // Extract copy node
            res.next = temp.next;
            res = res.next;

            // Restore original list link (skip copy)
            // Critical: Must restore original list to satisfy interview constraints.
            temp.next = temp.next.next;

            temp = temp.next;
        }
        return dummyNode.next;
    }

    public static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}