public class LCAInBinaryTree {
    /*? Lowest Common Ancestor of a Binary Tree
       “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has
        both p and q as descendants (where we allow a node to be a descendant of itself).”
                  3
                /   \
               5     1
              / \   / \
             6   2 0   8
                / \
               7   4

        p = 5, q = 4
        Output: 5
        Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
    ?*/

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode ans = new TreeNode(0);
        return ans;
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
