import java.util.ArrayList;
import java.util.Arrays;

public class Find_Pairs_With_Given_Sum_In_DLL {

    /*
     * Approach: Two Pointers (Opposite Ends)
     * Pattern: Two Pointers (Requires Sorted List)
     * Time Complexity: O(2N) ~ O(N) - O(N) to find tail, O(N) to converge pointers.
     * Space Complexity: O(1) - Excluding the space for the result list.
     */
    public static ArrayList<ArrayList<Integer>> findPairsWithGivenSum(int target, Node head) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();

        // Initialize pointers at Head (Smallest) and Tail (Largest)
        Node left = head;
        Node right = findRight(head);

        // Logic: Classic Two Sum on Sorted Structure.
        // Stop when pointers cross or meet (left.data >= right.data).
        while (left.data < right.data) {
            int sum = left.data + right.data;

            if (sum == target) {
                res.add(new ArrayList<>(Arrays.asList(left.data, right.data)));
                // Move both inward to look for other pairs
                left = left.next;
                right = right.prev;
            }
            // Logic: Sum is too small, need larger value -> Move Left Forward
            else if (sum < target) {
                left = left.next;
            }
            // Logic: Sum is too big, need smaller value -> Move Right Backward
            else {
                right = right.prev;
            }
        }
        return res;
    }

    // Helper: Traverses to the end to find the Tail node
    private static Node findRight(Node head) {
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        return temp;
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