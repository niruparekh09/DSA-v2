public class ChildrenSumProperty {
    public static void main(String[] args) {

    }

    /*
     * Checks whether a given binary tree satisfies the "children sum property".
     * The children sum property means that for every node, the node's value
     * should be equal to the sum of its left and right children's values.
     *
     * Example:
     *       10
     *      /  \
     *     8    2
     *    / \    \
     *   3   5    2
     * This tree satisfies the property.
     */
    boolean checkChildrenSum(TreeNode root) {
        // Base case: An empty tree satisfies the property by default
        if (root == null) {
            return true;
        }

        // Base case: A leaf node also satisfies the property (no children to compare)
        if (root.left == null && root.right == null) {
            return true;
        }

        // Get the values of left and right children, use 0 if a child is missing
        int leftVal = (root.left != null) ? root.left.data : 0;
        int rightVal = (root.right != null) ? root.right.data : 0;

        // Check if the current node satisfies the children sum property
        if (root.data == (leftVal + rightVal)) {
            // If current node is valid, recursively check the same property for its children
            return checkChildrenSum(root.left) && checkChildrenSum(root.right);
        } else {
            // If current node doesn't satisfy the condition, return false immediately
            return false;
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
