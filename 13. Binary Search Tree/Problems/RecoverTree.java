import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecoverTree {

    // Brute Solution TC: O(N*logN) SC: O(N)
    List<Integer> inorder = new ArrayList<>();
    int i = 0;

    void recoverTreeBrute(TreeNode root) {
        // Step 1: Get the inorder traversal of the current tree (It has 2 nodes swapped so will be unsorted)
        collectInorder(root, inorder);

        // Step 2: Sort the inorder to get the correct order
        Collections.sort(inorder);

        // Step 3: Restore Inorder to get the correct tree
        restoreInorder(root, inorder);
    }

    private void collectInorder(TreeNode root, List<Integer> inorder) {
        if (root == null) return;
        collectInorder(root.left, inorder);
        inorder.add(root.data);
        collectInorder(root.right, inorder);
    }

    private void restoreInorder(TreeNode root, List<Integer> inorder) {
        if (root == null) return;
        restoreInorder(root.left, inorder);
        root.data = inorder.get(i++);
        restoreInorder(root.right, inorder);
    }

    // Optimal Solution TC: O(N) SC: O(N)
    private TreeNode prev;
    private TreeNode first;
    private TreeNode middle;
    private TreeNode last;

    void recoverTree(TreeNode root) {
        first = middle = last = null;
        prev = new TreeNode(Integer.MIN_VALUE);
        inorderTraversal(root);

        // 2nd Violation
        if (first != null && last != null) {
            int temp = first.data;
            first.data = last.data;
            last.data = temp;
        }

        // 1st Violation
        else if (first != null && middle != null) {
            int temp = first.data;
            first.data = middle.data;
            middle.data = temp;
        }
    }

    private void inorderTraversal(TreeNode root) {
        if (root == null) return;

        // Inorder: Left → Root → Right
        inorderTraversal(root.left);

        // Detect violation in sorted (inorder) order
        if (prev != null && root.data < prev.data) {

            // 1st Violation → nodes might be adjacent
            if (first == null) {
                first = prev;   // first misplaced node
                middle = root;  // possible second node
            }
            // 2nd Violation → nodes are non-adjacent
            else {
                last = root;    // mark last misplaced node
            }
        }

        // Update prev to current before moving right
        prev = root;

        inorderTraversal(root.right);
    }

    private class TreeNode {
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
