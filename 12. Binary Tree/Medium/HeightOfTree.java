import java.util.LinkedList;
import java.util.Queue;

public class HeightOfTree {

    // Using Recursion
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;

        // Recursively traverse left and right subtree of a node
        int lh = maxDepth(root.left);
        int rh = maxDepth(root.right);

        // Height of tree = current node + max of height of left/right subtree
        return 1 + Math.max(lh, rh);
    }

    // Using Iteration
    public int maxDepthUsingLeveOrderTraversal(TreeNode root) {
        if (root == null) return 0;

        int level = 0;

        Queue<TreeNode> q = new LinkedList<>();

        q.add(root);

        while (!q.isEmpty()) {
            int size = q.size();

            // Traversing a level at once and adding its child to queue.
            for (int i = 0; i < size; i++) {
                TreeNode removed = q.remove();

                if (removed.left != null) q.add(removed.left);
                if (removed.right != null) q.add(removed.right);
            }

            // Increasing level in every pass.
            level++;
        }

        return level;
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
