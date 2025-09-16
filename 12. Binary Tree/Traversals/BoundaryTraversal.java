import java.util.ArrayList;
import java.util.List;

public class BoundaryTraversal {

    /*
    # Binary Tree Structure:
    #
    #               1
    #             /   \
    #           2       3
    #         /   \    / \
    #        4     5  6   7
    #             / \
    #            8   9
    #
    # Boundary Traversal (Anti-clockwise):
    #
    # 1 → Left Boundary → Leaves → Reverse Right Boundary
    #
    # Left Boundary (excluding leaf): 2 → 4
    # Leaves (left to right): 8, 9, 6, 7
    # Right Boundary (excluding leaf, reversed): 3
    #
    # Final Boundary Traversal: [1, 2, 4, 8, 9, 6, 7, 3]
    */
    public List<Integer> boundary(TreeNode root) {
        List<Integer> ans = new ArrayList<>();

        if (root == null) return ans;

        ans.add(root.data); // Adding the root data as any of the below functions won't include it.

        // 1. Left Boundary Excluding leaf.
        // 2. Leaf Node (Using Pre Order Trav.)
        // 3. Right Boundary Excluding leaf in reverse.
        if (root.left != null) traverseLeftBoundary(root.left, ans);
        if (root.left != null || root.right != null)
            traverseLeafNode(root, ans); // Adding check to avoid adding root twice in ans
        // List<Integer> revList = new ArrayList<>();
        if (root.right != null) traverseRightBoundaryReverse(root.right, ans);

        // Rather than this we could add the elements in ans. after the recursion, in that way tha recursive stack will
        // pop the operation one by one and the elements will be added  in reverse order.
        //* Keep in mind the normal recursion problem like printing number where we just need to change if we are printing
        //* before recursion call or after recursion call. That will change the order if printed on asc or dsc order.
        /*
        Collections.reverse(revList);
        ans.addAll(revList);
        */

        return ans;
    }

    private void traverseLeftBoundary(TreeNode root, List<Integer> ans) {
        if (root == null) return;

        // Leaf node detection
        if (root.left == null && root.right == null) {
            return;
        }

        ans.add(root.data);

        // Going left -> left and if there is no left node than only going right
        if (root.left != null) traverseLeftBoundary(root.left, ans);
        else if (root.right != null) traverseLeftBoundary(root.right, ans);
    }

    // Using pre-order traversal because it goes to left subtree leaf node first and then right subtree leaf node
    // Root -> Left -> Right
    private void traverseLeafNode(TreeNode root, List<Integer> ans) {
        if (root == null) return;

        // Checking for leaf node
        if (root.left == null && root.right == null) ans.add(root.data);

        traverseLeafNode(root.left, ans);
        traverseLeafNode(root.right, ans);
    }

    private void traverseRightBoundaryReverse(TreeNode root, List<Integer> ans) {
        if (root == null) return;

        // Leaf node detection
        if (root.left == null && root.right == null) {
            return;
        }

        // Going right -> right and if there is no right node than only going left
        if (root.right != null) traverseRightBoundaryReverse(root.right, ans);
        else if (root.left != null) traverseRightBoundaryReverse(root.left, ans);

        ans.add(root.data);
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