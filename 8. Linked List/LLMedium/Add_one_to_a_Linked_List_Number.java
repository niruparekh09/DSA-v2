public class Add_one_to_a_Linked_List_Number {

    /*
     * Approach: Iterative with Reversal
     * Pattern: LinkedList Reversal / Math
     * Time Complexity: O(3N) ~ O(N) - Reverse, Iterate, Reverse back.
     * Space Complexity: O(1) - In-place operations.
     */
    public static Node addOneItr(Node head) {
        // Step 1: Reverse to access LSB (Least Significant Digit) at the head.
        head = reverse(head);

        Node temp = head;
        int carry = 1; // We want to add 1

        while (temp != null) {
            temp.data = temp.data + carry;

            // Case 1: No overflow (e.g., 4+1=5). We are done.
            if (temp.data < 10) {
                carry = 0;
                break;
            } else {
                // Case 2: Overflow (e.g., 9+1=10). Set current to 0, propagate carry.
                temp.data = 0;
                carry = 1;
            }
            temp = temp.next;
        }

        // Edge Case: Carry persists after loop (e.g., 99 -> 00 with carry 1).
        // Logic: Since we reversed the list, we need to add the new '1' before reversing back,
        // or handle the pointer attachment carefully.
        if (carry == 1) {
            Node newNode = new Node(1);
            head = reverse(head); // Reverse back to original order (0 -> 0)
            newNode.next = head;  // Attach 1 -> 0 -> 0
            return newNode;
        }

        // Step 3: Restore original order
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

    /*
     * Approach: Recursive Backtracking (Optimal for code cleanliness)
     * Pattern: Recursion (DFS)
     * Time Complexity: O(N) - One pass to end, one pass back.
     * Space Complexity: O(N) - Recursion stack depth.
     */
    public static Node addOne(Node head) {
        int carry = helper(head);

        // Edge Case: If carry comes out of the head (e.g., 99 -> 100), create new head.
        if (carry == 1) {
            Node newNode = new Node(1);
            newNode.next = head;
            return newNode;
        }
        return head;
    }

    // Helper utilizes the recursion stack to process from Tail to Head (Backtracking)
    private static int helper(Node temp) {
        // Base Case: Reached null (end of list). Return 1 (the value we want to add).
        if (temp == null) return 1;

        int carry = helper(temp.next);

        temp.data = temp.data + carry;

        // Logic: If sum < 10, carry absorbs here. Return 0 to previous stack frame.
        if (temp.data < 10) return 0;

        // Logic: If sum >= 10, digit becomes 0, propagate 1 up the stack.
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