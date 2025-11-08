import java.util.ArrayList;
import java.util.List;

public class LCAInBT {

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
