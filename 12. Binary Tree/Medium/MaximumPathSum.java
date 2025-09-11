public class MaximumPathSum {
    public int maxPathSum(TreeNode root) {
        int[] maxi = new int[1];
        maxi[0] = Integer.MIN_VALUE;
        findMaxPathSum(root, maxi);
        return maxi[0];
    }

    private int findMaxPathSum(TreeNode root, int[] maxi) {
        // If the node is null, return 0 because there's no path.
        if (root == null) return 0;

        // It asks the left and right children: What's the best path sum you can give me?
        // It ignores negative sums by using Math.max(0, ...) because adding a negative path would only make the total worse.
        int leftPathSum = Math.max(0, findMaxPathSum(root.left, maxi));
        int rightPathSum = Math.max(0, findMaxPathSum(root.right, maxi));

        // At each node, it checks if the best path through this node (including both children) is higher than the current maximum.
        maxi[0] = Math.max(maxi[0], (root.data + leftPathSum + rightPathSum));

        // It only returns the best path from one side (left or right) plus the node’s value because when
        // returning to the parent, the path can only go through one branch.
        return root.data + Math.max(leftPathSum, rightPathSum);
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
