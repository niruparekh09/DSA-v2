import java.util.HashMap;

public class Copy_List_with_Random_Pointer {

    // TC: O(2N) SC: O(N) + O(N)
    public static Node copyRandomListBrute(Node head) {
        Node temp = head;
        HashMap<Node, Node> map = new HashMap<>();
        while (temp != null) {
            Node newNode = new Node(temp.val);
            map.put(temp, newNode);
            temp = temp.next;
        }
        temp = head;
        while (temp != null) {
            Node node = map.get(temp);
            node.next = map.get(temp.next);
            node.random = map.get(temp.random);
            temp = temp.next;
        }
        return map.get(head);
    }

    // TC: O(3N) SC: O(N)
    public static Node copyRandomList(Node head) {
        if (head == null)
            return null;

        insertCopyInBetween(head);
        connectRandomPointers(head);

        return getDeepCopyList(head);
    }

    public static void insertCopyInBetween(Node head) {
        Node temp = head;
        while (temp != null) {
            Node nextElement = temp.next; // Store next el of temp
            Node copyNode = new Node(temp.val);
            copyNode.next = nextElement;
            temp.next = copyNode;
            temp = nextElement;
        }
    }


    public static void connectRandomPointers(Node head) {
        Node temp = head;
        while (temp != null) {
            Node copyNode = temp.next;
            if (temp.random != null)
                copyNode.random = temp.random.next; // copy Random will be next to real random element.
            else
                copyNode.random = null;
            temp = temp.next.next; // The next original element will be next to copy element so next.next
        }
    }

    public static Node getDeepCopyList(Node head) {
        Node temp = head;
        Node dummyNode = new Node(-1);
        Node res = dummyNode;
        while (temp != null) {
            res.next = temp.next;
            res = res.next;
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
