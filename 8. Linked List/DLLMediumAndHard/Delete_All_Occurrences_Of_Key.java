public class Delete_All_Occurrences_Of_Key {
    public static Node deleteAllOccurOfX(Node head, int x) {
        Node temp = head;
        while (temp != null) {
            if (temp.data == x) {
                if (temp == head) {
                    head = head.next;
                }
                Node nextNode = temp.next;
                Node prevNode = temp.prev;
                if (nextNode != null) nextNode.prev = prevNode; // To handle tail node
                if (prevNode != null) prevNode.next = nextNode; // To handle head node

                temp = nextNode; // Move to new node
            } else temp = temp.next;
        }
        return head;
    }

    public static class Node {
        int data;
        Node next;
        Node prev;

        Node(int data) {
            this.data = data;
            next = prev = null;
        }
    }
}
