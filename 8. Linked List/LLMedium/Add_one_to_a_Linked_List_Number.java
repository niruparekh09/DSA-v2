public class Add_one_to_a_Linked_List_Number {

    public static Node addOneItr(Node head) {
        head = reverse(head);
        Node temp = head;
        int carry = 1;
        while (temp != null) {
            temp.data = temp.data + carry;
            if (temp.data < 10) {
                carry = 0;
                break;
            } else {
                temp.data = 0;
                carry = 1;
            }
            temp = temp.next;
        }
        if (carry == 1) {
            Node newNode = new Node(1);
            head = reverse(head);
            newNode.next = head;
            return newNode;
        }
        head = reverse(head);
        return head;
    }

    public static Node reverse(Node head) {
        if (head == null || head.next == null) return head;
        Node current = head;
        Node prev = null;
        while (current != null) {
            Node front = current.next;
            current.next = prev;
            prev = current;
            current = front;
        }
        return prev;
    }

    // Recursive Solution
    public static Node addOne(Node head) {
        int carry = helper(head);
        if (carry == 1) {
            Node newNode = new Node(1);
            newNode.next = head;
            return newNode;
        }
        return head;
    }

    private static int helper(Node temp) {
        if (temp == null) return 1;
        int carry = helper(temp.next);
        temp.data = temp.data + carry;
        if (temp.data < 10) return 0;
        temp.data = 0;
        return 1;
    }

    public static class Node {
        int data;
        Node next;

        Node(int x) {
            data = x;
            next = null;
        }
    }
}
