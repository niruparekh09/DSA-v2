public class FindMaxInBST {

    // TC: O(n) SC: O(H) RecStack
    public int maxValueRec(TreeNode root) {
        if (root == null) return -1;
        // If we have reached the right most node in right subtree, return the value or keep traversing
        return (root.right == null) ? root.data : maxValueRec(root.right);
    }

    // TC: O(n) SC: O(1)
    public int maxValueItr(TreeNode root) {
        if (root == null) return -1;
        TreeNode cur = root;

        while (cur.right != null) {
            cur = cur.right;
        }

        return cur.data;

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
