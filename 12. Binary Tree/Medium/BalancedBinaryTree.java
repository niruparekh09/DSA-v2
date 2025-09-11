public class BalancedBinaryTree {

    // O(n*n) Solution
    public boolean isBalancedBrute(TreeNode root) {
        if (root == null) return true;

        int leftH = heightBrute(root.left);
        int rightH = heightBrute(root.right);

        if (Math.abs(leftH - rightH) > 1) return false;

        return isBalancedBrute(root.left) && isBalancedBrute(root.right);
    }

    public int heightBrute(TreeNode root) {
        if (root == null) return 0;

        int lh = heightBrute(root.left);
        int rh = heightBrute(root.right);

        return 1 + Math.max(lh, rh);
    }

    // O(n) Solution
    public boolean isBalanced(TreeNode root) {
        return height(root) != -1;
    }

    public int height(TreeNode root) {
        if (root == null) return 0;

        int lh = height(root.left);
        int rh = height(root.right);

        // Calculating the balance of the tree on the go. If the tree is not balanced then return -1.
        if (Math.abs(lh - rh) > 1) return -1;

        // If either of lh or rh is -1 then the tree is already not balanced so return -1.
        if (lh == -1 || rh == -1) return -1;

        return 1 + Math.max(lh, rh);
    }

    static class TreeNode {
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
