public class CheckCompletenessOfBT {
    /*
     * Approach: Index-based Validation
     * Pattern: Complete Binary Tree (CBT) Properties
     * Time Complexity: O(N) - Two passes: one to count nodes, one to validate.
     * Space Complexity: O(H) - Recursive stack depth, proportional to tree height.
     */
    public boolean isCompleteTree(TreeNode root) {
        // Step 1: Get the total count of nodes to establish the valid index boundary.
        int totalNodeCount = countTotalNodes(root);

        // Step 2: Perform a recursive check using 0-based heap indexing.
        return isCBT(root, 0, totalNodeCount);
    }

    /**
     * CBT Validation Rule:
     * In a complete binary tree, if we assign indices (root=0, left=2i+1, right=2i+2),
     * every node's index must be less than the total number of nodes.
     */

    private boolean isCBT(TreeNode root, int ind, int totalNodeCount) {
        if (root == null) return true;

        // If current index is out of bounds, there is a "gap" before this node.
        if (ind >= totalNodeCount) return false;

        // Recurse: Ensure both subtrees maintain valid continuous indexing.
        return isCBT(root.left, 2 * ind + 1, totalNodeCount) &&
                isCBT(root.right, 2 * ind + 2, totalNodeCount);
    }

    /**
     * Helper to count nodes:
     * Required to define the threshold for the index check.
     */
    private int countTotalNodes(TreeNode root) {
        if (root == null) return 0;

        return 1 + countTotalNodes(root.left) + countTotalNodes(root.right);
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}