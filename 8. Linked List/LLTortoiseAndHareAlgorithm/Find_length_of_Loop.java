import java.util.HashMap;

public class Find_length_of_Loop {

    // TC: O(2*N)  SC: O(N)
    public static int countNodesInLoop1(Node head) {
        HashMap<Node, Integer> store = new HashMap<>();
        int timer = 0;
        Node temp = head;
        while (temp != null) {
            timer++;
            if (store.containsKey(temp)) return timer - store.get(temp);
            store.put(temp, timer);
            temp = temp.next;
        }
        return -1;
    }

    // TC: O(N) - O(2N) Depends on length of loop SC: O(1)
    public static int countNodesInLoop(Node head) {
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow.equals(fast)) {
                return findLength(slow, fast);
            }
        }
        return -1;
    }

    private static int findLength(Node slow, Node fast) {
        int cnt = 1;
        slow = slow.next;
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
