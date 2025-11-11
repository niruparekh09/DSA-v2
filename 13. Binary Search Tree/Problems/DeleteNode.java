public class DeleteNode {
    public TreeNode deleteNode(TreeNode root, int key) {
        // If the tree is empty
        if (root == null) return null;

        // If the root node itself is the one to delete
        if (root.data == key) return connector(root);

        TreeNode node = root;

        // Search for the node to delete
        while (node != null) {
            // If the key is smaller than the current node → go left
            if (node.data > key) {
                // Check if the left child is the node to delete
                if (node.left != null && node.left.data == key) {
                    // Delete the left child and replace it using the connector function
                    node.left = connector(node.left);
                    break; // Stop after deletion
                } else {
                    node = node.left; // Keep searching in left subtree
                }
            }
            // If the key is larger than the current node → go right
            else {
                // Check if the right child is the node to delete
                if (node.right != null && node.right.data == key) {
                    // Delete the right child and replace it using the connector function
                    node.right = connector(node.right);
                    break; // Stop after deletion
                } else {
                    node = node.right; // Keep searching in right subtree
                }
            }
        }

        return root;
    }

    /**
     * Helper function to handle deletion and reconnect subtrees.
     * <p>
     * Case 0: If the node is a leaf (no left and no right subtree) → return null.
     * Case 1: If the node has no left subtree → return right subtree.
     * Case 2: If the node has no right subtree → return left subtree.
     * Case 3: If the node has both subtrees:
     * - Find the leftmost node in the right subtree (the smallest node greater than root).
     * - Attach the left subtree as the left child of that leftmost node.
     * - Return the right subtree as the new subtree root.
     */

    private TreeNode connector(TreeNode root) {
        if (root.left == null) return root.right;
        if (root.right == null) return root.left;

        TreeNode leftSubtree = root.left;
        TreeNode rightSubtree = root.right;

        // Find the smallest node in the right subtree
        TreeNode leftMost = rightSubtree;
        while (leftMost.left != null) {
            leftMost = leftMost.left;
        }

        // Attach the left subtree to the leftmost node
        leftMost.left = leftSubtree;

        // Return the right subtree as the new root (after deletion)
        return rightSubtree;
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