import java.util.ArrayList;
import java.util.Arrays;

public class Find_Pairs_With_Given_Sum_In_DLL {

    // TC: O(2*N) SC: O(1)
    public static ArrayList<ArrayList<Integer>> findPairsWithGivenSum(int target, Node head) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        Node left = head;
        Node right = findRight(head);
        while (left.data < right.data) {
            int sum = left.data + right.data;
            if (sum == target) {
                res.add(new ArrayList<>(Arrays.asList(left.data, right.data)));
                left = left.next;
                right = right.prev;
            } else if (sum < target) left = left.next;
            else right = right.prev;
        }
        return res;
    }

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
