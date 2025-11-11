import java.util.ArrayList;
import java.util.Collections;

public class KthNode {

    // TC: O(N) + O(NLogN) | SC: O(N)
    public int kthSmallestBrute(TreeNode root, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        traverseTree(root, list);
        Collections.sort(list);
        return list.get(k - 1);
    }

    private void traverseTree(TreeNode root, ArrayList<Integer> list) {
        if (root == null) return;

        list.add(root.data);
        traverseTree(root.left, list);
        traverseTree(root.right, list);
    }

    /**
     * Property of BST: Inorder Of BST always gives sorted list
     * For Normal Iterative or recursive inorder traversal TC: O(N) SC: O(N)
     * For Morris Inorder traversal TC: O(N) SC: O(1)
     */
    // TC: O(N) SC: O(N)
    public int kthSmallestBetter(TreeNode root, int k) {
        int[] counter = new int[1]; // counter[0] = count of nodes visited
        int[] result = new int[1]; // result[0] = the k-th smallest value
        result[0] = -1; // Initialize the result

        inOrder(root, k, counter, result);
        return result[0]; // Return the value stored in the array
    }

    private void inOrder(TreeNode root, int k, int[] counter, int[] result) {
        // If the value is already found, stop all recursion immediately
        if (root == null || result[0] != -1) return;

        // 1. Traverse Left
        inOrder(root.left, k, counter, result);

        // Check again in case the left call found the result
        if (result[0] != -1) return;

        // 2. Visit Node (In-Order Processing)
        counter[0]++;
        if (counter[0] == k) {
            result[0] = root.data; // Store the result
            return; // Stop processing for this branch
        }

        // 3. Traverse Right
        inOrder(root.right, k, counter, result);
    }

    // TC: O(N) SC: O(1)
    public int kthSmallest(TreeNode root, int k) {
        int res = -1;
        int counter = 0;

        TreeNode cur = root;

        while (cur != null) {
            // Check if the cur node has a left child or not
            if (cur.left == null) {
                counter++;
                if (counter == k) {
                    res = cur.data;
                    break;
                }
                cur = cur.right; // <-- move to right after visiting
            }
            // If the cur has a left child than make a chain with the rightmost elem of left subtree to the cur node
            else {
                TreeNode prev = cur.left;

                while (prev.right != null && prev.right != cur) prev = prev.right;

                // First visit to the rightmost child of left subtree
                if (prev.right == null) {
                    prev.right = cur;
                    cur = cur.left; // Move to left child
                }

                // Second visit i.e. chain already exists (We have already visited once). Now we visit the node for inorder
                else {
                    prev.right = null; // Break the chain
                    counter++;
                    if (counter == k) {
                        res = cur.data;
                        break;
                    }
                    cur = cur.right; // Left subtree has been visited so visit right now
                }
            }
        }

        return res;
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
