public class InsertNodeIntoBST {
    public TreeNode insertIntoBSTItr(TreeNode root, int val) {
        if(root == null) return new TreeNode(val);
        TreeNode cur = root;

        while (true) {
            // If the val is greater than root.data
            if (cur.data < val) {
                if (cur.right != null) cur = cur.right; // Till We Reach the leaf node
                else {
                    cur.right = new TreeNode(val);
                    break;
                }
            } else {
                if (cur.left != null) cur = cur.left;
                else {
                    cur.left = new TreeNode(val);
                    break;
                }
            }
        }

        return root;
    }

    public TreeNode insertIntoBSTRec(TreeNode root, int val) {
        // Base case: found the correct spot
        if (root == null) {
            return new TreeNode(val);
        }

        // Recur down the tree
        if (val < root.data) {
            root.left = insertIntoBSTRec(root.left, val);
        } else {  // val > root.val since it's guaranteed unique
            root.right = insertIntoBSTRec(root.right, val);
        }

        // Return the (unchanged) root node pointer
        return root;
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
