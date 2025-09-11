public class SameTree {
    // Function to check if two binary trees are identical
    public boolean isSameTree(TreeNode p, TreeNode q) {

        // If both nodes are null, trees are identical at this branch
        if (p == null && q == null)
            return true;

        // If one node is null and the other isn't, trees are not identical
        if (p == null || q == null)
            return false;

        // If the values of the current nodes are different, trees are not identical
        if (p.data != q.data)
            return false;

        // Recursively check if the left subtrees and right subtrees are identical
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
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
