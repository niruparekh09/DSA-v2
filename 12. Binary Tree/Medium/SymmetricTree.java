public class SymmetricTree {
    /*
         1
        / \
       2   2
      / \ / \
     3  4 4  3

     isSymmetric = true
    */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return checkSymmetry(root.left, root.right);
    }

    private boolean checkSymmetry(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true; // Both nodes are null so symmetric
        }

        if (left == null || right == null) {
            return false; // Either node is null so asymmetric
        }

        if (left.data != right.data) {
            return false; // Data not equal so asymmetric
        }

        return checkSymmetry(left.left, right.right) && checkSymmetry(left.right, right.left);
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
