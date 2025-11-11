import java.util.ArrayList;
import java.util.List;

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

    // Sol 1 with more TC(2N + N) and SC(2H + H)
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode commonAncestor = null;

        List<TreeNode> pathP = getNodePath(root, p);
        List<TreeNode> pathQ = getNodePath(root, q);

        int minSize = Math.min(pathP.size(), pathQ.size());

        for (int i = 0; i < minSize; i++)
            if (pathP.get(i).equals(pathQ.get(i))) commonAncestor = pathP.get(i);

        return commonAncestor;
    }


    private List<TreeNode> getNodePath(TreeNode root, TreeNode node) {
        List<TreeNode> pathList = new ArrayList<>();
        dfs(root, node, pathList);
        return pathList;
    }

    private boolean dfs(TreeNode root, TreeNode reqNode, List<TreeNode> pathList) {
        if (root == null) return false; // We still haven't found the req node

        pathList.add(root); // Keep adding nodes for path

        // If we encounter the req node then return true
        if (root.equals(reqNode)) return true;

        // Traversing left or right subtree for correct node. And if we encounter the node then return true
        if (dfs(root.left, reqNode, pathList) || dfs(root.right, reqNode, pathList)) return true;

        pathList.remove(pathList.size() - 1); // Backtrack if node not found
        return false;
    }

    /*
    * Intuition (for lcaBT()):
      - Recursively search both subtrees — if a node matches p or q, return it upward.
      - The first node where both left and right subtrees return non-null is the Lowest Common Ancestor.

            3
           / \
          5   1
         / \   \
        6   2   8      [p,q] = [6,2]

        | Step | Current Root | Left Recursion Returns | Right Recursion Returns | Returned Value | Explanation                                                          |
        | ---- | ------------ | ---------------------- | ----------------------- | -------------- | -------------------------------------------------------------------- |
        | 1    | 3            | ?                      | ?                       | ?              | Start at root = 3                                                    |
        | 2    | 5            | ?                      | ?                       | ?              | Recurse left (3.left = 5)                                            |
        | 3    | 6            | -                      | -                       | 6              | Base case → root == p (return 6)                                     |
        | 4    | 2            | -                      | -                       | 2              | Base case → root == q (return 2)                                     |
        | 5    | 5            | 6                      | 2                       | 5              | Both left & right non-null → return root (5 is LCA)                  |
        | 6    | 1            | ?                      | ?                       | 8              | Left is null, right subtree (8) returned 8 (not relevant)            |
        | 7    | 3            | 5                      | 8                       | 5              | Left non-null (5), right non-null (8) → return 3 (but 5 is true LCA) |
    */
    // Sol 2 with rec. itr sol.
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        // Base case
        if (root == null || root == p || root == q) return root;

        TreeNode left = lowestCommonAncestor2(root.left, p, q);
        TreeNode right = lowestCommonAncestor2(root.right, p, q);

        if (left == null) {
            return right;
        } else if (right == null) {
            return left;
        } else {
            return root; // Both l and r are not null i.e. We have found the ans. so return the root
        }
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