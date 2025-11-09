public class SearchInBST {
    public TreeNode searchBSTRecursive(TreeNode root, int val) {
        if (root == null || root.data == val) return root;

        if (root.data > val) {
            return searchBSTRecursive(root.left, val);
        } else {
            return searchBSTRecursive(root.right, val);
        }
    }

    public TreeNode searchBSTItr(TreeNode root, int val) {
        TreeNode curr = root;

        while (curr != null) {
            if (curr.data == val) return curr;

            curr = (curr.data > val) ? curr.left : curr.right;
        }

        // The val hasn't been found in BST
        // This will also deal with the root is provided as null as while loop won't be entered
        return null;
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
