public class DiaOfBT {
    int maxi = Integer.MIN_VALUE;

    // O(n*n) solution
    public int diameterOfBinaryTree(TreeNode root) {
        calcDiameterOfBinaryTree(root);
        return maxi;
    }

    public void calcDiameterOfBinaryTree(TreeNode root) {
        if (root == null) return;

        // Finding left and right height for each node
        int leftH = findHeight(root.left);
        int rightH = findHeight(root.right);

        maxi = Math.max(maxi, leftH + rightH); // Calc. max dia.

        // Recursively finding Diameter at every node.
        calcDiameterOfBinaryTree(root.left);
        calcDiameterOfBinaryTree(root.right);
    }

    // Finding the max depth/height of a root of a tree or a node of a tree
    public int findHeight(TreeNode root) {
        if (root == null) return 0;

        int lh = findHeight(root.left);
        int rh = findHeight(root.right);

        return 1 + Math.max(lh, rh);
    }

    // O(n) solution
    public int diameterOfBinaryTreeOptimal(TreeNode root) {
        int[] maxi = new int[1];
        dfs(root, maxi);
        return maxi[0];
    }

    public int dfs(TreeNode root, int[] maxi) {
        if (root == null)
            return 0;

        int lh = dfs(root.left, maxi);
        int rh = dfs(root.right, maxi);

        maxi[0] = Math.max(maxi[0], (lh + rh));

        return 1 + Math.max(lh, rh);
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
