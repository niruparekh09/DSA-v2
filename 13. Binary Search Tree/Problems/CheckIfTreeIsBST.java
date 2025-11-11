public class CheckIfTreeIsBST {
    /*
           8
          / \
         3   10
            /
           9

     | Node                    | Range (minValue, maxValue) | Check       | Result |
     | ----------------------- | -------------------------- | ----------- | ------ |
     | 8                       | (-∞, +∞)                   | 8 in range  | ✅     |
     | 3                       | (-∞, 8)                    | 3 in range  | ✅     |
     | null (3’s left & right) | —                          | return true | ✅     |
     | 10                      | (8, +∞)                    | 10 in range | ✅     |
     | 9                       | (8, 10)                    | 9 in range  | ✅     |
     | null (9’s left & right) | —                          | return true | ✅     |
     */
    public boolean isBST(TreeNode root) {
        // Start validation with the full integer range for the root node
        return check(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean check(TreeNode root, long minValue, long maxValue) {
        // An empty tree is a valid BST
        if (root == null) return true;

        // Current node must lie strictly within the allowed range
        if (root.data <= minValue || root.data >= maxValue) return false;

        // Recursively check:
        // - Left subtree with updated max range (current node’s value)
        // - Right subtree with updated min range (current node’s value)
        return check(root.left, minValue, root.data) && check(root.right, root.data, maxValue);
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