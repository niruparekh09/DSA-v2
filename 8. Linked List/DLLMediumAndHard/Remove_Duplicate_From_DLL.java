public class Remove_Duplicate_From_DLL {

    public static Node removeDuplicates(Node head) {
        Node temp = head;
        while (temp != null && temp.next != null) {
            Node nextNode = temp.next;
            while (nextNode != null && nextNode.data == temp.data) {
                nextNode = nextNode.next;
            }
            temp.next = nextNode;
            if (nextNode != null) nextNode.prev = temp;
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
