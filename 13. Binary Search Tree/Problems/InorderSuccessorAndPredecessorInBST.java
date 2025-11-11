import java.util.ArrayList;
import java.util.List;

public class InorderSuccessorAndPredecessorInBST {

    List<Integer> succPredBST(TreeNode root, int key) {
        List<Integer> ans = new ArrayList<>(List.of(-1, -1));
        int succ = Integer.MAX_VALUE;

        TreeNode cur = root;

        // Finding Successor
        while (cur != null) {
            // If cur node is <= key then move to right for finding the immediate successor
            if (cur.data <= key) {
                cur = cur.right;
            }

            // Else set the succ and move to left to see if we can find even smaller successor
            else {
                succ = Math.min(succ, cur.data);
                cur = cur.left;
            }
        }

        cur = root; // resetting cur
        int pred = Integer.MIN_VALUE;

        // Finding Predecessor
        while (cur != null) {
            // Vice versa of successor
            // < instead of <= because key can't be its own pred.
            if (cur.data < key) {
                pred = Math.max(pred, cur.data);
                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }

        ans.set(0, (pred == Integer.MIN_VALUE) ? -1 : pred);
        ans.set(1, (succ == Integer.MAX_VALUE) ? -1 : succ);

        return ans;
    }

    private static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        TreeNode(int _data) {
            this.data = _data;
            this.left = null;
            this.right = null;
        }
    }
}
