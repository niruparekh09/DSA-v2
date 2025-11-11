public class LCAInBST {
    public TreeNode lcaBT(TreeNode root, int p, int q) {
        // We have encounter p or q so send the root above in recursive stack
        if (root == null || root.data == p || root.data == q) return root;

        TreeNode left = lcaBT(root.left, p, q);
        TreeNode right = lcaBT(root.right, p, q);

        if (left == null) return right;
        else if (right == null) return left;
        else return root;
    }

    /*
    Example Tree (BST):
            6
           / \
          2   8
         / \ / \
        0  4 7  9
          / \
         3   5

    Find LCA of p = 3 and q = 5

    +-----------+-----------+-----------+-------------------------------+
    | Step      | Current   | Action    | Explanation                   |
    +-----------+-----------+-----------+-------------------------------+
    | 1         | 6         | Go Left   | 3,5 < 6                       |
    | 2         | 2         | Go Right  | 3,5 > 2                       |
    | 3         | 4         | Return    | 3 < 4 < 5 → Found LCA         |
    +-----------+-----------+-----------+-------------------------------+
    Result → LCA = 4
    */
    public TreeNode lca(TreeNode root, int p, int q) {
        if (root == null) return null;

        // Case 1: If both nodes are smaller than current root,
        // LCA must lie in the left subtree.
        if (root.data > p && root.data > q) {
            return lca(root.left, p, q);
        }

        // Case 2: If both nodes are greater than current root,
        // LCA must lie in the right subtree.
        if (root.data < p && root.data < q) {
            return lca(root.right, p, q);
        }

        // Case 3: If one node lies on the left and the other on the right
        // or current root matches one of the nodes,
        // current node is the LCA.
        return root;
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