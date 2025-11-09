public class FindMinInBST {

    // TC: O(n) SC: O(H) RecStack
    public int minValueRec(TreeNode root) {
        if (root == null) return -1;
        // If we have reached the left most node in left subtree, return the value or keep traversing
        return (root.left == null) ? root.data : minValueRec(root.left);
    }

    // TC: O(n) SC: O(1)
    public int minValueItr(TreeNode root) {
        if (root == null) return -1;
        TreeNode cur = root;

        while (cur.left != null) {
            cur = cur.left;
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
